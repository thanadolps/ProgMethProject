package core;

import javafx.scene.canvas.GraphicsContext;

public interface Draw {
    default void preDraw(GraphicsContext gc) {};
    void draw(GraphicsContext gc, double dt);
}
