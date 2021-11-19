package utils;

import core.Main;
import javafx.geometry.Point2D;

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

    public static Point2D grid2pixel(Point2D pos) {
        var gridDim = getGridPixelDimension();

        return new Point2D(pos.getX()*gridDim.getX(), pos.getY()*gridDim.getY());
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
}
