package entity.game;

import logic.Simulation;

public class Farm extends type2 {
	
	public Farm(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(getR() + 1);
		setType(null);
		Simulation.getFarm().add(this);
		// TODO Auto-generated constructor stub
	}
	
}
