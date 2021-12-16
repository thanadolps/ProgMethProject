package entity.game;

import entity.base.BulletsType;
import javafx.scene.image.Image;
import utils.Sprites;

public class Fire extends type1 {
	
	public Fire(int speedatk, int attack, int price , int x ,int y) {
		super(speedatk, attack, price, x ,y );
		setType(BulletsType.BURN);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getSprite() {
		return Sprites.TowerFire;
	}
}
