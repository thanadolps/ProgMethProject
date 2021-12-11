package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Optional;

public class TowerSelectUI extends TilePane {

    ObservableList<TowerButton> towerButtons = FXCollections.observableList(new ArrayList<>());
    Optional<Integer> selectingIndex = Optional.empty();

    public void addTowerButton(TowerButton towerButton) {
        int i = towerButtons.size();
        towerButton.setOnAction(ev -> selectingIndex = Optional.of(i));

        towerButtons.add(towerButton);
        this.getChildren().add(towerButton);
    }

    public Optional<TowerButton> getSelected() {
        return selectingIndex.map(i -> towerButtons.get(i));
    }
}
