package level.spawner;

import core.Tick;
import entity.base.monster;
import level.Track;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Spawner implements Tick {
    Consumer<monster> onSpawn;
    Track track;

    public void setOnSpawn(Consumer<monster> onSpawn) {
        this.onSpawn = (monster) -> {
            if(this.track != null) {
                monster.setTrack(this.track);
            }
            onSpawn.accept(monster);
        };
    }

    public Spawner setTrack(Track track) {
        this.track = track;
        return this;
    }

    public abstract boolean isDone();
}
