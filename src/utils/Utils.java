package utils;

import core.Main;
import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static String[][] parseTsv(Path path) throws IOException {
        return Files
                .lines(path)
                .map(line -> line.split("\\s+"))
                .toArray(String[][]::new);
    }

    public static Point2D grid2pixel(double x, double y) {
        var gridDim = getGridPixelDimension();

        return new Point2D(x*gridDim.getX(), y*gridDim.getY());
    }

    public static Point2D grid2pixel(Point2D pos) {
        return grid2pixel(pos.getX(), pos.getY());
    }

    public static Point2D pixel2grid(double x, double y) {
        var gridDim = getGridPixelDimension();

        return new Point2D(x/gridDim.getX(), y/gridDim.getY());
    }

    public static Point2D pixel2grid(Point2D pos) {
        return pixel2grid(pos.getX(), pos.getY());
    }

    private static Point2D GridDim;

    public static Point2D getGridPixelDimension() {
        if(GridDim != null) {
            return GridDim;
        }

        var grid = Main.game.getCurrentLevel().getTileGrid();
        double w = Main.canvas.getWidth();
        double h = Main.canvas.getHeight();

        double gridW = w/grid.getIndexWidth();
        double gridH = h/grid.getIndexHeight();

        GridDim = new Point2D(gridW, gridH);
        return GridDim;
    }

    public static Point2D pair2point(Pair<Integer, Integer> pair) {
        return new Point2D(pair.getKey(), pair.getValue());
    }
}
