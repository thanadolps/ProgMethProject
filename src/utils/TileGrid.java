package utils;

import core.Draw;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileGrid implements Draw {
    Image[][] sprites;
    boolean[][] towerPlaceable;
    TilesProp tilesProps;

    public TileGrid(String[][] tile, String[][] tile_prop) {
        int h = tile.length;
        int w = h>0?tile[0].length:0;
        sprites = new Image[h][w];
        towerPlaceable = new boolean[h][w];
        tilesProps = new TilesProp(tile_prop);

        for (int y = 0; y < tile.length; y++) {
            for (int x = 0; x < tile[0].length; x++) {
                sprites[y][x] = tilesProps.getSprite(tile[y][x]);
                towerPlaceable[y][x] = tilesProps.getTowerPlaceable(tile[y][x]);
            }
        }

    }

    public int getIndexWidth() {
        return sprites.length>0?sprites[0].length:0;
    }

    public int getIndexHeight() {
        return sprites.length;
    }

    @Override
    public void draw(GraphicsContext gc, double dt) {
        var canvas = gc.getCanvas();
        var w = canvas.getWidth();
        var h = canvas.getHeight();

        var dw = w / getIndexWidth();
        var dh = h / getIndexHeight();

        for (int i = 0; i < getIndexWidth(); i++) {
            for (int j = 0; j < getIndexHeight(); j++) {
                double x = dw*i;
                double y = dh*j;
                gc.drawImage(
                        sprites[j][i],
                        x,
                        y,
                        dw,
                        dh
                );
            }
        }
    }

    public boolean isTowerPlaceable(int x, int y) {
        return towerPlaceable[y][x];
    }
}
