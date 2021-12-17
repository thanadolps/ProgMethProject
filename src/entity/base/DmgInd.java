package entity.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Utils;

public class DmgInd extends Entity {
    double x;
    double y;
    int dmg;

    double timer = 0;

    DmgInd(double x, double y, int dmg) {
        this.x = x;
        this.y = y;
        this.dmg = dmg;
    }


    @Override
    public void draw(GraphicsContext gc, double dt) {
        var px = Utils.grid2pixel(x, y);
        gc.setFill(Color.DARKRED);
        gc.fillText(""+dmg, px.getX(), px.getY());
    }

    @Override
    public void tick(double dt) {
        y -= 0.4*dt;
        timer += dt;
        if(timer > 0.5) {
            this.markDestroy();
        }
    }
}
