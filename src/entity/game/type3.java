package entity.game;

import core.Main;
import entity.base.tower;
import javafx.scene.image.Image;
import javafx.util.Pair;
import logic.Simulation;

public class type3 extends tower {

	public type3(int speedatk, int attack, int price,int x ,int y) {
		super(speedatk, attack, price);
		setR(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean upgrade_lsh(int price) {
		// TODO Auto-generated method stub
		if ( Simulation.getMoney() < price ) return false;
		switch(getLevel()) {
		case 1: 
			Simulation.decreaseMoney(price);
			setPrice(getPrice()+price);
			setSpeedatk(getSpeedatk()+100);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice()+price);
			setAttack(getSpeedatk()+100);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Ice ice = new Ice(getSpeedatk()+100,getAttack(),getPrice()+price,getX(),getY());
			Main.game.getTowers().setTower(getX(), getY(), ice);
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean upgrade_rsh(int price) {
		// TODO Auto-generated method stub
		if ( Simulation.getMoney() < price ) return false;
		switch(getLevel()) {
		case 1: 
			Simulation.decreaseMoney(price);
			setPrice(getPrice()+price);
			setR(getR()+1);
			return true;
		case 2:
			Simulation.decreaseMoney(price);
			setPrice(getPrice()+price);
			setR(getR()+1);
			return true;
		case 3:
			Simulation.decreaseMoney(price);
			Ice ice = new Ice(getSpeedatk()+100,getAttack(),getPrice()+price,getX(),getY());
			Main.game.getTowers().setTower(getX(), getY(), ice);
			return true;
		default:
			return false;
		}
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
	public void tick(Pair<Integer, Integer> pos, double dt) {

	}
}
