/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author faubricioch
 */
public class MainWindow extends Application{

    @Override
    public void start(Stage stage) throws Exception {
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
                
            }
        });
        //accion del item
        
        //agregamos el menu a la barra
        bar.getMenus().add(menu);
        
        BorderPane pane=new BorderPane();
        pane.setTop(bar);
        
        //declaramos scene
        Scene scene=new Scene(pane, 300, 300);
        
        //lo agregamos a la ventana
        stage.setScene(scene);
        
        //lo mostramos todo
        stage.show();
    }
    
}
