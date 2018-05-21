/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Steven
 */
public class EasyMode extends Application {

    private Pane pane;
    private Image maze;
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Mosaic Maker");
        primaryStage.setResizable(false);
        //creamos los componentes de la ventana
        this.pane = new Pane();
        this.canvas = new Canvas(1200, 766);
        this.pane.getChildren().add(this.canvas);
        this.maze = new Image(new FileInputStream("assets/maze1.png"));
        this.gc = canvas.getGraphicsContext2D();

        //creamos la escena
        Scene scene = new Scene(this.pane, 1366, 766);
        primaryStage.setScene(scene);
        primaryStage.show();
        //centramos la escena en la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
        //dibujamos
        this.draw(gc);
    }
    //metodo draw
    public void draw(GraphicsContext gc) {
        gc.drawImage(maze, 0, 0);
    }
}
