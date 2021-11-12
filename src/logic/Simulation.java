package logic;

import entity.base.tower;

public class Simulation {

	private static int money = 500;
	private static int round = 1;
	
	public static void increaseMoney(int price) {
		if ( price < 0 ) return;
		setMoney(getMoney()+price);
	}
	
	public static void decreaseMoney(int price) {
		if ( price > getMoney() ) return;
		setMoney(getMoney()-price);
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int price) {
		if ( money < 0 ) money = 0;
		else money = price;
	}
	
	public static void replace(tower tower) {
		
	}
	
}
