package entity.game;

import entity.base.Monster;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import utils.Sprites;
import utils.Utils;

public class Soldier extends Monster {

    public Soldier(int hp , double baseSpeed) {
        super(hp, baseSpeed);
    }

    public Soldier() {
        super(10, 1);
    }


    @Override
    public Image getSprite() {
        return Sprites.Solider;
    }

    @Override
    public double getHitBoxRadius() {
        return 0.25;
    }

    @Override
    public int getBounty() {
        return 10;
    }
}
