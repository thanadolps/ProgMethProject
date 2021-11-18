package core.timing;

import core.Draw;
import core.Tick;
import javafx.scene.canvas.GraphicsContext;

public class FpsCounter implements Draw, Tick {
    Interval fpsTimer = new Interval(5);
    double lastFps = 0d;
    int frameCount = 0;
    double x, y;

    public FpsCounter(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext gc, double dt) {
        gc.fillText("FPS (over 5s): " + Math.round(lastFps), this.x, this.y);
    }

    @Override
    public void tick(double dt) {
        frameCount += 1;
        fpsTimer.tick(
                dt,
                (pe) -> {
                    this.lastFps = frameCount/pe;
                    frameCount = 0;
                }
        );
    }
}
