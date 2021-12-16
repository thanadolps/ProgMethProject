package entity.game;

import entity.base.BulletsType;
import javafx.scene.image.Image;
import utils.Sprites;

public class Laser extends type3{

	public Laser(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setType(BulletsType.PIERCE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getSprite() {
		return Sprites.TowerLaser;
	}
}
