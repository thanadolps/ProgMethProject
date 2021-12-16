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
import utils.Sprites;

import java.util.Optional;

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

	public type2(type2 other) {
		this(other.getSpeedatk(), other.getAttack(), other.getPrice(), other.getX(), other.getY());
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		if (this.getLevel() > 2)
			return;
		BulletsType type = this.getType();
		Monster m = findMonster();
		if (m == null)
			return;
		Bullets b = new Bullets(this.getCenterX(), this.getCenterY(), this.getAttack(), type, m);
		Main.game.addBullet(b);
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
		return Sprites.TowerType2;
	}

	@Override
	public Optional<Tower> get_upgrade_lsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type2(this);
		switch (getLevel()) {
		case 1:
			tower.setPrice(new_price);
			tower.setSpeedatk(getSpeedatk()+100);
			tower.setLevel(getLevel()+1);
			return Optional.of(tower);
		case 2:
			tower.setPrice(new_price);
			tower.setR(getR() + 1);
			tower.setLevel(getLevel()+1);
			return Optional.of(tower);
		case 3:
			Farm f = new Farm(getSpeedatk(), getAttack(), new_price, getX(), getY());
			return Optional.of(f);
		default:
			return Optional.empty();
		}
	}

	@Override
	public Optional<Tower> get_upgrade_rsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type2(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setAttack(getAttack() + 100);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setR(getR() + 1);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Strength s = new Strength(getSpeedatk(), getAttack(), new_price, getX(), getY());
				return Optional.of(s);
			default:
				return Optional.empty();
		}

	}

	@Override
	public int upgradePrice_lsh() {
		// TODO Auto-generated method stub
		return switch (getLevel()) {
			case 1 -> 150;
			case 2 -> 300;
			case 3 -> 600;
			default -> 0;
		};
	}

	@Override
	public int upgraderPrice_rsh() {
		// TODO Auto-generated method stub
		return switch (getLevel()) {
			case 1 -> 150;
			case 2 -> 250;
			case 3 -> 550;
			default -> 0;
		};
	}

}
