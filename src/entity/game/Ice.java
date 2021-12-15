package entity.game;

import entity.base.BulletsType;

public class Ice extends type1 {
		
	public Ice(int speedatk, int attack, int price , int x ,int y ) {
		super(speedatk, attack, price , x , y);
		setType(BulletsType.FREEZE);
		// TODO Auto-generated constructor stub
	}

}
