package entity.game;

import core.Main;
import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import javafx.scene.image.Image;
import utils.Sprites;

import java.util.Optional;

public class Laser extends type3{

	public Laser(double speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setType(BulletsType.PIERCE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getSprite() {
		return Sprites.TowerLaser;
	}

	@Override
	public void attack() {
		BulletsType type = this.getType();
		Monster m = findMonster();
		if (m == null)
			return;
		Bullets b = new Bullets(this.getCenterX(), this.getCenterY(), this.getAttack(), type, null);
		var d = m.getPos().subtract(b.getX(), b.getY()).normalize().multiply(b.getSpeed());
		b.setLastVx(d.getX());
		b.setLastVy(d.getY());
		Main.game.addBullet(b);
	}

	@Override
	public Optional<Tower> get_upgrade_rsh() {
		return Optional.empty();
	}

	@Override
	public Optional<Tower> get_upgrade_lsh() {
		return Optional.empty();
	}
}
