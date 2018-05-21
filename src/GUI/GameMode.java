/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import javafx.application.Application;
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
public class GameMode extends Application{

    //componentes
    Button btnEasy, btnMedium, btnHard;
    Image mazeEasy, mazeMedium, mazeHard;
    ImageView mazeImageE, mazeImageM, mazeImageH;
    GridPane gpane;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //inicializamos las imagenes y los imageview
        mazeEasy=new Image(new File("assets/Maze Modo1.png").toURI().toString());
        mazeMedium=new Image(new File("assets/Maze Modo2.png").toURI().toString());
        mazeHard=new Image(new File("assets/Maze Modo3.png").toURI().toString());
        mazeImageE=new ImageView(mazeEasy);
        mazeImageM=new ImageView(mazeMedium);
        mazeImageH=new ImageView(mazeHard);

        //inicializamos el gridpane
        gpane=new GridPane();
        gpane.setVgap(10);
        gpane.setHgap(10);
        
        //inicializamos los botones
        btnEasy=new Button("Easy Mode");
        btnMedium=new Button("Medium mode");
        btnHard=new Button("Hard Mode");
        
        //agregamos las imagenes y los botones al gridpane
        gpane.add(mazeImageE, 0, 0);
        gpane.add(btnEasy, 0, 1);
        //
        gpane.add(mazeImageM, 1, 0);
        gpane.add(btnMedium, 1, 1);
        //
        gpane.add(mazeImageH, 2, 0);
        gpane.add(btnHard, 2, 1);
        
        //creamos la escena
        Scene scene=new Scene(gpane, 700, 300);
        stage.setScene(scene);
        stage.show();
    }
    
}
