package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class MapLoader {
    public static TileGrid loadMap(String levelName) throws IOException, URISyntaxException {
        var levelFolder = ClassLoader.getSystemResource("map/"+levelName);
		return loadMap(Paths.get(levelFolder.toURI()));
    }

    public static TileGrid loadMap(Path mapFolder) throws IOException {
        var tile = Utils.parseTsv(mapFolder.resolve("tile.tsv"));
        var tile_prop = Utils.parseTsv(mapFolder.resolve("tile_prop.tsv"));
        return new TileGrid(tile, tile_prop);
    }
}
