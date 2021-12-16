package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    private final TowerSelectUI towerSelectUI;
    private final TowerInfoUI towerInfoUI;
    private final PlayerInfoUI playerInfoUI;

    public Sidebar() {
        towerSelectUI = new TowerSelectUI();
        towerInfoUI = new TowerInfoUI();

        playerInfoUI = new PlayerInfoUI();
        playerInfoUI.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setVgrow(playerInfoUI, Priority.ALWAYS);
        VBox.setMargin(playerInfoUI, new Insets(32,32,32,32));

        getChildren().addAll(towerSelectUI, towerInfoUI, playerInfoUI);
    }

    public void refresh() {
        getTowerInfoUI().refresh();
        getPlayerInfoUI().refresh();
    }

    public TowerSelectUI getTowerSelectUI() {
        return towerSelectUI;
    }

    public TowerInfoUI getTowerInfoUI() {
        return towerInfoUI;
    }

    public PlayerInfoUI getPlayerInfoUI() {
        return playerInfoUI;
    }
}
