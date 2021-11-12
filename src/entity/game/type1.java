package entity.game;

import entity.base.tower;
import logic.Simulation;

public class type1 extends tower {

	private boolean pierce;
	
	public type1(int speedatk, int attack, int price) {
		super(speedatk, attack, price);
		// TODO Auto-generated constructor stub
	}

	public void setPierce(boolean pierce) {
		this.pierce = pierce;
	}

	public void increase_Attack() {
		setAttack(getAttack()+100);
	}

	public void increase_AttackSpeed() {
		setSpeedatk(getSpeedatk()+100);
	}
	
	public void incrase_range() {
		
	}
	
	public void break_through() {
		setPierce(true);
	}
	
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void range() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgrade_lsh( int price ) {
		// TODO Auto-generated method stub
		switch(getLevel()) {
			case 1: 
				if ( Simulation.getMoney() < price ) break;
				Simulation.decreaseMoney(price);
				setPrice(getPrice()+price);
				setSpeedatk(getSpeedatk()+100);
				break;
			case 2:
				if ( Simulation.getMoney() < price ) break;
				Simulation.decreaseMoney(price);
				setPrice(getPrice()+price);
				setAttack(getAttack()+100)
				break;
			case 3:
				if ( Simulation.getMoney() < price ) break;
				Simulation.decreaseMoney(price);
				Fire fire = new Fire(getSpeedatk(),getAttack()+100,getPrice()+price);
				replace(fire);
				break;
			default:
				break;
		}
	}

	@Override
	public void upgrade_rsh(int price) {
		// TODO Auto-generated method stub
		switch(getLevel()) {
		case 1: 
			if ( Simulation.getMoney() < price ) break;
			Simulation.decreaseMoney(price);
			setPrice(getPrice()+price);
			increaseRange();
			break;
		case 2:
			if ( Simulation.getMoney() < price ) break;
			Simulation.decreaseMoney(price);
			setPrice(getPrice()+price);
			increaseRange();
			break;
		case 3:
			if ( Simulation.getMoney() < price ) break;
			Simulation.decreaseMoney(price);
			Ice ice = new Ice(getSpeedatk()+100,getAttack(),getPrice()+price);
			replace(ice);
			break;
		default:
			break;
	}
	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub
		Simulation.increaseMoney(getPrice()/10);
		delete();
	}

}
