package ui;

import core.Main;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;
import logic.Towers;

public class TowerInfoUI extends TextFlow {
    Text mainText;
    Button deleteBtn;

    int towerX;
    int towerY;

    public TowerInfoUI() {
        mainText = new Text();
        deleteBtn = new Button("DELETE");
        getChildren().addAll(mainText, deleteBtn);
        unseeTower();
    }

    public void seeTower(int x, int y) {
        setVisible(true);
        towerX = x;
        towerY = y;

        Towers towers = Main.game.getTowers();
        var _tower = towers.getTower(x, y);
        if(_tower.isEmpty()) {
            unseeTower();
            return;
        }
        var tower = _tower.get();

        mainText.setText(tower.toString());
        deleteBtn.setOnAction(ev -> {
            towers.deleteTower(x, y);
            unseeTower();
        });
    }

    public void unseeTower() {
        setVisible(false);
    }
}
