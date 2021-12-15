package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
