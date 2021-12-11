package utils;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class InputUtils {
    public final MouseTrack mouse = new MouseTrack();

    public static class MouseTrack {
        private Point2D gridPos = new Point2D(0, 0);

        private void updatePixelPos(double x, double y) {
            this.gridPos = Utils.pixel2grid(new Point2D(x, y));
        }

        public Point2D getPixelPos() {
            return Utils.grid2pixel(gridPos);
        }
        public Point2D getGridPos() {
            return this.gridPos;
        }
    }

    public InputUtils(Canvas canvas) {
        canvas.setOnMouseMoved(mouseEvent -> mouse.updatePixelPos(mouseEvent.getX(), mouseEvent.getY()));
    }
}
