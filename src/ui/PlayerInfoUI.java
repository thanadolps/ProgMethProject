package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Simulation;

public class PlayerInfoUI extends VBox {
    Text round, lifepoint,maxhp,myhp,money;

    public PlayerInfoUI() {
        round = new Text();
        lifepoint = new Text();
        maxhp = new Text();
        myhp = new Text();
        money = new Text();

        HBox empty3 = new HBox();
        empty3.getChildren().add(new Text());

        HBox empty4 = new HBox();
        empty4.getChildren().add(new Text());

        HBox hb3 = new HBox(50);
        hb3.setAlignment(Pos.CENTER);
        hb3.getChildren().add(round);

        HBox hb4 = new HBox(50);
        hb4.setAlignment(Pos.CENTER);
        hb4.getChildren().add(lifepoint);

        HBox hb5 = new HBox(50);
        hb5.setAlignment(Pos.CENTER);
        hb5.getChildren().addAll(lifepoint,myhp,maxhp);

        HBox hb6 = new HBox(50);
        hb6.setAlignment(Pos.CENTER);
        hb6.getChildren().add(money);

        getChildren().addAll(hb3, empty3, hb5, empty4, hb6);

        round.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        maxhp.setStyle("-fx-font-size: 32px;-fx-font-family:\"Arial Black\";-fx-fill: #006400;");
        maxhp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        myhp.setStyle("-fx-font-size: 32px;-fx-font-family:\"Arial Black\";-fx-fill: #8B0000;");
        myhp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        lifepoint.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        money.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));

        refresh();
    }

    public void refresh() {
        round.setText("round = " + Simulation.getRound());
        maxhp.setText("/150");
        myhp.setText(logic.Simulation.getLifepoint()+"");
        lifepoint.setText("LifePoint = ");
        money.setText("Current Money = "+logic.Simulation.getMoney());
    }
}
