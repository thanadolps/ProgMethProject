package ui;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TitleScreen extends HBox {
    public TitleScreen() {
        var box = new VBox();

        var title = new Text("Welcome");

        var restartBtn = new Button("Level 1");
        restartBtn.setOnAction(ev -> Main.restart());

        var exitBtn = new Button("Exit");
        exitBtn.setOnAction(ev -> {
            System.out.println("Game exited by user");
            System.exit(0);
        });

        box.getChildren().addAll(title, restartBtn, exitBtn);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        getChildren().add(box);

        setMargin(box, new Insets(16, 16, 16, 16));
    }
}
