package entity.game.experiment;

import core.Main;
import entity.base.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import utils.Sprites;
import utils.Utils;

import java.util.Optional;

public class TestTower2 extends Tower {
    public TestTower2(int x, int y) {
        super(0,0,0,x,y);
    }

    @Override
    public Optional<Tower> get_upgrade_lsh() {
        return Optional.empty();
    }

    @Override
    public Optional<Tower> get_upgrade_rsh() {
        return Optional.empty();
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
            if(m.getBaseSpeed() > 15) {
                m.die();
            }
        }
    }

    @Override
    public void drawOverlay(Pair<Integer, Integer> pos, GraphicsContext gc, double dt) {
        super.drawOverlay(pos, gc, dt);
        var px = Utils.grid2pixel(pos.getKey()-1, pos.getValue());
        var gridDim = Utils.getGridPixelDimension();

        gc.save();

        gc.setGlobalAlpha(0.5);
        gc.setFill(Color.RED);
        gc.fillRect(px.getX(), px.getY(), gridDim.getX(), gridDim.getY());

        gc.restore();
    }

    @Override
    public int upgradePrice_lsh() {
        return 0;
    }

    @Override
    public int upgraderPrice_rsh() {
        return 0;
    }

    @Override
    protected Image getSprite() {
        return Sprites.Tower2Sprite;
    }
}
