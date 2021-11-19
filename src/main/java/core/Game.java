package core;

import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import level.Level;
import level.Level1;
import level.spawner.Spawner;

import java.io.IOException;
import java.util.ArrayList;

public class Game implements Draw, Tick {
    Level currentLevel;
    Spawner activeSpawner;
    ArrayList<Entity> entities = new ArrayList<>();

    public Game() {
        try {
            currentLevel = new Level1();
            activeSpawner = currentLevel.nextSpawner();
            activeSpawner.setOnSpawn(this::addEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GraphicsContext gc, double dt) {
        currentLevel.getTileGrid().draw(gc, dt);
        entities.forEach(entity -> entity.draw(gc, dt));
    }

    @Override
    public void tick(double dt) {
        if(activeSpawner.isDone()) {
            System.out.println("DONE");
            activeSpawner = currentLevel.nextSpawner();
            activeSpawner.setOnSpawn(this::addEntity);
        }
        activeSpawner.tick(dt);

        entities.forEach(entity -> {
            if(!entity.isDestroyed()) {
                entity.tick(dt);
            }
        });
        // TODO: decrease freq of remove
        entities.removeIf(Entity::isDestroyed);
    }

    public void addEntity(Entity entity) {
        System.out.println("Adding " + entity);
        entities.add(entity);
    }


    public Level getCurrentLevel() {
        return currentLevel;
    }
}
