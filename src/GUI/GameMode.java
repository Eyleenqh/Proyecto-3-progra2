/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author faubricioch
 */
public class GameMode extends Application {

    //componentes
    private Button btnEasy, btnMedium, btnHard;
    private Image mazeEasy, mazeMedium, mazeHard;
    private ImageView mazeImageE, mazeImageM, mazeImageH;
    private GridPane gpane;
    private EasyMode easyMode;
    private MediumMode mediumMode;
    private HardMode hardMode;
    @Override
    public void start(Stage stage) throws Exception {
        
        this.easyMode=new EasyMode();
        this.mediumMode=new MediumMode();
        this.hardMode=new HardMode();
        //inicializamos las imagenes y los imageview
        this.mazeEasy = new Image(new File("assets/Maze Modo1.png").toURI().toString());
        this.mazeMedium = new Image(new File("assets/Maze Modo2.png").toURI().toString());
        this.mazeHard = new Image(new File("assets/Maze Modo3.png").toURI().toString());
        this.mazeImageE = new ImageView(this.mazeEasy);
        this.mazeImageM = new ImageView(this.mazeMedium);
        this.mazeImageH = new ImageView(this.mazeHard);

        //inicializamos el gridpane
        this.gpane = new GridPane();
        this.gpane.setVgap(10);
        this.gpane.setHgap(10);

        //inicializamos los botones
        this.btnEasy = new Button("Easy Mode");
        this.btnMedium = new Button("Medium mode");
        this.btnHard = new Button("Hard Mode");

        //acciones de los botones
        this.btnEasy.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    easyMode.start(stage);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        this.btnMedium.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    mediumMode.start(stage);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        this.btnHard.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    hardMode.start(stage);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameMode.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(GameMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        //agregamos las imagenes y los botones al gridpane
        this.gpane.add(this.mazeImageE, 0, 0);
        this.gpane.add(this.btnEasy, 0, 1);
        //
        this.gpane.add(this.mazeImageM, 1, 0);
        this.gpane.add(this.btnMedium, 1, 1);
        //
        this.gpane.add(this.mazeImageH, 2, 0);
        this.gpane.add(this.btnHard, 2, 1);

        //creamos la escena
        Scene scene = new Scene(this.gpane, 700, 300);
        stage.setScene(scene);
        stage.show();
    }

}
