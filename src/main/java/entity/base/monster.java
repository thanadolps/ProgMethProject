package main.java.entity.base;

import main.java.logic.GameMap;

public class monster {

	private int hp;
	private int speed;
	private int dlife = 1;
	
	public int getDlife() {
		return dlife;
	}

	public void setDlife(int dlife) {
		this.dlife = dlife;
	}

	public monster(int hp, int speed) {
		super();
		this.hp = hp;
		this.speed = speed;
	}
	
	public void run() {
		
	}
	
	public boolean dead() {
		if ( getHp() <= 0 ) return true;
		return false;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if ( hp <= 0 ) return;
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void takeDamage( Bullets bullets ) {
		if ( bullets == Bullets.BURN ) setHp(getHp()-50);
		if ( bullets == Bullets.FREEZE ) setSpeed(getSpeed()-10);
		setHp(getHp()-bullets.label);
		GameMap.deleteBullets(bullets);
		if ( dead() ) delete(this); //ลบตัวนั้นออกยังนึกไม่ออก
	}
	
	
}
