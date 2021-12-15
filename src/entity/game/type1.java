package entity.game;

import core.Main;
import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;
import utils.Sprites;

public class type1 extends Tower {

	public type1(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(2);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
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
				setAttack(getAttack() + 100);
				setLevel(getLevel() + 1);
				return true;
			case 3:
				Simulation.decreaseMoney(price);
				Fire fire = new Fire(getSpeedatk(), getAttack() + 100, getPrice() + price, getX(), getY());
				Main.game.getTowers().setTower(getX(), getY(), fire);
				return true;
			default:
				return false;
		}
	}

	private void replace(type1 type1) {
		// TODO: implement
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
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
				Ice ice = new Ice(getSpeedatk() + 100, getAttack(), getPrice() + price, getX(), getY());
				Main.game.getTowers().setTower(getX(), getY(), ice);
				return true;
			default:
				return false;
		}
	}
}
