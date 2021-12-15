package entity.base;

import core.Game;
import core.Main;
import entity.game.Strength;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;
import utils.Utils;
import java.lang.Math;

public abstract class Tower {

	private int speedatk;
	private int attack;
	private int price;
	private int level = 1;
	private int x;
	private int y;
	private int r;
	private BulletsType type ;

	public BulletsType getType() {
		return type;
	}

	public void setType(BulletsType type) {
		this.type = type;
	}

	public Tower(int speedatk, int attack, int price , int x , int y) {
		super();
		this.speedatk = speedatk;
		this.attack = attack;
		this.price = price;
		this.x = x;
		this.y = y;
		// GameMap.addTower(x, y, this);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public abstract boolean upgrade_lsh(int price);

	public abstract boolean upgrade_rsh(int price);

	public abstract void attack();

	public void sell() {
		// TODO Auto-generated method stub
		Simulation.increaseMoney(getPrice() / 10);
		Main.game.getTowers().deleteTower(getX(), getY());
	}

	public Monster findMonster() {
		double min = Double.MAX_VALUE ;
		Monster save = null;
		for ( Monster m : Main.game.getMonsters() ) {
			double distance = Math.sqrt(Math.pow(m.getX()-getX(),2)+Math.pow(m.getY()-getY(),2));
			if ( distance < min && distance < getR() ) {
					min = distance;
					save = m;
				}
			}
		if ( save.equals(null) ) return null;
		return save;
		}
	
	public double range() {
		double area = Math.PI * getR() * getR();
		return area;
	}

	public int getSpeedatk() {
		return speedatk;
	}

	public void setSpeedatk(int speedatk) {
		this.speedatk = speedatk;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void inRangetype2() {
		for ( Tower t : Main.game.getTowers().getTower(x, y, null) ) {
			for ( Strength s : Simulation.getStrength() ) {
				double dx = t.getX()-s.getX();
				double dy = t.getY()-s.getY();
				double r = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
				if ( r < s.getR() ) {
					t.setAttack(t.getAttack()+100) ;
					return;
				}
				}
			}
		}
	

	public int getR() {
		return r;
	}

	public void setR(int r) {
		if (r < 0)
			r = 1;
		this.r = r;
	}


	public abstract void tick(Pair<Integer, Integer> pos, double dt);

	protected abstract Image getSprite();
	
	public void draw(Pair<Integer, Integer> pos, GraphicsContext gc, double dt) {
		var px = Utils.grid2pixel(Utils.pair2point(pos));
		var gridDim = Utils.getGridPixelDimension();
		gc.drawImage(getSprite(), px.getX(), px.getY(), gridDim.getX(), gridDim.getY());
	}
	
	

}
