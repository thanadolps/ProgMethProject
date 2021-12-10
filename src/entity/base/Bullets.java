package entity.base;

import javafx.scene.canvas.GraphicsContext;

public class Bullets extends Entity {
	
	private int x ;
	private int y ;
	private BulletsType type;
	private Monster m;

	@Override
	public void draw(GraphicsContext gc, double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(double dt) {
		// TODO Auto-generated method stub
		
	}
	
	public int getDamage() {
		if ( type.equals(BulletsType.Type.BURN) ) return m.se 
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BulletsType getType() {
		return type;
	}

	public void setType(BulletsType type) {
		this.type = type;
	}
	
	
	
	
	
}
