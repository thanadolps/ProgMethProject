package main.java.entity.base;

import main.java.logic.GameMap;
import main.java.logic.Simulation;

public abstract class tower {

	private int speedatk;
	private int attack;
	private int price;
	private int level = 1;
	private int x;
	private int y;
	private int r;

	public tower(int speedatk, int attack, int price) {
		super();
		this.speedatk = speedatk;
		this.attack = attack;
		this.price = price;
		GameMap.addTower(x, y, this);

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
		GameMap.delete(getX(), getY());
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

	public boolean inRangetype2() {
		return true;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		if (r < 0)
			r = 1;
		this.r = r;
	}

}
