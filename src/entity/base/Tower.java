package entity.base;

import core.Main;
import core.timing.Interval;
import entity.game.Boom;
import entity.game.Farm;
import entity.game.Fire;
import entity.game.Ice;
import entity.game.Laser;
import entity.game.Strength;
import entity.game.type1;
import entity.game.type2;
import entity.game.type3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;
import utils.Utils;
import java.lang.Math;
import java.util.Optional;

public abstract class Tower implements Cloneable {

	private double speedatk;
	private int baseAttack;
	private int extraAttack;
	private int price;
	private int level = 1;
	private int x;
	private int y;
	private double r;
	private BulletsType type;

	private Interval attackTimer;

	public BulletsType getType() {
		return type;
	}

	public void setType(BulletsType type) {
		this.type = type;
	}

	public Tower(double speedatk, int baseAttack, int price, int x, int y) {
		super();
		this.speedatk = speedatk;
		this.baseAttack = baseAttack;
		this.price = price;
		this.x = x;
		this.y = y;
		this.type = BulletsType.NORMAL;

		this.attackTimer = new Interval(getPeriod());
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

	public abstract Optional<Tower> get_upgrade_lsh();

	public abstract Optional<Tower> get_upgrade_rsh();

	public boolean upgrade_lsh(int price) {
		if (!Simulation.decreaseMoney(price)) {
			return false;
		}
		var new_tower = get_upgrade_lsh();
		if(new_tower.isPresent()) {
			var tower = new_tower.get();
			Main.game.getTowers().setTower(tower.getX(), tower.getY(), tower);
		}
		return true;
	}

	public boolean upgrade_rsh(int price) {
		if (!Simulation.decreaseMoney(price)) {
			return false;
		}
		var new_tower = get_upgrade_rsh();
		if(new_tower.isPresent()) {
			var tower = new_tower.get();
			Main.game.getTowers().setTower(tower.getX(), tower.getY(), tower);
		}
		return true;
	}

	public abstract void attack();

	public void sell() {
		// TODO Auto-generated method stub
		Simulation.increaseMoney(getPrice() / 10);
		Main.game.getTowers().deleteTower(getX(), getY());
	}

	public double getCenterX() {
		return x + 0.5;
	}

	public double getCenterY() {
		return y + 0.5;
	}

	public Monster findMonster() {
		double min = Double.MAX_VALUE;
		Monster save = null;
		for (Monster m : Main.game.getMonsters()) {
			double distance = Math.sqrt(Math.pow(m.getX() - getCenterX(), 2) + Math.pow(m.getY() - getCenterY(), 2));
			if (distance < min && distance < getR()) {
				min = distance;
				save = m;
			}
		}
		return save;
	}

	public double range() {
		double area = Math.PI * getR() * getR();
		return area;
	}

	public double getPeriod() {
		return 1.0/(double)(speedatk);
	}

	public double getSpeedatk() {
		return speedatk;
	}

	public void setSpeedatk(double speedatk) {
		this.speedatk = speedatk;
		attackTimer.setPeriod(getPeriod());
		attackTimer.resetTime();
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}

	/*public void พำดพำห้ExtraAttack() {
		// checkNearbyStrength
	}*/

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * คำนวนว่่า tower อยู่ในวง buff จาก tower แบบ strength ไหม
	 * @return boolean, อยู่ในวง buff ไหม
	 */
	public boolean checkNearbyStrength() {
		final boolean[] check = {false};
		Main.game.getTowers().iterateTower((t, tower) -> {
			for (Strength s : Simulation.getStrength()) {
				if(s == this) {return;}
				double x = t.getKey();
				double y = t.getValue();
				double dx = x - s.getX();
				double dy = y - s.getY();
				double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
				if (r < s.getR()) {
					check[0] = true;
					break;
				}
			}
		});
		return check[0];
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		if (r < 0)
			r = 1;
		this.r = r;
	}

	public void tick(Pair<Integer, Integer> pos, double dt) {
		attackTimer.tick(dt, p -> attack());
	}

	protected abstract Image getSprite();

	public Image getIconSprite() {
		return getSprite();
	}

	public void draw(Pair<Integer, Integer> pos, GraphicsContext gc, double dt) {
		var px = Utils.grid2pixel(Utils.pair2point(pos));
		var gridDim = Utils.getGridPixelDimension();
		gc.drawImage(getSprite(), px.getX(), px.getY(), gridDim.getX(), gridDim.getY());
	}

	public void drawOverlay(Pair<Integer, Integer> pos, GraphicsContext gc, double dt) {
		var px = Utils.grid2pixel(pos.getKey(), pos.getValue());
		var gridDim = Utils.getGridPixelDimension();

		var oldAlpha = gc.getGlobalAlpha();
		gc.setGlobalAlpha(0.5);
		gc.fillOval(px.getX(), px.getY(), gridDim.getX(), gridDim.getY());

		gc.setGlobalAlpha(0.2);
		var r = Utils.grid2pixel(getR(), getR());
		gc.fillOval(px.getX() - r.getX() + gridDim.getX()/2, px.getY() - r.getY() + gridDim.getX()/2, 2*r.getX(), 2*r.getY());

		gc.setGlobalAlpha(oldAlpha);

	}
	
	public abstract int upgradePrice_lsh();
	
	public abstract int upgraderPrice_rsh();
	
	public static String getName(Tower t) {
		if ( t.getClass().equals(Fire.class) ) return "Fire";
		else if ( t.getClass().equals(Ice.class) ) return "Ice";
		else if ( t.getClass().equals(Boom.class) ) return "Boom";
		else if ( t.getClass().equals(Laser.class) ) return "Laser";
		else if ( t.getClass().equals(Farm.class) ) return "Farm";
		else if ( t.getClass().equals(Strength.class) ) return "Strength";
		else if ( t.getClass().equals(type1.class) ) return "Base Tower Type 1";
		else if ( t.getClass().equals(type2.class) ) return "Base Tower Type 2";
		else if (t.getClass().equals(type3.class)) return "Base Tower Type 3";
		else return "Unknown Tower";
	}
	
	public String getBulletsType(Tower t) {
		if ( t.getType().equals(BulletsType.BURN) ) return "BURN";
		else if ( t.getType().equals(BulletsType.FREEZE) ) return "FREEZE";
		else if ( t.getType().equals(BulletsType.PIERCE) ) return "LASER";
		else if ( t.getType().equals(BulletsType.NORMAL) ) return "NORMAL TYPE";
		else return "NULL";
	}
	
}
