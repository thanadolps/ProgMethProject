package main.java.entity.base;

import core.Draw;
import core.Tick;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity implements Draw, Tick {
    private boolean destroyed = false;

    protected void markDestroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
