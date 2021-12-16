package utils;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class Sprites {
    public static Image GrassASprite, GrassBSprite, SandSprite, TowerSprite, Tower2Sprite,
            TowerType1, TowerFire, TowerIce,
            TowerType2, TowerStrength, TowerFarm;
    private static final HashMap<String, Image> imagesByName = new HashMap<>();

    static {
        try {
            GrassASprite = Sprites.loadImage("grass_tile_1.png");
            GrassBSprite = Sprites.loadImage("grass_tile_3.png");
            SandSprite = Sprites.loadImage("sand_tile.png");
            TowerSprite = Sprites.loadImage("tower.png");
            Tower2Sprite = Sprites.loadImage("tower2.png");

            TowerType1 = Sprites.loadImage("tower_1.png");
            TowerIce = Sprites.loadImage("tower_ice.png");
            TowerFire = Sprites.loadImage("tower_fire.png");

            TowerType2 = Sprites.loadImage("tower_2.png");
            TowerStrength = Sprites.loadImage("tower_strength.png");
            TowerFarm = Sprites.loadImage("tower_farm.png");

            System.out.println("Sprite Loaded:");
            imagesByName.forEach((k, v) -> {
                System.out.print(k+",");
            });
            System.out.println();

        } catch (IOException e) {
            System.err.println("Fail to load sprite.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Image loadImage(String fileName) throws IOException {
        var name = fileName.split("\\.", 2)[0];
        var loadedImage = getImage(name);
        if(loadedImage.isPresent()) {
            return loadedImage.get();
        }

        var file = Sprites.class.getResource("../sprite/" + fileName);
        if (file == null) {
            throw new FileNotFoundException(Sprites.class.getResource("..") + "/sprite/" + fileName);
        }
        var img = new Image(file.openStream());
        imagesByName.put(name, img);
        return img;
    }

    /**
     * Get loaded image corresponding to the file name. the image must be load use `loadImage` before.
     *
     * @param fileName sprite's filename (without extension)
     * @return loaded `Image` corresponding to the fileName, if exist
     */
    public static Optional<Image> getImage(String fileName) {
        return Optional.ofNullable(imagesByName.get(fileName));
    }
}
