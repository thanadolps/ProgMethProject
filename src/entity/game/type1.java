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

import java.util.Optional;

public class type1 extends Tower {

	public type1(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(2);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
	}

	public type1(type1 other) {
		this(other.getSpeedatk(), other.getAttack(), other.getPrice(), other.getX(), other.getY());
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		BulletsType type = this.getType();
		Monster m = findMonster();
		if (m == null)
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
	public Optional<Tower> get_upgrade_lsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type1(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setSpeedatk(getSpeedatk() + 100);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setAttack(getAttack() + 100);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Fire fire = new Fire(getSpeedatk(), getAttack() + 100, new_price, getX(), getY());
				return Optional.of(fire);
			default:
				return Optional.empty();
		}
	}

	private void replace(type1 type1) {
		// TODO: implement
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Optional<Tower> get_upgrade_rsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgraderPrice_rsh();
		var tower = new type1(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setR(getR() + 1);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setR(getR() + 1);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Ice ice = new Ice(getSpeedatk() + 100, getAttack(), new_price, getX(), getY());
				return Optional.of(ice);
			default:
				return Optional.empty();
		}
	}

	@Override
	public int upgradePrice_lsh() {
		// TODO Auto-generated method stub
		switch ( getLevel() ) {
		case 1: 
			return 100;
		case 2:
			return 200;
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
			return 200;
		case 3:
			return 400;
		default:
			return 0;
		}
	}
}
