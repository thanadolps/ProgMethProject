package entity.base;


public abstract class tower {

	private int speedatk;
	private int attack;
	private int price;
	private int level;
	private int x;
	private int y;
	
	public tower(int speedatk, int attack, int price, int x ,int y) {
		super();
		this.speedatk = speedatk;
		this.attack = attack;
		this.price = price;
		this.x = x;
		this.y = y;
		
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public abstract void upgrade_lsh(int price);
	
	public abstract void upgrade_rsh(int price);
	
	public abstract void attack();
	
	public abstract void range();
	
	public abstract void sell();

	public int getSpeedatk() {
		return speedatk;
	}

	public void setSpeedatk(int speedatk) {
		this.speedatk = speedatk;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean inRangetype2() {
		return true;
	}
	
	public void increaseRange() {
		
	}

	
	
}
