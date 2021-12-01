package entity.base;

public enum Bullets {
	
	BURN(200),
	FREEZE(100),
	NORMAL(150);
	
	public final int label;
	
	private Bullets(int attack) {
		this.label = attack;
	}

}
