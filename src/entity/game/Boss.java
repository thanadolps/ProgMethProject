package entity.game;

import entity.base.Monster;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Sprites;
import utils.Utils;

public class Boss extends Monster {

	public Boss() {
		super(50, 1);
		setDlife(50);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Image getSprite() {
		return Sprites.Boss;
	}

	@Override
	public double getHitBoxRadius() {
		return 0.5;
	}

	@Override
	public int getBounty() {
		return 0;
	}
}
