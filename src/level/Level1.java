package level;

import entity.base.monster;
import javafx.geometry.Point2D;
import level.spawner.PeriodicSpawner;
import level.spawner.SequentialSpawner;
import level.spawner.Spawner;
import utils.MapLoader;
import utils.TileGrid;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Level1 extends Level{
    TileGrid tileGrid = MapLoader.loadMap("level1");
    int count = 0;
    Track sampleTrack = new Track(new Point2D[]{
            new Point2D(0,1.5),
            new Point2D(6.5,1.5),
            new Point2D(6.5,6.5),
            new Point2D(1.5,6.5),
            new Point2D(1.5,5.5)
    });

    public Level1() throws IOException, URISyntaxException {
    }

    @Override
    public TileGrid getTileGrid() {
        return tileGrid;
    }

    @Override
    public Spawner nextSpawner() {
        return new SequentialSpawner(new Spawner[]{
            new PeriodicSpawner(() -> new monster(10, 20), 1, 5),
            new PeriodicSpawner(() -> new monster(20, 13), 1, 5)
        }).setTrack(sampleTrack);
    }
}
