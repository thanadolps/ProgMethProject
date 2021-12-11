package ui;

import entity.base.Tower;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public ImageView getImageView() {
        return imageView;
    }

    public Supplier<Tower> getFactory() {
        return factory;
    }
}
