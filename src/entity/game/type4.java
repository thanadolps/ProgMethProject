package entity.game;

import entity.base.Tower;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class type4 extends Tower{
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

	@Override
	public Image getSprite() {
		return null;
	}

	@Override
	public void tick(Point2D pos, double dt) {

	}

}
