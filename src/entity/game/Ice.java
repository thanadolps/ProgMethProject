package entity.game;

import entity.base.BulletsType;
import entity.base.Tower;
import javafx.scene.image.Image;
import utils.Sprites;

import java.util.Optional;

public class Ice extends type1 {
		
	public Ice(int speedatk, int attack, int price , int x ,int y ) {
		super(speedatk, attack, price , x , y);
		setType(BulletsType.FREEZE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<Tower> get_upgrade_lsh() {
		return Optional.empty();
	}

	@Override
	public Optional<Tower> get_upgrade_rsh() {
		return Optional.empty();
	}

	@Override
	public Image getSprite() {
		return Sprites.TowerIce;
	}
}
