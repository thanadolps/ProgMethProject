package core;

import core.timing.Interval;
import entity.base.Bullets;
import entity.base.Entity;
import entity.base.Monster;
import entity.base.Tower;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import level.Level;
import level.Level1;
import level.spawner.Spawner;
import logic.Towers;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Game implements Draw, Tick {
    Level currentLevel;
    Spawner activeSpawner;
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<Bullets> bullets = new ArrayList<>();
    Towers towers = new Towers();
    /**
     * <p>
     * Hashmap which map tile position to set of all active monster in that tile.
     * Monster with position m are within tile position t if floor(m.x) == t.x and floor(m.y) == t.y.
     * </p>
     *
     * <p>
     * In game, {@link Game#tickMonster} and {@link Game#addMonster} method actively update this field
     * to match actual position of monster every frame.
     * </p>
     */
    HashMap<Pair<Integer, Integer>, HashSet<Monster>> monstersMap = new HashMap<>();

    public Game() {
        try {
            currentLevel = new Level1();
            System.out.println(currentLevel);
            activeSpawner = currentLevel.nextSpawner();
            activeSpawner.setOnSpawn(this::addMonster);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GraphicsContext gc, double dt) {
        currentLevel.getTileGrid().draw(gc, dt);
        bullets.forEach(bullet -> {if(!bullet.isDestroyed()) bullet.draw(gc, dt);});
        monsters.forEach(monster -> monster.draw(gc, dt));
        this.drawTower(gc, dt);

        debugMonsterCount(gc);
    }

    private void drawTower(GraphicsContext gc, double dt) {
        towers.iterateTower((pos, tower) -> tower.draw(pos, gc, dt));
    }

    private void debugMonsterCount(GraphicsContext gc) {
        var grid = getCurrentLevel().getTileGrid();
        for (int j = 0; j < grid.getIndexHeight(); j++) {
            for (int i = 0; i < grid.getIndexWidth(); i++) {
                var px = Utils.grid2pixel(new Point2D(i, j));

                gc.fillText(Integer.toString(getMonstersAt(i, j).size()), px.getX(), px.getY());
            }
        }

        gc.fillText(Integer.toString(monsters.size()),  gc.getCanvas().getWidth()-24, 24);
    }

    @Override
    public void tick(double dt) {
        if(activeSpawner.isDone()) {
            System.out.println("DONE");
            activeSpawner = currentLevel.nextSpawner();
            activeSpawner.setOnSpawn(this::addMonster);
        }
        activeSpawner.tick(dt);

        this.tickTower(dt);
        bullets.forEach(bullet -> {if(!bullet.isDestroyed()) bullet.tick(dt);});
        this.tickMonster(dt);

        // TODO: decrease freq of remove
        monsters.removeIf(Entity::isDestroyed);
        bullets.removeIf(Entity::isDestroyed);
    }

    private void tickMonster(double dt) {
        monsters.forEach(monster -> {
            // Position before tick
            var pos1 = new Pair<>((int) monster.getX(), (int) monster.getY());

            if (monster.isDestroyed()) {
                monstersMap.get(pos1).remove(monster);
                return;
            }

            monster.tick(dt);
            // if it's destroyed, remove this monster from monstersMap.
            if (monster.isDestroyed()) {
                monstersMap.get(pos1).remove(monster);
                return;
            }

            // Position after tick
            var pos2 = new Pair<>((int) (monster.getX()), (int) (monster.getY()));
            // Change this monster location in monstersMap from pos1 to pos2 (if pos1 != pos2)
            if (!pos1.equals(pos2)) {
                monstersMap.get(pos1).remove(monster);
                monstersMap.compute(pos2, (k, v) -> {
                    if (v == null) {
                        v = new HashSet<>();
                    }
                    v.add(monster);
                    return v;
                });
            }
        });
    }

    private void tickTower(double dt) {
        towers.iterateTower((pos, tower) -> tower.tick(pos, dt));
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);

        var pos = new Pair<>((int)monster.getX(), (int)monster.getY());
        monstersMap.putIfAbsent(pos, new HashSet<>());
        monstersMap.get(pos).add(monster);
    }

    public void addBullet(Bullets bullet) {
        bullets.add(bullet);
    }

    /**
     * Get set of all active monster within pos (x,y). <br>
     * For more details see {@link core.Game#getMonstersAt(Pair)}
     *
     * @implNote This method is O(1)
     */
    public Set<Monster> getMonstersAt(int x, int y) {
        return this.getMonstersAt(new Pair<>(x, y));
    }

    /**
     * Get set of all active monster within pos (tile position).<br>
     * Monster's position are rounded down,
     * e.g. pos=(1,2) will get all monster with x in [1.0, 2.0) and y in [2.0, 3.0)
     *
     * @param pos Tile position
     * @return Set of monster in tile position pos
     * @see Game#monstersMap
     * @implNote This method is O(1)
     */
    public Set<Monster> getMonstersAt(Pair<Integer, Integer> pos) {
        return Objects.requireNonNullElseGet(monstersMap.get(pos), HashSet::new);
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
    
    

    public Towers getTowers() {
        return towers;
    }

    public ArrayList<Bullets> getBullets() {
        return bullets;
    }
}
