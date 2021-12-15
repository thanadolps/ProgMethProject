package entity.game;

import core.Main;
import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import javafx.geometry.Point2D;
import entity.base.Tower;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;

public class type2 extends Tower {

	// type2 ถ้าเวล 1,2 ตีได้ปกติ แต่ตอนเวล 3 จะตีไม่ได้
	private boolean wide = false;
	private boolean strength = false;
	private boolean speed = false;

	public type2(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(2);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		if (this.getLevel() > 2)
			return;
		BulletsType type = this.getType();
		Monster m = findMonster();
		if (m.equals(null))
			return;
		Bullets b = new Bullets(this.getX(), this.getY(), this.getAttack(), type, m);
		// ต้องใช้ tick ไหม
		b.tick(1.0);
	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub
		Simulation.increaseMoney(getPrice() / 10);
		if (this.getClass().equals(Strength.class))
			Simulation.getStrength().remove(this);
		if (this.getClass().equals(Farm.class))
			Simulation.getFarm().remove(this);
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
			setSpeedatk(getSpeedatk()+100)
			setLevel(getLevel()+1);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setR(getR() + 1);
			setLevel(getLevel()+1);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Farm f = new Farm(getSpeedatk(), getAttack(), getPrice() + price, getX(), getY());
			Main.game.getTowers().setTower(getX(), getY(), f);
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
				Strength s = new Strength(getSpeedatk(), getAttack(), getPrice() + price, getX(), getY());
				Main.game.getTowers().setTower(getX(), getY(), s);
				return true;
			default:
				return false;
		}

	}

}
