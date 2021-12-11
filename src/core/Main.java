package core;

import core.timing.FpsCounter;
import core.timing.Interval;
import entity.game.type1;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.TowerButton;
import ui.TowerSelectUI;
import utils.InputUtils;
import utils.Sprites;

public class Main extends Application {
    public static Game game = new Game();
    public static Canvas canvas;
    public static InputUtils inputUtils;
    public static TowerSelectUI towerSelectUI;

    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = new HBox();
        canvas = new Canvas();
        towerSelectUI = new TowerSelectUI();

        setupUI();
        setupGraphics(canvas.getGraphicsContext2D());
        inputUtils = new InputUtils(canvas);

        root.getChildren().addAll(canvas, towerSelectUI);

        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower d' Fence");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void setupUI() {
        canvas.setWidth(960);
        canvas.setHeight(960);

        towerSelectUI.addTowerButton(new TowerButton(() -> new type1(0,0,0,0,0)));
    }

    private void setupGraphics(GraphicsContext gc) {
        double w = gc.getCanvas().getWidth();
        double h = gc.getCanvas().getHeight();
        gc.setFill(Color.BLACK);

        gc.getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.handleClick(mouseEvent));

        new AnimationTimer() {
            long prevNano = System.nanoTime();

            // 0.016 sec/frame = 60 frame/sec
            final Interval tickTimer = new Interval(0.016);
            final Interval drawTimer = new Interval(0.016);
            final FpsCounter fpsCounter = new FpsCounter(16,h-16);

            @Override
            public void handle(long now) {
                long dtNano = now - prevNano;
                this.prevNano = now;
                double dt = dtNano * 1e-9;

                drawTimer.tick(dt, (deltaTime) -> {
                    gc.clearRect(0,0, w, h);

                    game.preDraw(gc);
                    fpsCounter.preDraw(gc);

                    game.draw(gc, deltaTime);
                    fpsCounter.draw(gc, deltaTime);
                });
                tickTimer.tick(dt, (deltaTime) -> {
                    game.tick(deltaTime);
                    fpsCounter.tick(deltaTime);
                });
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
