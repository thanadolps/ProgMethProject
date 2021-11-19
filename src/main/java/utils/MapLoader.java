package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class MapLoader {
    public static TileGrid loadMap(String levelName) throws IOException {
        var levelFolder = MapLoader.class.getResource("../map/" + levelName);
        return loadMap(new File(levelFolder.getFile()));
    }

    public static TileGrid loadMap(File mapFolder) throws IOException {
        var p = mapFolder.getPath();
        var tile = Utils.parseTsv(new File(p + "/tile.tsv").toPath());
        var tile_prop = Utils.parseTsv(new File(p + "/tile_prop.tsv").toPath());
        return new TileGrid(tile, tile_prop);
    }
}
