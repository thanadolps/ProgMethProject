package core;

import core.timing.FpsCounter;
import core.timing.Interval;
import entity.game.type1;
import entity.game.type2;
import entity.game.type3;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import level.Level1;
import logic.Simulation;
import ui.*;
import utils.InputUtils;
import utils.Sprites;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {
    public static Game game;
    public static Canvas canvas;
    public static InputUtils inputUtils;
    public static Sidebar sidebar;
    public static Stage stage;
    public static AnimationTimer gameLoop;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setScene(new Scene(new TitleScreen()));
        stage.setTitle("Tower d' Fence");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    public static void restart() {
        var root = new HBox();
        canvas = new Canvas();
        sidebar = new Sidebar();

        try {
            game = new Game(new Level1());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        Simulation.restart();

        setupUI();
        setupGraphics(canvas.getGraphicsContext2D());
        inputUtils = new InputUtils(canvas);

        root.getChildren().addAll(canvas, sidebar);

        var scene = new Scene(root);
        stage.setScene(scene);
    }

    private static void setupUI() {
        canvas.setWidth(800);
        canvas.setHeight(800);

        var towerSelectUI = sidebar.getTowerSelectUI();
        towerSelectUI.addTowerButton(new TowerButton((x, y) ->
                new type1(1,200,10, x, y))
        );
        towerSelectUI.addTowerButton(new TowerButton((x, y) ->
                new type2(1,300,10, x, y))
        );
        towerSelectUI.addTowerButton(new TowerButton((x, y) ->
                new type3(0.5,400,10, x, y))
        );
    }

    private static void setupGraphics(GraphicsContext gc) {
        double w = gc.getCanvas().getWidth();
        double h = gc.getCanvas().getHeight();
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(18));

        gc.getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.handleClick(mouseEvent));

        gameLoop = new AnimationTimer() {
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
        };
        gameLoop.start();
    }

    public static void switchToEndScreen() {
        if(gameLoop != null) {
            gameLoop.stop();
        }
        stage.setScene(new Scene(new EndScreen()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
