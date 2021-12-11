package entity.game.experiment;

import core.Main;
import entity.base.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.util.Pair;
import utils.Sprites;
import utils.Utils;

public class TestTower1 extends Tower {
    public TestTower1() {
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
        // Make slow monster to the right of the tower dead
        int x = pos.getKey();
        int y = pos.getValue();

        var monsters = Main.game.getMonstersAt(x+1, y);
        for (var m : monsters) {
            if(m.getSpeed() < 15) {
                m.die();
            }
        }
    }

    @Override
    public void drawOverlay(Pair<Integer, Integer> pos, GraphicsContext gc, double dt) {
        super.drawOverlay(pos, gc, dt);
        var px = Utils.grid2pixel(pos.getKey()+1, pos.getValue());
        var gridDim = Utils.getGridPixelDimension();

        gc.save();

        gc.setGlobalAlpha(0.5);
        gc.setFill(Color.RED);
        gc.fillRect(px.getX(), px.getY(), gridDim.getX(), gridDim.getY());

        gc.restore();
    }

    @Override
    protected Image getSprite() {
        return Sprites.TowerSprite;
    }
}
