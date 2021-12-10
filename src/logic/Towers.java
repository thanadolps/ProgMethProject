package logic;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import entity.base.Bullets;
import entity.base.tower;
import javafx.geometry.Point2D;
import javafx.util.Pair;

public class Towers {

	private final ArrayList<ArrayList<tower>> arrayGrid = new ArrayList<ArrayList<tower>>();

	public Towers() {
		makeMap();
		// just for testing
		setTower(5,3, new entity.game.type1(1,1,1, 3, 0));
	}

	public void makeMap() {
		for ( int i = 0 ; i<50 ; i++) {
			ArrayList<tower> t = new ArrayList<>();
			arrayGrid.add(t);
			for ( int j = 0 ; j<50 ; j++) {
				arrayGrid.get(i).add(null);
			}
		}
	}
	
	public void setTower(int x , int y, tower tower) {
		getRow(y).set(x, tower);
	}

	public tower getTower(int x, int y, tower tower) {
		return getRow(y).get(x);
	}

	public void deleteTower(int x, int y) {
		setTower(x, y, null);
	}

	public ArrayList<tower> getRow(int y) {
		return arrayGrid.get(y);
	}

	public ArrayList<ArrayList<tower>> asArrayList() {
		return arrayGrid;
	}

	public void iterateTower(BiConsumer<Pair<Integer, Integer>, tower> f) {
		for (int j = 0; j < arrayGrid.size(); j++) {
			var row = arrayGrid.get(j);
			for (int i = 0; i < row.size(); i++) {
				var tower = row.get(i);
				if(tower != null) {
					f.accept(new Pair<>(i, j), tower);
				}
			}
		}
	}

	public int getWidth() {
		var row = getRow(0);
		return row == null ? 0: row.size();
	}

	public int getHeight() {
		return arrayGrid.size();
	}
}

