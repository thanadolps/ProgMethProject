package entity.base;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import level.Track;
import utils.Utils;

public class monster extends Entity {

	private int hp;
	private int speed;
	private Track track;
	Point2D pos;
	int trackIndex;
	
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

	public void setTrack(Track track) {
		this.track = track;
		trackIndex = 0;
		pos = track.path[trackIndex];
	}

	@Override
	public void draw(GraphicsContext gc, double dt) {
		var screen = Utils.grid2pixel(pos);
		var gridDim = Utils.getGridPixelDimension();
		/*gc.fillText(
				String.format("hp{%s},speed{%s},track{%s}",hp,speed,track),
				screen.getX(),
				screen.getY()
		);*/
		if(getSpeed() < 15) {
			gc.fillRect(screen.getX(), screen.getY(), 0.5 * gridDim.getX(), 0.5 * gridDim.getY());
		}
		else {
			gc.fillOval(screen.getX(), screen.getY(), 0.75 * gridDim.getX(), 0.75 * gridDim.getY());
		}
	}

	@Override
	public void tick(double dt) {
		var target = track.path[trackIndex];
		var deltaS = target.subtract(pos);
		var dist = deltaS.magnitude();
		if(dist < 0.01) {
			trackIndex += 1;
			if(trackIndex >= track.path.length) {
				System.out.println("Monster entered");
				this.markDestroy();
			}
			return;
		}
		pos = pos.add(deltaS.multiply(0.05*speed*dt / dist));
	}
}
