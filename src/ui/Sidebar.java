package ui;

import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    private final TowerSelectUI towerSelectUI;
    private final TowerInfoUI towerInfoUI;

    public Sidebar() {
        towerSelectUI = new TowerSelectUI();
        towerInfoUI = new TowerInfoUI();
        getChildren().addAll(towerSelectUI, towerInfoUI);
    }

    public TowerSelectUI getTowerSelectUI() {
        return towerSelectUI;
    }

    public TowerInfoUI getTowerInfoUI() {
        return towerInfoUI;
    }
}
