package entity.game;

import entity.base.tower;
import logic.Simulation;

public class type2 extends tower {

	public type2(int speedatk, int attack, int price) {
		super(speedatk, attack, price);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void range() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void sell() {
		// TODO Auto-generated method stub
		Simulation.increaseMoney(getPrice()/10);
		delete();
	}

	@Override
	public void upgrade_lsh(int price) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgrade_rsh(int price) {
		// TODO Auto-generated method stub
		
	}
	
	

}
