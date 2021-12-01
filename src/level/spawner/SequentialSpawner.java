package level.spawner;

import java.util.ArrayList;

public class SequentialSpawner extends Spawner {
    private final Spawner[] spawners;
    private int i = 0;

    public SequentialSpawner(Spawner[] spawners) {
        this.spawners = spawners;
        for (var spawner : spawners) {
            spawner.setOnSpawn(monster -> this.onSpawn.accept(monster));
        }
    }

    @Override
    public void tick(double dt) {
        if(isDone()) {
            return;
        }

        var spawner = spawners[i];
        spawner.tick(dt);

        if(spawner.isDone()) {
            i += 1;
        }
    }

    @Override
    public boolean isDone() {
        return i >= spawners.length;
    }
}
