package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MapLoader {
    public static TileGrid loadMap(String levelName) throws IOException {
        var tile = ClassLoader.getSystemResourceAsStream("map/" + levelName + "/tile.tsv");
        var tile_prop = ClassLoader.getSystemResourceAsStream("map/" + levelName + "/tile_prop.tsv");

        var tile_content = Utils.parseTsv(new String(tile.readAllBytes(), StandardCharsets.UTF_8));
        var tile_prop_content = Utils.parseTsv(new String(tile_prop.readAllBytes(), StandardCharsets.UTF_8));

        return new TileGrid(tile_content, tile_prop_content);
    }
}
