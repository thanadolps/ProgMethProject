package logic;

import core.Main;
import entity.base.Monster;
import entity.game.Farm;
import entity.game.Strength;
import utils.Sound;

import java.util.ArrayList;

public class Simulation {

	private static int money;
	private static int round ;
	private static int lifepoint;
	private static ArrayList<Strength> strength;
	private static ArrayList<Farm> farm;

	static {
		restart();
	}

	public static void restart() {
		money = 500;
		round = 1;
		lifepoint = 150;
		strength = new ArrayList<>();
		farm = new ArrayList<>();

		if(Main.sidebar != null) {
			Main.sidebar.refresh();
		}
	}

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
		Main.sidebar.refresh();
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
		round += 1;
		Main.sidebar.refresh();
	}

	public static int getRound() {
		return round;
	}

	public static int getLifepoint() {
		return lifepoint;
	}

	public static void setLifepoint(int lifepoint) {
		Simulation.lifepoint = lifepoint;
	}

	public static void onMonsterEnter(Monster monster) {
		setLifepoint(getLifepoint() - monster.getDlife());
		Main.sidebar.refresh();
		if(Math.random() < 0.5) {
			Sound.Hurt1.play();
		}
		else {
			Sound.Hurt2.play();
		}
		if(isLose()) {
			Main.switchToEndScreen();
		}
	}

	public static boolean isLose() {
		return getLifepoint() < 0;
	}
}
