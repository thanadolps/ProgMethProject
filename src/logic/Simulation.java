package logic;

import core.Main;
import entity.base.Monster;
import entity.base.Tower;
import entity.game.Farm;
import entity.game.Strength;
import entity.game.boss;
import entity.game.soldier;

import java.util.ArrayList;
import java.util.Timer;

public class Simulation {

	private static int money = 1000;
	private static int round = 1;
	private static int lifepoint = 150;
	private static Timer time = new Timer();
	private static ArrayList<Monster> myTower = new ArrayList<>();
	private static ArrayList<Strength> strength = new ArrayList<>();
	private static ArrayList<Farm> farm = new ArrayList<>();

	public static ArrayList<Farm> getFarm() {
		return farm;
	}

	public static ArrayList<Strength> getStrength() {
		return strength;
	}

	public static void increaseMoney(int price) {
		if (price < 0)
			return;
		setMoney(getMoney() + price);
		Main.sidebar.getTowerInfoUI().refresh();
	}

	public static boolean decreaseMoney(int price) {
		if (price > getMoney())
			return false;
		setMoney(getMoney() - price);
		return true;
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int price) {
		if (money < 0)
			money = 0;
		else
			money = price;
	}

	public static void nextRound() {
		// จับเวลาเวลาแต่ละroundเท่ากัน และมีช่วงพักระหว่างroundปมสามถึงสี่วิ
	}

	public static int getLifepoint() {
		return lifepoint;
	}

	public static void setLifepoint(int lifepoint) {
		Simulation.lifepoint = lifepoint;
	}

	public static void onMonsterEnter(Monster monster) {
		setLifepoint(getLifepoint() - monster.getDlife());
	}

	public void decreasehpTower() {
		if (myTower.isEmpty())
			return;
		for (Monster m : myTower) {
			if (m.getClass().equals(soldier.class))
				setLifepoint(getLifepoint() - m.getDlife());
			if (m.getClass().equals(boss.class))
				setLifepoint(getLifepoint() - m.getDlife());
		}
		myTower.clear();
	}

	public boolean getLose() {
		if (getLifepoint() < 0)
			return false;
		return true;
	}

	public void produce() {
		for (Farm f : farm) {
			increaseMoney(100);
		}
	}

}
