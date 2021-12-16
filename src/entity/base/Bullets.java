package entity.base;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;

import core.Main;
import javafx.scene.paint.Color;
import utils.Utils;

public class Bullets extends Entity {
	
	private double x ;
	private double y ;
	private double lastVx = 0;
	private double lastVy = 0;
	private int attack;
	private BulletsType type;
	private Monster m;
	private final HashSet<Monster> attackHistory = new HashSet<>();

	@Override
	public void draw(GraphicsContext gc, double dt) {
		// TODO Auto-generated method stub
		var bulletColor = switch (type) {
			case BURN -> Color.RED;
			case FREEZE -> Color.BLUE;
			case PIERCE -> Color.VIOLET;
			case NORMAL -> Color.BLACK;
		};
		gc.setFill(bulletColor);

		var px = Utils.grid2pixel(x, y);
		gc.fillOval(px.getX(), px.getY(), 10, 10);
	}

	@Override
	public void tick(double dt) {
		// Monster is unassigned case
		if(m == null) {
			// continue moving forward
			this.x += lastVx * dt;
			this.y += lastVy * dt;

			for(var m: Main.game.getMonstersAt((int)getX(), (int)getY())) {
				if(attackHistory.contains(m)) {
					continue;
				}

				var dx = m.getX() - getX();
				var dy = m.getY() - getY();
				var dist_sq = dx*dx + dy*dy;
				if(dist_sq <= m.getHitBoxRadius()*m.getHitBoxRadius()) {
					m.takeBullet(this);
					attackHistory.add(m);
					if(!type.equals(BulletsType.PIERCE)) {
						markDestroy();
					}
				}
			}

			return;
		}

		// Bullet Homing Motion
		double h = Main.game.getCurrentLevel().getTileGrid().getIndexHeight();
		double w = Main.game.getCurrentLevel().getTileGrid().getIndexWidth();
		double vx = m.getX()-x;
		double vy = m.getY()-y;
		double d = Math.sqrt(Math.pow(vx, 2)+Math.pow(vy, 2));
		double dsx = vx*getSpeed()/d;
		double dsy = vy*getSpeed()/d;
		this.x += dsx*dt;
		this.y += dsy*dt;
		lastVx = dsx;
		lastVy = dsy;

		// Monster dead case
		if(m.isDestroyed() || m.isDead()) {
			m = null;
			return;
		}

		// Hit monster case
		if ( hitMonster(m) ) {
			m.takeBullet(this);
			if(!type.equals(BulletsType.PIERCE)) {
				markDestroy();
			}
		}

		// Out of map case
		if ( !((0<=x && x<=w) && (0<=y && y<=h)) ) markDestroy();
	}

	public double getSpeed() {
		return 3;
	}

	public boolean hitMonster(Monster m) {
		double dx = m.getX()-x;
		double dy = m.getY()-y;
		double r = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		return r <= m.getHitBoxRadius();
	}
	
	public Bullets() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Bullets(double x , double y , int attack , BulletsType type , Monster m ) {
		super();
		this.x = x;
		this.y = y;
		this.attack = attack;
		this.type = type;
		this.m = m;
	}


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public BulletsType getType() {
		return type;
	}

	public void setType(BulletsType type) {
		this.type = type;
	}

	public int getAttack() {
		return attack;
	}

	public void setLastVx(double lastVx) {
		this.lastVx = lastVx;
	}

	public void setLastVy(double lastVy) {
		this.lastVy = lastVy;
	}
}
