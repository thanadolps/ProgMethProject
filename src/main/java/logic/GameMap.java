package main.java.logic;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.entity.base.Bullets;
import main.java.entity.base.tower;

public class GameMap {

	private static ArrayList<ArrayList<tower>> maptower = new ArrayList<ArrayList<tower>>();
	private static ObservableList<Bullets> bullets = FXCollections.observableArrayList();
	//ไม่รู้ว่าmonsterจะเก็บไว้ยังไงดี

	public static void makeMap() {
		for ( int i = 0 ; i<50 ; i++) {
			ArrayList<tower> t = new ArrayList<>();
			maptower.add(t);
			for ( int j = 0 ; j<50 ; j++) {
				maptower.get(i).add(null);
			}
		}
	}
	
	public static void addTower(int x , int y, tower tower) {
		ArrayList<tower> pos = maptower.get(x);
		pos.set(y, tower);
	}
	
	public static void delete(int x ,int y) {
		ArrayList<tower> pos = maptower.get(x);
		pos.set(y,null);
	}
	
	public static void shot(Bullets b) {
		bullets.add(b);
	}
	
	public static void deleteBullets(Bullets b) {
		for ( int i = 0 ; i<bullets.size() ; i++ ) {
			if ( bullets.get(i) == b ) bullets.remove(i);
		}
	}
}

