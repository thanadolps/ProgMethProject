package level.spawner;

import core.timing.Interval;
import entity.base.Monster;
import javafx.geometry.Point2D;
import level.Track;

import java.util.function.Supplier;

public class PeriodicSpawner extends Spawner {
    private final Interval timer;
    private final Supplier<Monster> factory;
    private int limit = -1;
    private int spawnCount = 0;
    
    public PeriodicSpawner(Supplier<Monster> factory, double interval) {
        this.factory = factory;
        this.timer = new Interval(interval);
    }

    public PeriodicSpawner(Supplier<Monster> factory, double interval, int limit) {
        this(factory, interval);
        this.limit = limit;
    }


    @Override
    public boolean isDone() {
        return (limit > 0) && (spawnCount >= limit);
    }

    @Override
    public void tick(double dt) {
        timer.tick(dt, (p) -> {
                    var mon = this.factory.get();
                    this.onSpawn.accept(mon);
                    spawnCount += 1;
                }
        );
    }

    public int getSpawnCount() {
        return spawnCount;
    }
}
