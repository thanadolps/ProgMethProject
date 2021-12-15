package entity.game;

import logic.Simulation;

public class Strength extends type2 {

	public Strength(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(getR() + 1);
		setType(null);
		Simulation.getStrength().add(this);
		// TODO Auto-generated constructor stub
	}
	
	


}
