package utils;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class TilesProp {
    private final HashMap<String, Image> spritesMap = new HashMap<>();
    private final HashMap<String, Boolean> towerPlaceable = new HashMap<>();

    public TilesProp(String[][] tilePropSpec) {
        var header = tilePropSpec[0];
        for (int j = 1; j < tilePropSpec.length; j++) {
            var tile = tilePropSpec[j][0];
            for (int i = 1; i < header.length; i++) {
                String head = header[i];
                String data = tilePropSpec[j][i];

                switch (head) {
                    case "sprite":
                        var img = Sprites.getImage(data);

                        img.ifPresentOrElse(
                                _img -> spritesMap.put(tile, _img),
                                () -> {
                                    System.err.println("Fail to get sprite: " + data + ", sprite might not have been loaded or didn't exist");
                                }
                        );
                        break;
                    case "tower_placeable":
                        towerPlaceable.put(tile, Integer.parseInt(data) > 0);
                        break;
                }
            }
        }
    }

    public Image getSprite(String tile) {
        return spritesMap.get(tile);
    }

    public boolean getTowerPlaceable(String tile) {
        return towerPlaceable.get(tile);
    }
}
