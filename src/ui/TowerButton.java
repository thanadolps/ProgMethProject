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

import java.util.function.Supplier;

public class TowerButton extends Button {
    ImageView imageView;
    Supplier<Tower> factory;

    public TowerButton(Supplier<Tower> factory, Image sprite) {
        this.factory = factory;
        this.imageView = new ImageView(sprite);

        this.setGraphic(imageView);
    }

    public TowerButton(Supplier<Tower> factory) {
        this(factory, factory.get().getIconSprite());
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

    public Supplier<Tower> getFactory() {
        return factory;
    }
}
