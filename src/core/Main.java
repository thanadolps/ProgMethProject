package core;

import core.timing.FpsCounter;
import core.timing.Interval;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static Game game = new Game();
    public static Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = new Pane();
        canvas = new Canvas();
        canvas.setWidth(960);
        canvas.setHeight(960);
        root.getChildren().add(canvas);
        
        setupGraphics(canvas.getGraphicsContext2D());

        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower d' Fence");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void setupGraphics(GraphicsContext gc) {
        double w = gc.getCanvas().getWidth();
        double h = gc.getCanvas().getHeight();
        gc.setFill(Color.BLACK);

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
