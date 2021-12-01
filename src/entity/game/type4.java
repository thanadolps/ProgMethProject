package entity.game;

import entity.base.tower;

public class type4 extends tower{
	//ยังนึกไม่ออกว่าเวล3จะเป็นอะไร

	public type4(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean upgrade_lsh(int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean upgrade_rsh(int price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

}
