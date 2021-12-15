package entity.base;

import core.Draw;
import core.Tick;

public abstract class Entity implements Draw, Tick {
    private boolean destroyed = false;

    protected void markDestroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
