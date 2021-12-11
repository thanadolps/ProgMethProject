package entity.game.experiment;

import core.Main;
import entity.base.Tower;
import javafx.scene.image.Image;
import javafx.util.Pair;
import utils.Sprites;

public class TestTower2 extends Tower {
    public TestTower2() {
        super(0,0,0);
    }

    @Override
    public boolean upgrade_lsh(int price) {
        return false;
    }

    @Override
    public boolean upgrade_rsh(int price) {
        return false;
    }

    @Override
    public void attack() {

    }

    @Override
    public void tick(Pair<Integer, Integer> pos, double dt) {
        // Testing code
        // Make fast monster to the left of the tower dead
        int x = pos.getKey();
        int y = pos.getValue();

        var monsters = Main.game.getMonstersAt(x-1, y);
        for (var m : monsters) {
            if(m.getSpeed() > 15) {
                m.die();
            }
        }
    }

    @Override
    protected Image getSprite() {
        return Sprites.Tower2Sprite;
    }
}
