package entity.game;

import core.Main;
import entity.base.Monster;
import javafx.scene.image.Image;
import utils.Sprites;

import java.lang.Math;

public class Boom extends type3 {

		public Boom(int speedatk, int attack, int price, int x , int y ) {
			super(speedatk, attack, price, x ,y);
			setType(null);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void attack() {
			// TODO Auto-generated method stub
			for ( Monster m : Main.game.getMonsters() ) {
				double dx = this.getX()-m.getX();
				double dy = this.getY()-m.getY();
				double r = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
				if ( r < this.getR() ) m.setHp(m.getHp()-100);
			}
		}

		@Override
		public Image getSprite() {
			return Sprites.TowerBoom;
		}
}
