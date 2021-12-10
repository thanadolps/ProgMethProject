package entity.game;

import core.Main;
import entity.base.tower;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;

public class type2 extends tower {

	// type2 ถ้าเวล 1,2 จะยังตีอะไรไม่ได้แต่ถ้าเวล 3 จะตีมอสได้ละ
	private boolean wide = false;
	private boolean strength = false;
	private boolean speed = false;

	public type2(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price);
		setR(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub
		Simulation.increaseMoney(getPrice() / 10);
		Main.game.getTowers().deleteTower(getX(), getY());
	}

	@Override
	public Image getSprite() {
		return null;
	}

	@Override
	public void tick(Pair<Integer, Integer> pos, double dt) {

	}

	@Override
	public boolean upgrade_lsh(int price) {
		// TODO Auto-generated method stub
		if (Simulation.getMoney() < price)
			return false;
		switch (getLevel()) {
		case 1:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setR(getR() + 1);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setR(getR() + 1);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Ice ice = new Ice(getSpeedatk() + 100, getAttack(), getPrice() + price, getX(), getY());
			Main.game.getTowers().setTower(getX(), getY(), ice);
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean upgrade_rsh(int price) {
		// TODO Auto-generated method stub
		if (Simulation.getMoney() < price)
			return false;
		switch (getLevel()) {
		case 1:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setAttack(getAttack() + 100);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setR(getR() + 1);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Ice ice = new Ice(getSpeedatk() + 100, getAttack(), getPrice() + price, getX(), getY());
			Main.game.getTowers().setTower(getX(), getY(), ice);
			return true;
		default:
			return false;
		}

	}

}
