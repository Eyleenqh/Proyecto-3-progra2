/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Buffer.SynchronizedBuffer;
import Domain.FastCharacter;
import Domain.FuriousCharacter;
import Domain.Item;
import Domain.SmartCharacter;
import Methods.DrawingMethods;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author faubricioch
 */
public class HardMode extends Application implements Runnable {

    //
    private Label general, smartLbl, fastLbl, furiousLbl;
    private TextField smartTfd, fastTfd, furiousTfd;
    private Button btnStart, btnPause, btnStop, btnSetI;
    private GridPane grid;
    private HBox hbox;
    private int smartQ;
    private int fastQ;
    private int furiousQ;
    private int set=0;
    private ArrayList<FuriousCharacter> arrayFurious;
    private ArrayList<FastCharacter> arrayFast;
    private ArrayList<SmartCharacter> arraySmart;
    //
    
    private Pane pane;
    private Canvas canvas;
    private GraphicsContext gc;
    private DrawingMethods dm;
    private int exit = 7;
    private int start = 8;
    private int size = 13;
    private int referenceMatrix[][] = dibujaMatriz();
    private Thread thread;
    private SynchronizedBuffer syncBuff;
    private FuriousCharacter[] furious;
    private FastCharacter[] fast;
    private SmartCharacter[] smart;
    private Item[] items;
    private boolean startRun=false;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hard Mode");
        stage.setResizable(false);
        stage.setMaximized(true);
        //
        //
        this.hbox=new HBox();
        this.grid=new GridPane();
        this.general=new Label("Choose how much characters do you want.");
        this.smartLbl=new Label("Smart");
        this.smartTfd=new TextField();
        this.fastLbl=new Label("Fast");
        this.fastTfd=new TextField();
        this.furiousLbl=new Label("Furious");
        this.furiousTfd=new TextField();
        this.btnSetI=new Button("Set characters");
        this.btnStart=new Button("Start run");
        this.btnStart.setDisable(true);
        this.btnPause=new Button("Pause run");
        this.btnPause.setDisable(true);
        this.btnStop=new Button("Stop run");
        this.btnStop.setDisable(true);
        //
        
        //
        
        
        //creamos los componentes de la ventana
        this.pane = new Pane();
        this.canvas = new Canvas(675, 675);
        this.pane.getChildren().add(this.canvas);
        this.gc = canvas.getGraphicsContext2D();
        
        //
        //
        this.grid.setVgap(10);
        this.grid.setHgap(17);
        this.grid.add(this.general, 0, 0);
        this.grid.add(this.smartLbl, 0, 1);
        this.grid.add(this.smartTfd, 0, 2);
        this.grid.add(this.fastLbl, 0, 3);
        this.grid.add(this.fastTfd, 0, 4);
        this.grid.add(this.furiousLbl, 0, 5);
        this.grid.add(this.furiousTfd, 0, 6);
        this.grid.add(this.btnSetI, 1, 1);
        this.grid.add(this.btnStart, 1, 2);
        this.grid.add(this.btnPause, 1, 3);
        this.grid.add(this.btnStop, 1, 4);
        
        this.hbox.setSpacing(10);
        this.hbox.getChildren().addAll(this.pane, this.grid);
        
        //acciones de los botones
        //acciones de los botones
        this.btnSetI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                //cantidad de caracteres
                //comprobamos que los textFild est\E9n llenos o al menos 1
                if(set==0){
                    if(smartTfd.getText().equals("") && 
                            fastTfd.getText().equals("") && 
                            furiousTfd.getText().equals("")){
                        general.setText("Insert at least one character");
                        set=0;
                    }else{
                        set=1;
                        btnStart.setDisable(false);
                        btnPause.setDisable(false);
                        btnStop.setDisable(false);

                        if(!smartTfd.getText().equals("")){
                            try{
                                setQ(1, Integer.parseInt(smartTfd.getText()));
                            }catch(Exception e){
                                general.setText("Insert correct information");
                                set=0;
                                btnStart.setDisable(true);
                                btnPause.setDisable(true);
                                btnStop.setDisable(true);
                            }
                        }else{
                            try {
                                setQ(1, 0);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(HardMode.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }//if para definir la cantidad de caracteres

                        if(!fastTfd.getText().equals("")){
                            try{
                                setQ(2, Integer.parseInt(fastTfd.getText()));
                            }catch(Exception e){
                                general.setText("Insert correct information");
                                set=0;
                                btnStart.setDisable(true);
                                btnPause.setDisable(true);
                                btnStop.setDisable(true);
                            }
                        }else{
                            try {
                                setQ(2, 0);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(HardMode.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }// if para definir la cantidad de caracteres

                        if(!furiousTfd.getText().equals("")){
                            try{
                                setQ(3, Integer.parseInt(furiousTfd.getText()));
                            }catch(Exception e){
                                general.setText("Insert correct information");
                                set=0;
                                btnStart.setDisable(true);
                                btnPause.setDisable(true);
                                btnStop.setDisable(true);
                            }
                        }else{
                            try {
                                setQ(3, 0);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(HardMode.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }//if para definir la cantidad de caracteres
                    }
                    smartTfd.setText("");
                    fastTfd.setText("");
                    furiousTfd.setText("");
                }else{
                    btnSetI.setDisable(true);
                    btnStart.setDisable(false);
                    btnPause.setDisable(false);
                    btnStop.setDisable(false);
                }
            }
        });
        
        this.btnStart.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(smartQ>0){
                    for(int i=0; i<smartQ; i++){
                        arraySmart.get(i).start();
                    }
                }
                if(furiousQ>0){
                    for(int i=0; i<furiousQ; i++){
                        arrayFurious.get(i).start();
                    }
                    
                }
                if(fastQ>0){
                    for(int i=0; i<fastQ; i++){
                        arrayFast.get(i).start();
                    }
                    
                }
                startRun=true;
                btnStart.setDisable(true);
                btnSetI.setDisable(true);
            }
        });
        //

        //creamos la escena
        Scene scene = new Scene(this.hbox, stage.getWidth(), stage.getMinHeight());
        this.canvas.setOnMouseClicked(mouseClick);
        stage.setScene(scene);
        stage.show();

        //llamamos a la clase que contiene los metodos de dibujo
        this.dm = new DrawingMethods();
        this.syncBuff = new SynchronizedBuffer();
        this.thread = new Thread(this);
        this.thread.start();
    }

    //metodo draw
    public void draw(GraphicsContext gc) {
        dm.drawMaze(gc, referenceMatrix, exit, start, size);
        //gc.drawImage(this.fast.getImage(), this.fast.getX(), this.fast.getY(), size, size);
        if(this.startRun){
            //hilos furious
            if(this.furiousQ>0){
                for(int i=0; i<furiousQ; i++){
                    gc.drawImage(this.arrayFurious.get(i).getImage(),
                            this.arrayFurious.get(i).getX(), this.arrayFurious.get(i).getY(),
                            size, size);
                }
            }
            
            //hilos smart
            if(this.smartQ>0){
                for(int i=0; i<smartQ; i++){
                    gc.drawImage(this.arraySmart.get(i).getImage(),
                            this.arraySmart.get(i).getX(), this.arraySmart.get(i).getY(),
                            size, size);
                }
            }
            
            //hilos smart
            if(this.smartQ>0){
                for(int i=0; i<smartQ; i++){
                    gc.drawImage(this.arraySmart.get(i).getImage(),
                            this.arraySmart.get(i).getX(), this.arraySmart.get(i).getY(),
                            size, size);
                }
            }
            
            //hilos fast
            if(this.fastQ>0){
                for(int i=0; i<fastQ; i++){
                    gc.drawImage(this.arrayFast.get(i).getImage(),
                            this.arrayFast.get(i).getX(), this.arrayFast.get(i).getY(),
                            size, size);
                }
            }
        }
    }

    //Evento del mouse que permite seleccionar una bloque
    EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            //obtiene las coordenadas de donde se activo el evento y tamanio en pixeles ingresado por el usuario
            int x = (int) e.getX();
            int y = (int) e.getY();
            if (e.getButton() == MouseButton.PRIMARY) {
                dm.removeOrAdd(gc, x, y, referenceMatrix, exit, start, size);
            }
        }
    };
    
    public void setQ(int quantity, int num) throws FileNotFoundException{
        if(quantity==1){
            this.smartQ=num;
            this.arraySmart=new ArrayList<SmartCharacter>(this.smartQ);
        }
        if(quantity==2){
            this.fastQ=num;
            this.arrayFast=new ArrayList<FastCharacter>(this.fastQ);
            this.fast=new FastCharacter[this.furiousQ];
            for(int i=0; i<this.fastQ; i++){
                this.fast[i]=new FastCharacter(0, (27 * size) - size, 10, 0, this.syncBuff);
                this.fast[i].setMatrix(this.referenceMatrix);
                this.arrayFast.add(this.fast[i]);
            }
        }
        if(quantity==3){
            this.furiousQ=num;
            this.arrayFurious=new ArrayList<FuriousCharacter>(this.furiousQ);
            this.furious=new FuriousCharacter[this.furiousQ];
            for(int i=0; i<this.furiousQ; i++){
                this.furious[i]=new FuriousCharacter(0, (27 * size) - size, this.size, 10, 0, this.syncBuff);
                this.furious[i].setMatrix(this.referenceMatrix, this.start);
                this.arrayFurious.add(this.furious[i]);
            }
        }
    }
    
    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;
        while (true) {
            System.out.println();
            while(this.startRun){   
                try {
                    start = System.nanoTime();
                    elapsed = System.nanoTime() - start;
                    wait = time - elapsed / 1000000;
                    Thread.sleep(wait);
                    GraphicsContext gc = this.canvas.getGraphicsContext2D();
                    //dibujamos
                    this.draw(gc);
                }catch (InterruptedException ex) {
                }
            }
        }
    }

    int[][] dibujaMatriz() {
        int[][] matrix = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
            {start, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, exit, exit},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {start, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        return matrix;
    }
}
