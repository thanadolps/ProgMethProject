package core;

import core.timing.Interval;
import entity.base.Entity;
import entity.base.monster;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import level.Level;
import level.Level1;
import level.spawner.Spawner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game implements Draw, Tick {
    Level currentLevel;
    Spawner activeSpawner;
    ArrayList<monster> monsters = new ArrayList<>();
    /**
     * <p>
     * Hashmap which map tile position to set of all active monster in that tile.
     * Monster with position m are within tile position t if floor(m.x) == t.x and floor(m.y) == t.y.
     * </p>
     *
     * <p>
     * In game, {@link Game#tick} (monsters.forEach part) and {@link Game#addMonster} method actively update this field
     * to match actual position of monster every frame.
     * </p>
     */
    HashMap<Pair<Integer, Integer>, HashSet<monster>> monstersMap = new HashMap<>();

    public Game() {
        try {
            currentLevel = new Level1();
            System.out.println(currentLevel);
            activeSpawner = currentLevel.nextSpawner();
            activeSpawner.setOnSpawn(this::addEntity);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GraphicsContext gc, double dt) {
        currentLevel.getTileGrid().draw(gc, dt);
        monsters.forEach(monster -> monster.draw(gc, dt));
    }

    @Override
    public void tick(double dt) {
        if(activeSpawner.isDone()) {
            System.out.println("DONE");
            activeSpawner = currentLevel.nextSpawner();
            activeSpawner.setOnSpawn(this::addEntity);
        }
        activeSpawner.tick(dt);

        monsters.forEach(monster -> {
            if(monster.isDestroyed()) {
                return;
            }

            // Position before tick
            var pos1 = new Pair<>((int)monster.getX(), (int)monster.getY());
            monster.tick(dt);

            // if it's destroyed, remove this monster from monstersMap.
            if(monster.isDestroyed()) {
                monstersMap.get(pos1).remove(monster);
                return;
            }

            // Position after tick
            var pos2 = new Pair<>((int)monster.getX(), (int)monster.getY());
            // Change this monster location in monstersMap from pos1 to pos2 (if pos1 != pos2)
            if(!pos1.equals(pos2)) {
                monstersMap.get(pos1).remove(monster);
                monstersMap.compute(pos2, (k, v) -> {
                    if(v == null) {
                        return new HashSet<>();
                    }
                    v.add(monster);
                    return v;
                });
            }
        });

        // TODO: decrease freq of remove
        monsters.removeIf(Entity::isDestroyed);
    }

    public void addEntity(Entity entity) {
        System.out.println("Adding " + entity);
        if(entity instanceof monster) {
            this.addMonster((monster)(entity));
        }
    }

    public void addMonster(monster monster) {
        monsters.add(monster);

        var pos = new Pair<>((int)monster.getX(), (int)monster.getY());
        monstersMap.putIfAbsent(pos, new HashSet<>());
        monstersMap.get(pos).add(monster);

        System.out.println(monstersMap);
    }

    /**
     * Get set of all active monster within pos (x,y). <br>
     * For more details see {@link core.Game#getMonstersAt(Pair)}
     *
     * @implNote This method is O(1)
     */
    public Set<monster> getMonstersAt(int x, int y) {
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
    public Set<monster> getMonstersAt(Pair<Integer, Integer> pos) {
        return monstersMap.get(pos);
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
