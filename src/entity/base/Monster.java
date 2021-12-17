package entity.base;

import core.Main;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import level.Track;
import logic.Simulation;
import utils.Sound;
import utils.Utils;

public abstract class Monster extends Entity {

	private int hp;
	private double baseSpeed;
	private Track track;
	Point2D pos;
	int trackIndex;
	private int dlife = 1;

	protected double freezeDuration = 0;
	protected double burnDuration= 0;

	public Monster(int hp, double baseSpeed) {
		super();
		this.hp = hp;
		this.baseSpeed = baseSpeed;
	}

	public abstract double getHitBoxRadius();
	public abstract int getBounty();
	public abstract Image getSprite();

	@Override
	public void draw(GraphicsContext gc, double dt) {
		var screen = Utils.grid2pixel(getPos());
		var gridDim = Utils.getGridPixelDimension();

		var gx = gridDim.getX();
		var gy = gridDim.getY();

		if(freezeDuration > 0) {
			// gc.setEffect();
		}

		var img = getSprite();
		var target = getTarget();
		var x = screen.getX() - 0.5*gx;
		var y = screen.getY() - 0.5*gy;
		if(target.getX() - getX() > 0) {
			gc.drawImage(img, x, y, gx, gy);
		}
		else {
			// Flipped in x-axis
			Utils.drawSpriteFlipped(gc, img, x, y, gx, gy);
		}
	}

	public boolean isDead() {
		return getHp() <= 0;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp <= 0) {
			this.hp = 0;
			return;
		}
		this.hp = hp;
	}

	public double getBaseSpeed() {
		return baseSpeed;
	}


	public void setBaseSpeed(double baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	/**
	 * ให้ความเร็วของมอนตอนนี้ ใช้ในการเตลื่อนที่ของมอน.
	 * โดยปกติจะเป็นตัวเดียวกับ getBaseSpeed() แต่ไม่จำเป็น. เช่นเวลาโดน freeze bullet, getSpeed จะให้ค่าช้ากว่า getBaseSpeed()
	 * @return ความเร็วในการเคลื่อนที่ของมอน
	 */
	public double getSpeed() {
		return freezeDuration>0? getBaseSpeed()/2 : getBaseSpeed();
	}

	public void setTrack(Track track) {
		this.track = track;
		trackIndex = 0;
		pos = track.path[trackIndex];
	}
		/*var screen = Utils.grid2pixel(pos);
		var gridDim = Utils.getGridPixelDimension();

		var gx = gridDim.getX();
		var gy = gridDim.getY();

		if(freezeDuration > 0) {
			gc.setFill(Color.BLUE);
		}

		gc.fillRect(screen.getX() - 0.25*gx, screen.getY() - 0.25*gy, 0.5 * gx, 0.5 * gy);

		gc.setFill(Color.BLACK);*/

	@Override
	public void tick(double dt) {
		// Move monster along the path
		var target = track.path[trackIndex];
		var deltaS = target.subtract(pos);
		var dist = deltaS.magnitude();
		if (dist < 0.05) {
			trackIndex += 1;
			if (trackIndex >= track.path.length) {
				Simulation.onMonsterEnter(this);
				this.markDestroy();
			}
			return;
		}
		pos = pos.add(deltaS.multiply(getSpeed() * dt / dist));

		// burn and freeze effect
		burnDuration = Math.max(burnDuration - dt, 0);
		freezeDuration = Math.max(freezeDuration - dt, 0);
		if(burnDuration > 0) {
			// takeDamage(burnDmg);
		}
	}

	/**
	 * ทำให้มอนตาย
	 */
	public void die() {
		// TODO: should also handle thing that happen when monster die
		// eg. increase player's money
		Simulation.increaseMoney(getBounty());
		this.markDestroy();
	}

	/**
	 * ประมวณผลเมื่อ monster โดน bullet ยิงโดน. จัดการเรื่องโดน damage และสถานะ freeze/burn จาก bullet.
	 * @param bullets bullet ที่ยิงโดน
	 */
	public void takeBullet(Bullets bullets) {
		Sound.BulletHit.play();
		if(takeDamage(bullets.getAttack())) {
			// Monster is dead, early return
			return;
		}
		switch (bullets.getType()) {
			case BURN -> burnDuration += 1;
			case FREEZE -> freezeDuration += 1;
		}
	}

	/**
	 * ทำให้ monster นี้โดน damage คือเสียเลือด. หากเลือดเหลือน้อยกว่าเท่ากับ 0 มันจะตาย
	 * @param dmg จำนวนเลือดที่เสีย
	 * @return boolean, แสดงว่าหลังโดน damage ตายไหม
	 */
	public boolean takeDamage(int dmg) {
		Main.game.addDmgInd(new DmgInd(getX(), getY(), dmg));
		setHp(getHp() - dmg);
		if(getHp() <= 0) {
			die();
			return true;
		}
		return false;
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

	public int getDlife() {
		return dlife;
	}

	public void setDlife(int dlife) {
		this.dlife = dlife;
	}

	/**
	 * @return ต่ำแหน่งที่มอนกำลังเคลื่อนที่ไปหาเป็นเส้นตรง
	 */
	public Point2D getTarget() {
		return track.path[trackIndex];
	}
}
