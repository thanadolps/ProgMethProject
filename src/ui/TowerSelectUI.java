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
        towerButton.setOnAction(ev -> handleButtonClick(i));
        towerButton.unhighlight();
        towerButtons.add(towerButton);
        this.getChildren().add(towerButton);
    }

    private void handleButtonClick(int i) {
        // if click on button which is already selected
        boolean isThisButtonSelected = selectingIndex.equals(Optional.of(i));
        if(isThisButtonSelected) {
            deselect();
        }
        else {
            select(i);
        }
    }

    public void deselect() {
        getSelected().ifPresent(TowerButton::unhighlight);
        selectingIndex = Optional.empty();
    }

    public void select(int i) {
        deselect();
        selectingIndex = Optional.of(i);
        getSelected().ifPresent(TowerButton::highlight);
    }

    public Optional<TowerButton> getSelected() {
        return selectingIndex.map(i -> towerButtons.get(i));
    }
}
