package utils;

import entity.base.Bullets;
import javafx.scene.media.AudioClip;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Sound {
    public static AudioClip TowerPlace, BulletHit;

    static {
        try {
            TowerPlace = loadAudio("plant.wav");
            BulletHit = loadAudio("splat.wav");
        } catch (IOException e) {
            System.err.println("Fail to load audio.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static AudioClip loadAudio(String fileName) throws IOException {
        var file = ClassLoader.getSystemResource("audio/" + fileName);
        if (file == null) {
            throw new FileNotFoundException(Sound.class.getResource("..") + "/audio/" + fileName);
        }
        return new AudioClip(file.toString());
    }

}
