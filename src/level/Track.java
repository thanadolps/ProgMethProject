package level;

import javafx.geometry.Point2D;

import java.util.Arrays;

public class Track {
    // This class might be unnecessary, just use Point2D[] or ArrayList
    public Point2D[] path;

    public Track(Point2D[] path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return Arrays.toString(path);
    }
}
