package entity.game;

import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import javafx.geometry.Point2D;
import core.Main;
import entity.base.Tower;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;

public class type3 extends Tower {

	public type3(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(1);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
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
				setSpeedatk(getSpeedatk() + 100);
				setLevel(getLevel() + 1);
				return true;
			case 2:
				Simulation.decreaseMoney(price);
				setPrice(getPrice() + price);
				setAttack(getSpeedatk() + 100);
				setLevel(getLevel() + 1);
				return true;
			case 3:
				Simulation.decreaseMoney(price);
				Laser l = new Laser(getSpeedatk() + 100, getAttack(), getPrice() + price, getX(), getY());
				Main.game.getTowers().setTower(getX(), getY(), l);
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
				setR(getR() + 1);
				setLevel(getLevel() + 1);
				return true;
			case 2:
				Simulation.decreaseMoney(price);
				setPrice(getPrice() + price);
				setR(getR() + 1);
				setLevel(getLevel() + 1);
				return true;
			case 3:
				Simulation.decreaseMoney(price);
				Boom b = new Boom(getSpeedatk() + 100, getAttack(), getPrice() + price, getX(), getY());
				Main.game.getTowers().setTower(getX(), getY(), b);
				return true;
			default:
				return false;
		}
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		BulletsType type = this.getType();
		Monster m = findMonster();
		if (m.equals(null))
			return;
		Bullets b = new Bullets(this.getX(), this.getY(), this.getAttack(), type, m);
		// ต้องใช้ tick ไหม
		b.tick(1.0);
	}

	@Override
	public Image getSprite() {
		return null;
	}

	@Override
	public void tick(Pair<Integer, Integer> pos, double dt) {

	}

	@Override
	public int upgradePrice_lsh() {
		// TODO Auto-generated method stub
		switch ( getLevel() ) {
		case 1: 
			return 200;
		case 2:
			return 250;
		case 3:
			return 450;
		default:
			return 0;
		}
	}

	@Override
	public int upgraderPrice_rsh() {
		// TODO Auto-generated method stub
		switch ( getLevel() ) {
		case 1: 
			return 100;
		case 2:
			return 300;
		case 3:
			return 450;
		default:
			return 0;
		}
	}
}
