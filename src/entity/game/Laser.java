package entity.game;

import entity.base.BulletsType;

public class Laser extends type3{

	public Laser(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setType(BulletsType.PIERCE);
		// TODO Auto-generated constructor stub
	}

}
