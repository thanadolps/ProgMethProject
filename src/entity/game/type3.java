package entity.game;

import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import core.Main;
import javafx.scene.image.Image;
import utils.Sprites;

import java.util.Optional;

public class type3 extends Tower {

	public type3(double speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(1.5);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
	}

	public type3(type3 other) {
		this(other.getSpeedatk(), other.getBaseAttack(), other.getPrice(), other.getX(), other.getY());
	}

	@Override
	public Optional<Tower> get_upgrade_lsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type3(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setSpeedatk(getSpeedatk() + 0.5);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setBaseAttack(getBaseAttack() + 200);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Laser l = new Laser(getSpeedatk() , getBaseAttack() + 900, new_price, getX(), getY());
				l.setR(getR() + 0.5);
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
				tower.setR(getR() + 0.25);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setSpeedatk(getSpeedatk() + 0.5);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Boom b = new Boom(getSpeedatk(), getBaseAttack() + 400, new_price, getX(), getY());
				b.setR(getR());
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
		Bullets b = new Bullets(this.getCenterX(), this.getCenterY(), this.getBaseAttack(), type, m);
		Main.game.addBullet(b);
	}

	@Override
	public Image getSprite() {
		return Sprites.TowerType3;
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
