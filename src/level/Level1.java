package level;

import entity.game.Boss;
import entity.game.Soldier;
import entity.game.SpeedSoldier;
import javafx.geometry.Point2D;
import level.spawner.EmptySpawner;
import level.spawner.PeriodicSpawner;
import level.spawner.SequentialSpawner;
import level.spawner.Spawner;
import logic.Simulation;
import utils.MapLoader;
import utils.TileGrid;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Level1 extends Level{
    TileGrid tileGrid = MapLoader.loadMap("level1");
    Track sampleTrack = new Track(new Point2D[]{
            new Point2D(0,1.5),
            new Point2D(6.5,1.5),
            new Point2D(6.5,6.5),
            new Point2D(0,6.5),
    });

    public Level1() throws IOException, URISyntaxException {
    }

    @Override
    public TileGrid getTileGrid() {
        return tileGrid;
    }

    @Override
    public Spawner nextSpawner() {
        var spawner = new ArrayList<Spawner>();
        for (int i = 0; i < 2; i++) {
            var amount = Math.exp(Simulation.getRound());
            spawner.add(new PeriodicSpawner(Soldier::new, 5/amount, (int) amount));
            spawner.add(new PeriodicSpawner(SpeedSoldier::new, 5/amount, (int) amount));
            spawner.add(new EmptySpawner(3));
        }
        spawner.add(new PeriodicSpawner(Boss::new, 1, Simulation.getRound()));
        spawner.add(new EmptySpawner(10));

        var spawnerArr = new Spawner[spawner.size()];
        spawnerArr = spawner.toArray(spawnerArr);

        return new SequentialSpawner(spawnerArr).setTrack(sampleTrack);
    }
}
