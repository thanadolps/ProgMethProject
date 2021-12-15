package ui;

import core.Main;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;
import logic.Towers;

public class TowerInfoUI extends TextFlow {
    Text mainText;
    Text description;
    Button deleteBtn;
    Button upgradeLeft = new Button();
    Button upgradeRight = new Button();

    int towerX;
    int towerY;

    public TowerInfoUI() {
        mainText = new Text();
        description = new Text();
        deleteBtn = new Button("DELETE");
        getChildren().addAll(mainText, deleteBtn);
        getChildren().addAll(upgradeLeft, upgradeRight);
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
        description.setText("Tower description Na.");
        deleteBtn.setOnAction(ev -> {
            towers.deleteTower(x, y);
            unseeTower();
        });

        upgradeLeft.setText("UPGRADE to TowerA");
        upgradeLeft.setOnAction(ev -> {
            System.out.println("Tower upgrade left da");
        });

        upgradeRight.setText("UPGRADE to TowerB");
        upgradeRight.setOnAction(ev -> {
            System.out.println("Tower upgrade right da");
        });
    }

    public void unseeTower() {
        setVisible(false);
    }
}
