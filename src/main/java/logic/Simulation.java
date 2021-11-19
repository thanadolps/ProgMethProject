package main.java.logic;

import main.java.entity.base.monster;
import main.java.entity.base.tower;


import java.util.ArrayList;
import java.util.Timer;

public class Simulation {

	private static int money = 500;
	private static int round = 1;
	private static int lifepoint = 150;
	private static Timer time = new Timer();
	private static ArrayList<monster> myTower = new ArrayList<>();
	
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
	
	public static void nextRound() {
		//จับเวลาเวลาแต่ละroundเท่ากัน และมีช่วงพักระหว่างroundปมสามถึงสี่วิ
	}

	public static int getLifepoint() {
		return lifepoint;
	}

	public static void setLifepoint(int lifepoint) {
		Simulation.lifepoint = lifepoint;
	}
	
	public void monsterIN(monster monster) {
		myTower.add(monster);
	}
	
	public void decreasehpTower() {
		if ( myTower.isEmpty() ) return;
		for ( monster m : myTower ) {
			if ( m.getClass().equals(soldier.getClass())) setLifepoint(getLifepoint()-m.getDlife());
			if ( m.getClass().equals(boss.getClass())) setLifepoint(getLifepoint()-m.getDlife());
		}
		myTower.clear();
	}
	
	public boolean getLose() {
		if ( getLifepoint() < 0 ) return false;
		return true;
	}
	
}
