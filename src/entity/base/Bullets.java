package entity.base;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

import core.Main;

public class Bullets extends Entity {
	
	private int x ;
	private int y ;
	private int attack;
	private BulletsType type;
	private Monster m;

	@Override
	public void draw(GraphicsContext gc, double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(double dt) {
		// TODO Auto-generated method stub
		double h = Main.game.getCurrentLevel().getTileGrid().getIndexHeight();
		double w = Main.game.getCurrentLevel().getTileGrid().getIndexWidth();
		double vx = m.getX()-x;
		double vy = m.getY()-y;
		double d = Math.sqrt(Math.pow(vx, 2)+Math.pow(vy, 2));
		//dsx = vx(speed)/d
		double dsx = vx*1.2/d;
		double dsy = vy*1.2/d;
		this.x += dsx*dt;
		this.y += dsy*dt;
		//ทำไงให้ bullets หาย
		if ( hitMonster(m) && !type.equals(BulletsType.PIERCE)) markDestroy();
		if ( !((0<=x && x<=w) && (0<=y && y<=h)) ) markDestroy();
		
		
	}
	
	public boolean hitMonster(Monster m) {
		double dx = m.getX()-x;
		double dy = m.getY()-y;
		double r = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		if ( r > 1 ) return false;
		return true;
	}
	
	public Bullets() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Bullets(int x , int y , int attack , BulletsType type , Monster m ) {
		this.x = x;
		this.y = y;
		this.attack = attack;
		this.type = type;
		this.m = m;
	}

	public void getDamage() {
		if ( type.equals(BulletsType.BURN) ) {
			m.setHp(m.getHp()-attack-50);
		}
		else if ( type.equals(BulletsType.FREEZE)) {
			m.setHp(attack);
			m.setSpeed(m.getSpeed()-10);
		}
		else {
			m.setHp(attack);
		}
		this.markDestroy();
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BulletsType getType() {
		return type;
	}

	public void setType(BulletsType type) {
		this.type = type;
	}
	
	
	
	
	
}
