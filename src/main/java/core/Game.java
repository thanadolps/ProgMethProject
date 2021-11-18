package core;

import javafx.scene.canvas.GraphicsContext;
import utils.MapLoader;
import utils.TileGrid;

import java.io.IOException;

public class Game implements Draw, Tick {

    TileGrid grid;

    public Game() {
        try {
            grid = MapLoader.loadMap("level1");
        } catch (IOException e) {
            System.err.println("Failed loading level: level1");
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GraphicsContext gc, double dt) {
        grid.draw(gc, dt);
    }

    @Override
    public void tick(double dt) {

    }
}
