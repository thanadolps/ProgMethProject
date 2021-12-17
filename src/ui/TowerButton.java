package ui;

import entity.base.Tower;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.function.BiFunction;

public class TowerButton extends Button {
    ImageView imageView;
    BiFunction<Integer, Integer, Tower> factory;

    public TowerButton(BiFunction<Integer, Integer, Tower> factory, Image sprite) {
        this.factory = factory;
        this.imageView = new ImageView(sprite);

        this.imageView.setFitWidth(100);
        this.imageView.setFitHeight(100);
        this.setGraphic(imageView);
    }

    public TowerButton(BiFunction<Integer, Integer, Tower> factory) {
        this(factory, factory.apply(0, 0).getIconSprite());
    }

    public void highlight() {
        setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void unhighlight() {
        setBackground(Background.EMPTY);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public BiFunction<Integer, Integer, Tower> getFactory() {
        return factory;
    }
}
