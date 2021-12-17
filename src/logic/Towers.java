package logic;

import entity.base.Tower;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Towers {

	private final ArrayList<ArrayList<Tower>> arrayGrid = new ArrayList<ArrayList<Tower>>();
	private Pair<Integer, Integer> selectedPosition = null;

	public Towers() {
		makeMap();
	}

	public void makeMap() {
		for ( int i = 0 ; i<50 ; i++) {
			ArrayList<Tower> t = new ArrayList<>();
			arrayGrid.add(t);
			for ( int j = 0 ; j<50 ; j++) {
				arrayGrid.get(i).add(null);
			}
		}
	}

	// Tower operation
	public void setTower(int x , int y, Tower tower) {
		getRow(y).set(x, tower);
		iterateTower((p, t) -> t.recalculateStrengthBuff());
	}

	public void deleteTower(int x, int y) {
		setTower(x, y, null);
		iterateTower((p, t) -> t.recalculateStrengthBuff());
	}

	// Querying and iterating
	public Optional<Tower> getTower(int x, int y) {
		return Optional.ofNullable(getRow(y).get(x));
	}

	public ArrayList<Tower> getRow(int y) {
		return arrayGrid.get(y);
	}

	public ArrayList<ArrayList<Tower>> asArrayList() {return arrayGrid; }

	public void iterateTower(BiConsumer<Pair<Integer, Integer>, Tower> f) {
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

	// Selecting system

	public boolean select(int x, int y) {
		if(getTower(x, y).isEmpty() || getSelectedPosition().equals(Optional.of(new Pair<>(x, y)))) {
			selectedPosition = null;
			return false;
		}
		else {
			selectedPosition = new Pair<>(x, y);
			return true;
		}
	}

	public Optional<Pair<Integer, Integer>> getSelectedPosition() {
		return Optional.ofNullable(selectedPosition);
	}
}

