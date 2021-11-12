package core;

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
    @Override
    public void start(Stage primaryStage) throws Exception {
        var root = new Pane();
        var canvas = new Canvas();
        canvas.setWidth(960);
        canvas.setHeight(540);
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
            Interval fpsTimer = new Interval(1);
            double fpsDisplay = 0d;

            @Override
            public void handle(long now) {
                long dtNano = now - prevNano;
                // 60 fps = 1.6 s/frame = 16000000 ns/frame
                if(dtNano < 16000000L){
                    return;
                }
                this.prevNano = now;
                double dt = dtNano * 1e-9;

                gc.clearRect(0,0, w, h);

                gc.fillText("fps = " + Math.round(fpsDisplay),w/2,h/2);
                fpsTimer.tick(
                        dt,
                        (pe) -> fpsDisplay = 1/dt
                );
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
