package entity.game;

import core.Game;
import core.Main;
import entity.base.Tower;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import logic.GameMap;
import logic.Simulation;
import utils.Sprites;

public class type1 extends Tower {

	public type1(int speedatk, int attack, int price, int x, int y) {
		super(speedatk, attack, price);
		setR(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}


	@Override
	public Image getSprite() {
		return Sprites.TowerSprite;
	}

	@Override
	public void tick(Point2D pos, double dt) {
		// Testing code
		// Make slow monster to the right of the tower dead
		int x = (int)pos.getX();
		int y = (int)pos.getY();

		var monsters = Main.game.getMonstersAt(x+1, y);
		for (var m : monsters) {
			if(m.getSpeed() < 15) {
				m.die();
			}
		}

	}

	@Override
	public boolean upgrade_lsh(int price) {
		// TODO Auto-generated method stub
		if (Simulation.getMoney() < price)
			return false;
		switch (getLevel()) {
		case 1:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setSpeedatk(getSpeedatk() + 100);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setAttack(getAttack() + 100);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Fire fire = new Fire(getSpeedatk(), getAttack() + 100, getPrice() + price, getX(), getY());
			GameMap.delete(getX(), getY());
			GameMap.addTower(getX(), getY(), fire);
			return true;
		default:
			return false;
		}
	}

	private void replace(type1 type1) {
		// TODO: implement
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean upgrade_rsh(int price) {
		// TODO Auto-generated method stub
		if (Simulation.getMoney() < price)
			return false;
		switch (getLevel()) {
		case 1:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setR(getR() + 1);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice() + price);
			setR(getR() + 1);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Ice ice = new Ice(getSpeedatk() + 100, getAttack(), getPrice() + price, getX(), getY());
			GameMap.delete(getX(), getY());
			GameMap.addTower(getX(), getY(), ice);
			return true;
		default:
			return false;
		}
	}
}
