package entity.base;

import logic.GameMap;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import level.Track;
import utils.Utils;

public class Monster extends Entity {

	private int hp;
	private int speed;
	private Track track;
	Point2D pos;
	int trackIndex;
	private int dlife = 1;

	public int getDlife() {
		return dlife;
	}

	public void setDlife(int dlife) {
		this.dlife = dlife;
	}

	public Monster(int hp, int speed) {
		super();
		this.hp = hp;
		this.speed = speed;
	}

	public void run() {

	}

	public boolean isDead() {
		if (getHp() <= 0)
			return true;
		return false;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp <= 0)
			return;
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTrack(Track track) {
		this.track = track;
		trackIndex = 0;
		pos = track.path[trackIndex];
	}

	@Override
	public void draw(GraphicsContext gc, double dt) {
		var screen = Utils.grid2pixel(pos);
		var gridDim = Utils.getGridPixelDimension();
		/*
		 * gc.fillText( String.format("hp{%s},speed{%s},track{%s}",hp,speed,track),
		 * screen.getX(), screen.getY() );
		 */
		var gx = gridDim.getX();
		var gy = gridDim.getY();

		if (getSpeed() < 15) {
			gc.fillRect(screen.getX() - 0.25*gx, screen.getY() - 0.25*gy, 0.5 * gx, 0.5 * gy);
		} else {
			gc.fillOval(screen.getX() - 0.75*gx/2, screen.getY() - 0.75*gy/2, 0.75 * gx, 0.75 * gy);
		}
	}

	@Override
	public void tick(double dt) {
		var target = track.path[trackIndex];
		var deltaS = target.subtract(pos);
		var dist = deltaS.magnitude();
		if (dist < 0.01) {
			trackIndex += 1;
			if (trackIndex >= track.path.length) {
				System.out.println("Monster entered");
				this.markDestroy();
			}
			return;
		}
		pos = pos.add(deltaS.multiply(0.05 * speed * dt / dist));
	}

	public void die() {
		// TODO: should also handle thing that happen when monster die
		// eg. increase player's money
		this.markDestroy();
	}

	public void takeDamage(Bullets bullets) {
		if (bullets.getType().equals(BulletsType.Type.BURN))
			setHp(getHp() - 50);
		if (bullets.getType().equals(BulletsType.Type.FREEZE))
			setSpeed(getSpeed() - 10);
		setHp(getHp() - bullets.label);
		GameMap.deleteBullets(bullets);
		if (isDead())
			die(); // ลบตัวนั้นออกยังนึกไม่ออก
	}

	public double getX() {
		return pos.getX();
	}

	public double getY() {
		return pos.getY();
	}

	public Point2D getPos() {
		return pos;
	}
}
