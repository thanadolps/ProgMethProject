package entity.game;

import javafx.scene.image.Image;
import utils.Sprites;

public class SpeedSoldier extends Soldier {

	public SpeedSoldier() {
		super(1500, 2);
		setDlife(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getSprite() {
		return Sprites.SpeedSolider;
	}


	@Override
	public int getBounty() {
		return 75;
	}
}
