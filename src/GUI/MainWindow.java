/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author faubricioch
 */
public class MainWindow extends Application{
    
    private Pane mainPane;
    @Override
    public void start(Stage stage) throws Exception {
        this.mainPane = new Pane();
        
        //titulo
        stage.setTitle("Maze of Threads");
        
        //Menú de ingreso al juego
        MenuBar bar=new MenuBar();
        Menu menu=new Menu("Game");
        MenuItem menuItem=new MenuItem("Select Mode");
        
        //agregamos el item al menu
        menu.getItems().add(menuItem);
        
        //accion del item
        menuItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                GameMode gameMode=new GameMode();
                try {
                    gameMode.start(stage);
                } catch (Exception ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //accion del item
        
        //agregamos el menu a la barra
        bar.getMenus().add(menu);
        bar.relocate(0, 0);
        this.mainPane.getChildren().add(bar);
        
        //declaramos scene
        Scene scene=new Scene(this.mainPane, 600, 600);
        
        //lo agregamos a la ventana
        stage.setScene(scene);
        
        //ponemos el fondo de la ventaba principal
        setBackground();
        
        //lo mostramos todo
        stage.show();
    }
    
    //metodo que establece el fondo de la ventana principal
    public void setBackground() throws FileNotFoundException{
        Image back = new Image(new FileInputStream("assets/icon.png"));
        //asigna el tamanio que va a tener la imagen
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        //establece la imagen como fondo
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        this.mainPane.setBackground(background);
    }
    
}
