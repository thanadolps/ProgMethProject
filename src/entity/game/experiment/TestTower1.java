package entity.game.experiment;

import core.Main;
import entity.base.Bullets;
import entity.base.BulletsType;
import entity.base.Monster;
import entity.base.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.util.Pair;
import utils.Sprites;
import utils.Utils;

import java.util.Optional;

public class TestTower1 extends Tower {
    public TestTower1() {
        super(0,0,0,0,0);
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
        // TODO Auto-generated method stub
        BulletsType type = this.getType();
        Monster m = findMonster();
        if (m == null)
            return;
        Bullets b = new Bullets(this.getX(), this.getY(), this.getAttack(), type, m);
        // ต้องใช้ tick ไหม
        Main.game.addBullet(b);
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
                attack();
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
    public int upgradePrice_lsh() {
        return 0;
    }

    @Override
    public int upgraderPrice_rsh() {
        return 0;
    }

    @Override
    protected Image getSprite() {
        return Sprites.TowerSprite;
    }
}
