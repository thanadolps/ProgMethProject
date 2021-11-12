package entity.base;

public class monster {

	private int hp;
	private int speed;
	
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
	
	
}
