package entity.game;

import javafx.scene.image.Image;
import logic.Simulation;
import utils.Sprites;

public class Strength extends type2 {

	public Strength(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price, x, y);
		setR(getR() + 1);
		setType(null);
		Simulation.getStrength().add(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getSprite() {
		return Sprites.TowerStrength;
	}

	@Override
	public void attack() {}
}
