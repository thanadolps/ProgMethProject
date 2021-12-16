package entity.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Sprites;
import utils.Utils;

public class SpeedSoldier extends Soldier {

	public SpeedSoldier() {
		super();
		setBaseSpeed(2);
		setDlife(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getSprite() {
		return Sprites.SpeedSolider;
	}


	@Override
	public int getBounty() {
		return 20;
	}
}
