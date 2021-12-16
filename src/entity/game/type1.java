package entity.game;

import core.Main;
import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import javafx.scene.image.Image;
import utils.Sprites;

import java.util.Optional;

public class type1 extends Tower {

	public type1(double speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(2);
		setType(BulletsType.NORMAL);
		// TODO Auto-generated constructor stub
	}

	public type1(type1 other) {
		this(other.getSpeedatk(), other.getBaseAttack(), other.getPrice(), other.getX(), other.getY());
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
		return Sprites.TowerType1;
	}

	@Override
	public Optional<Tower> get_upgrade_lsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgradePrice_lsh();
		var tower = new type1(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setSpeedatk(getSpeedatk() + 2);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setBaseAttack(getBaseAttack() + 200);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Fire fire = new Fire(getSpeedatk(), getBaseAttack() + 500, new_price, getX(), getY());
				return Optional.of(fire);
			default:
				return Optional.empty();
		}
	}

	@Override
	public Optional<Tower> get_upgrade_rsh() {
		// TODO Auto-generated method stub
		var new_price = getPrice() + upgraderPrice_rsh();
		var tower = new type1(this);
		switch (getLevel()) {
			case 1:
				tower.setPrice(new_price);
				tower.setR(getR() + 0.5);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 2:
				tower.setPrice(new_price);
				tower.setR(getR() + 0.5);
				tower.setLevel(getLevel() + 1);
				return Optional.of(tower);
			case 3:
				Ice ice = new Ice(getSpeedatk() + 4, getBaseAttack(), new_price, getX(), getY());
				ice.setR(getR());
				return Optional.of(ice);
			default:
				return Optional.empty();
		}
	}

	@Override
	public int upgradePrice_lsh() {
		// TODO Auto-generated method stub
		return switch (getLevel()) {
			case 1 -> 100;
			case 2 -> 200;
			case 3 -> 450;
			default -> 0;
		};
	}

	@Override
	public int upgraderPrice_rsh() {
		// TODO Auto-generated method stub
		return switch (getLevel()) {
			case 1 -> 100;
			case 2 -> 200;
			case 3 -> 400;
			default -> 0;
		};
	}
}
