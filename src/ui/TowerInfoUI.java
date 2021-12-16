package ui;

import core.Main;
import entity.base.Tower;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Simulation;
import logic.Towers;

import java.util.function.Consumer;

public class TowerInfoUI extends VBox {
	GridPane grid;
    Text mainText;
    //Text description;
    Text attack,price,speedattack,bulletstype,range,level;
    Button sellBtn;
    Button upgradeLeft = new Button();
    Button upgradeRight = new Button();
    Text vac,vac2,vac3,vac4 ;

    int towerX;
    int towerY;

    public TowerInfoUI() {   	
        mainText = new Text();
        //description = new Text();
        attack = new Text();
        price = new Text();
        speedattack= new Text();
        bulletstype = new Text();
        range = new Text();
        level = new Text();
        sellBtn = new Button("SELL");
        grid = new GridPane();
        vac = new Text();
        vac2 = new Text();
        vac3 = new Text();
        vac4 = new Text();

        HBox empty = new HBox();
        empty.getChildren().add(vac);
        
        HBox empty2 = new HBox();
        empty2.getChildren().add(vac2);
        
        upgradeLeft.setStyle("-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkgreen;");
        
        upgradeRight.setStyle("-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkgreen;");
        
        sellBtn.setStyle("-fx-text-fill: white; -fx-font-weight: bold;-fx-font-family: \"Arial Narrow\"; -fx-background-color: darkred;");
        
        HBox hb = new HBox(50);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll( upgradeLeft, upgradeRight, sellBtn);
        
        HBox hb2 = new HBox(50);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().add(mainText);
      
        grid.setAlignment(Pos.CENTER); 
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(attack, 0, 0 );
        grid.add(speedattack, 2,0);
        grid.add(level, 0,1);
        grid.add(price, 2,1);
        grid.add(range, 0,2);
        grid.add(bulletstype, 2,2);
        
        getChildren().add(empty2);
        getChildren().add(hb2);
        getChildren().add(grid);
        getChildren().add(hb);
        getChildren().add(empty);
        //getChildren().addAll(upgradeLeft, upgradeRight);
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

        //mainText.setText(tower.toString());
        mainText.setText(Tower.getName(tower));
        mainText.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        
        
        //description.setText("Tower description Na.");
        attack.setText("Attack = "+tower.getBaseAttack()+"+"+tower.getExtraAttack());
        attack.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        speedattack.setText("SpeedAttack = "+tower.getSpeedatk());
        speedattack.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        price.setText("Price = "+tower.getPrice());
        price.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        range.setText("Range = "+tower.getR());
        range.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        level.setText("level = "+tower.getLevel());
        level.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        bulletstype.setText("BulletsType = "+tower.getType());
        bulletstype.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        sellBtn.setOnAction(ev -> {
            tower.sell();
            unseeTower();
        });

        updateUpgradeBtn(
                upgradeLeft,
                ev -> tower.upgrade_lsh(tower.upgradePrice_lsh()),
                x, y, tower.upgradePrice_lsh(),
                tower.get_upgrade_lsh().orElse(null)
        );

        updateUpgradeBtn(
                upgradeRight,
                ev -> tower.upgrade_rsh(tower.upgraderPrice_rsh()),
                x, y, tower.upgraderPrice_rsh(),
                tower.get_upgrade_rsh().orElse(null)
        );
    }

    private void updateUpgradeBtn(Button ele, Consumer<ActionEvent> upgradeAction, int x, int y, int price, Tower new_tower) {
        // No upgrade available case
        if(new_tower == null) {
            ele.setText("Upgrade Maxed out");
            ele.setDisable(true);
            return;
        }

        String upgradeMsg = "UPGRADE to\n" + Tower.getName(new_tower) + "\n";

        // Not enough money case
        if(Simulation.getMoney() < price) {
            ele.setText(upgradeMsg + "Insufficient fund: " + price);
            ele.setDisable(true);
            return;
        }

        // Normal case
        ele.setDisable(false);
        ele.setText(upgradeMsg + price);
        ele.setOnAction(ev -> {
            upgradeAction.accept(ev);
            seeTower(x, y);
        });
    }

    public void refresh() {
        if(towerX >=0 && towerY >= 0)
            seeTower(towerX, towerY);
    }

    public void unseeTower() {
        towerX = -1;
        towerY = -1;
        setVisible(false);
    }
}
