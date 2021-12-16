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

import java.util.Optional;

public class type3 extends Tower {

	public type3(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(1);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
	}

	public type3(type3 other) {
		this(other.getSpeedatk(), other.getAttack(), other.getPrice(), other.getX(), other.getY());
	}

	@Override
	public Optional<Tower> get_upgrade_lsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type3(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setSpeedatk(getSpeedatk() + 100);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setAttack(getSpeedatk() + 100);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Laser l = new Laser(getSpeedatk() + 100, getAttack(), new_price, getX(), getY());
				return Optional.of(l);
			default:
				return Optional.empty();
		}
	}

	@Override
	public Optional<Tower> get_upgrade_rsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type3(this);
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
				Boom b = new Boom(getSpeedatk() + 100, getAttack(), new_price, getX(), getY());
				return Optional.of(b);
			default:
				return Optional.empty();
		}
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
	public int upgradePrice_lsh() {
		// TODO Auto-generated method stub
		return switch (getLevel()) {
			case 1 -> 200;
			case 2 -> 250;
			case 3 -> 450;
			default -> 0;
		};
	}

	@Override
	public int upgraderPrice_rsh() {
		// TODO Auto-generated method stub
		return switch (getLevel()) {
			case 1 -> 100;
			case 2 -> 300;
			case 3 -> 450;
			default -> 0;
		};
	}
}
