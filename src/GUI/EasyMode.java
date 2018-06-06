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
 * @author Steven
 */
public class EasyMode extends Application implements Runnable {

    private Pane pane;
    private Canvas canvas;
    private GraphicsContext gc;
    private DrawingMethods dm;
    private int exit = 7;
    private int start = 8;
    private int size = 30;
    private int referenceMatrix[][] = {{11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
    {11, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
    {11, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 11},
    {11, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 11},
    {11, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 11},
    {11, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 11},
    {11, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 11},
    {11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 11},
    {11, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 11},
    {11, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 11},
    {11, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 11},
    {11, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 11},
    {11, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 11},
    {start, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 11},
    {11, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 11},
    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, exit},
    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}};

    private Thread thread;
    private SynchronizedBuffer syncBuff;
    private Label general, smartLbl, fastLbl, furiousLbl, itemlbl, nombrelbl, playerslbl;
    private TextField smartTfd, fastTfd, furiousTfd, itemtfd, nombreTfd, playersTfd;
    private Button btnStart, btnPause, btnStop, btnSetI, btnSetItem, btnSetPlayers, btnSetName;
    private GridPane grid;
    private HBox hbox;
    private int smartQ;
    private int fastQ;
    private int furiousQ;
    private int itemsQ;
    private int playersQ;
    private int set = 0, numP;
    private ArrayList<FuriousCharacter> arrayFurious;
    private ArrayList<FastCharacter> arrayFast;
    private ArrayList<SmartCharacter> arraySmart;
    private ArrayList<Item> arrayItem;
    private FuriousCharacter[] furious;
    private FastCharacter[] fast;
    private SmartCharacter[] smart;
    private Item[] items;
    private boolean startRun = false, drawIt = true;
    private String[] name;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Easy Mode");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        //creamos los componentes de la ventana
        this.numP = 0;
        this.playerslbl = new Label("Number of players");
        this.playersTfd = new TextField();
        this.btnSetPlayers = new Button("Set players");

        this.nombrelbl = new Label("Player");
        this.nombreTfd = new TextField();
        this.btnSetName = new Button("Set Name");

        this.hbox = new HBox();
        this.grid = new GridPane();
        this.general = new Label("Choose how much characters do you want.");
        this.smartLbl = new Label("Smart");
        this.smartTfd = new TextField();
        this.fastLbl = new Label("Fast");
        this.fastTfd = new TextField();
        this.furiousLbl = new Label("Furious");
        this.furiousTfd = new TextField();
        this.itemlbl = new Label("Items: ");
        this.itemtfd = new TextField();
        this.btnSetItem = new Button("Set Items");
        this.btnSetI = new Button("Set characters");
        this.btnStart = new Button("Start run");
        this.btnStart.setDisable(true);
        this.btnPause = new Button("Pause run");
        this.btnPause.setDisable(true);
        this.btnStop = new Button("Stop run");
        this.btnStop.setDisable(true);
        this.btnSetI.setDisable(true);
        this.btnSetName.setDisable(true);
        //

        this.pane = new Pane();
        this.canvas = new Canvas(648, 648);
        this.canvas.setOnMouseClicked(mouseClick);
        this.pane.getChildren().add(this.canvas);
        this.gc = canvas.getGraphicsContext2D();

        
        this.grid.setVgap(10);
        this.grid.setHgap(17);
        //labels y textFields
        //this.grid.add(this.general, 0, 0);
        this.grid.add(this.playerslbl, 0, 1);
        this.grid.add(this.playersTfd, 0, 2);

        this.grid.add(this.nombrelbl, 0, 3);
        this.grid.add(this.nombreTfd, 0, 4);

        this.grid.add(this.smartLbl, 0, 5);
        this.grid.add(this.smartTfd, 0, 6);

        this.grid.add(this.furiousLbl, 0, 7);
        this.grid.add(this.furiousTfd, 0, 8);

        this.grid.add(this.fastLbl, 0, 9);
        this.grid.add(this.fastTfd, 0, 10);

        this.grid.add(this.itemlbl, 0, 11);
        this.grid.add(this.itemtfd, 0, 12);

        //agregamos botones
        this.grid.add(this.btnSetPlayers, 1, 2);
        this.grid.add(this.btnSetName, 1, 4);
        this.grid.add(this.btnSetI, 1, 6);
        this.grid.add(this.btnStart, 1, 7);
        this.grid.add(this.btnPause, 1, 8);
        this.grid.add(this.btnStop, 1, 9);

        this.hbox.setSpacing(10);
        this.hbox.getChildren().addAll(this.pane, this.grid);

        this.btnSetPlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                try {
                    if (playersTfd.getText().equals("") || playersTfd.getText().equals("0")) {
                        playerslbl.setText("Set a number");
                    } else {
                        playersQ = Integer.parseInt(playersTfd.getText());
                        name = new String[playersQ];
                        btnSetName.setDisable(false);
                        //btnSetI.setDisable(false);
                        btnSetPlayers.setDisable(true);
                        playersTfd.setDisable(true);
                    }
                } catch (Exception e) {
                    playerslbl.setText("Set a number");
                }
            }
        });

        this.btnSetName.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (playersQ > 0) {
                    if (nombreTfd.getText().equals("")) {
                        nombrelbl.setText("Set a name");
                    } else {
                        name[numP] = nombreTfd.getText();
                        btnSetI.setDisable(false);
                        nombreTfd.setText("");
                        nombreTfd.setDisable(true);
                        numP++;
                    }
                }
            }
        });

        //acciones de los botones
        //acciones de los botones
        this.btnSetI.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                //cantidad de caracteres
                //comprobamos que los textFild est\E9n llenos o al menos 1
                int pl = 0;
                if (set == 0) {
                    if (smartTfd.getText().equals("")
                            && fastTfd.getText().equals("")
                            && furiousTfd.getText().equals("")) {
                        general.setText("Insert at least one character");
                        set = 0;
                        pl++;
                    } else {
                        set = 1;

                        if (!smartTfd.getText().equals("")) {
                            try {
                                pl = 0;
                                smartQ = smartQ + Integer.parseInt(smartTfd.getText());
                            } catch (Exception e) {
                                smartLbl.setText("Insert correct information");
                                set = 0;
                                pl++;
                            }
                        } else {
                            smartQ = smartQ + 0;
                            pl = 0;
                        }//if para definir la cantidad de caracteres

                        if (!fastTfd.getText().equals("")) {
                            try {
                                pl = 0;
                                fastQ = fastQ + Integer.parseInt(fastTfd.getText());
                            } catch (Exception e) {
                                pl++;
                                fastLbl.setText("Insert correct information");
                                set = 0;
                            }
                        } else {
                            fastQ = fastQ + 0;
                            pl = 0;
                        }// if para definir la cantidad de caracteres

                        if (!furiousTfd.getText().equals("")) {
                            try {
                                pl = 0;
                                furiousQ = furiousQ + Integer.parseInt(furiousTfd.getText());
                            } catch (Exception e) {
                                pl++;
                                furiousLbl.setText("Insert correct information");
                                set = 0;
                            }
                        } else {
                            pl = 0;
                            furiousQ = furiousQ + 0;
                        }//if para definir la cantidad de caracteres

                        if (!itemtfd.getText().equals("")) {
                            try {
                                pl = 0;
                                itemsQ = itemsQ + Integer.parseInt(itemtfd.getText());
                            } catch (Exception e) {
                                itemlbl.setText("Insert correct information");
                                set = 0;
                                pl++;
                                btnStart.setDisable(true);
                                btnPause.setDisable(true);
                                btnStop.setDisable(true);
                            }
                        } else {
                            pl = 0;
                            itemsQ = itemsQ + 0;
                        }//if para definir la cantidad de caracteres
                    }
                    smartTfd.setText("");
                    fastTfd.setText("");
                    furiousTfd.setText("");
                    itemtfd.setText("");
                    set = 0;
                    if (pl == 0) {
                        playersQ--;
                        btnSetName.setDisable(false);
                        nombreTfd.setDisable(false);
                        btnSetI.setDisable(true);
                    }
                    if (playersQ == 0) {
                        try {
                            setQuantity();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(HardMode.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        btnStart.setDisable(false);
                        btnPause.setDisable(false);
                        btnStop.setDisable(false);
                        btnSetI.setDisable(true);
                        btnSetName.setDisable(true);
                    }
                }
            }
        });

        this.btnStart.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (smartQ > 0) {
                    for (int i = 0; i < smartQ; i++) {
                        arraySmart.get(i).start();
                    }
                }
                if (furiousQ > 0) {
                    for (int i = 0; i < furiousQ; i++) {
                        arrayFurious.get(i).start();
                    }

                }
                if (fastQ > 0) {
                    for (int i = 0; i < fastQ; i++) {
                        arrayFast.get(i).start();
                    }

                }
                if (itemsQ > 0) {
                    for (int i = 0; i < itemsQ; i++) {
                        arrayItem.get(i).start();
                    }
                }
                startRun = true;
                drawIt = true;
                btnStart.setDisable(true);
                btnSetI.setDisable(true);
            }
        });

        this.btnPause.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (startRun) {
                    startRun = false;
                    drawIt = false;
                    btnPause.setText("Restart Run");
                } else {
                    startRun = true;
                    drawIt = true;
                    btnPause.setText("Pause Run");
                }
            }
        });

        this.btnStop.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                thread.stop();
            }
        });

        //creamos la escena
        Scene scene = new Scene(this.hbox, primaryStage.getWidth(), primaryStage.getMinHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
        /*//centramos la escena en la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);*/

        //Creamos la matriz inicial
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
        if (this.startRun) {
            //hilos furious
            if (this.furiousQ > 0) {
                for (int i = 0; i < furiousQ; i++) {
                    gc.drawImage(this.arrayFurious.get(i).getImage(),
                            this.arrayFurious.get(i).getX(), this.arrayFurious.get(i).getY(),
                            size, size);
                }
            }

            //hilos smart
            if (this.smartQ > 0) {
                for (int i = 0; i < smartQ; i++) {
                    gc.drawImage(this.arraySmart.get(i).getImage(),
                            this.arraySmart.get(i).getX(), this.arraySmart.get(i).getY(),
                            size, size);
                }
            }

            //hilos fast
            if (this.fastQ > 0) {
                for (int i = 0; i < fastQ; i++) {
                    gc.drawImage(this.arrayFast.get(i).getImage(),
                            this.arrayFast.get(i).getX(), this.arrayFast.get(i).getY(),
                            size, size);
                }
            }

            //hilos fast
            if (this.itemsQ > 0) {
                for (int i = 0; i < itemsQ; i++) {
                    gc.drawImage(this.arrayItem.get(i).getImage(),
                            this.arrayItem.get(i).getX(), this.arrayItem.get(i).getY(),
                            size, size);
                }
            }
        }
    }

    //Evento del mouse que permite seleecionar una bloque
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

    public void setQ(int quantity, int num) throws FileNotFoundException {
        if (quantity == 1) {
            this.smartQ = num;
            this.arraySmart = new ArrayList<SmartCharacter>(this.smartQ);
            this.smart = new SmartCharacter[this.smartQ];
            for (int i = 0; i < this.smartQ; i++) {
                this.smart[i] = new SmartCharacter(0, (27 * size) - size, this.size, 10, 0, this.syncBuff);
                this.smart[i].setMatrix(this.referenceMatrix, this.start);
                this.arraySmart.add(this.smart[i]);
            }
        }
        if (quantity == 2) {
            this.fastQ = num;
            this.arrayFast = new ArrayList<FastCharacter>(this.fastQ);
            this.fast = new FastCharacter[this.fastQ];
            for (int i = 0; i < this.fastQ; i++) {
                this.fast[i] = new FastCharacter(0, (27 * size) - size, this.size, 10, 0, this.syncBuff);
                this.fast[i].setMatrix(this.referenceMatrix, this.start);
                this.arrayFast.add(this.fast[i]);
            }
        }
        if (quantity == 3) {
            this.furiousQ = num;
            this.arrayFurious = new ArrayList<FuriousCharacter>(this.furiousQ);
            this.furious = new FuriousCharacter[this.furiousQ];
            for (int i = 0; i < this.furiousQ; i++) {
                this.furious[i] = new FuriousCharacter(0, (27 * size) - size, this.size, 10, 0, this.syncBuff);
                this.furious[i].setMatrix(this.referenceMatrix, this.start);
                this.arrayFurious.add(this.furious[i]);
            }
        }
        if (quantity == 4) {
            this.itemsQ = num;
            this.arrayItem = new ArrayList<Item>(this.itemsQ);
            this.items = new Item[this.itemsQ];
            int move = 1;
            for (int i = 0; i < this.itemsQ; i++) {
                this.items[i] = new Item(0, (27 * size) - size, this.size, 10, 0, this.syncBuff);
                if (move == 2) {
                    this.items[i].setMatrix(referenceMatrix, move);
                    this.arrayItem.add(this.items[i]);
                    move = 1;
                } else {
                    this.items[i].setMatrix(referenceMatrix, move);
                    this.arrayItem.add(this.items[i]);
                    move = 2;
                }
            }
        }
        this.numP--;
    }

    public void setQuantity() throws FileNotFoundException {
        this.setQ(1, this.smartQ);
        this.setQ(2, this.fastQ);
        this.setQ(3, this.furiousQ);
        this.setQ(4, this.itemsQ);
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
            while (this.startRun) {
                try {
                    start = System.nanoTime();
                    elapsed = System.nanoTime() - start;
                    wait = time - elapsed / 1000000;
                    Thread.sleep(wait);
                    GraphicsContext gc = this.canvas.getGraphicsContext2D();
                    //dibujamos
                    this.draw(gc);
                } catch (InterruptedException ex) {
                }
            }
            if (!this.drawIt && this.itemsQ > 0) {
                try {
                    start = System.nanoTime();
                    elapsed = System.nanoTime() - start;
                    wait = time - elapsed / 1000000;
                    Thread.sleep(wait);
                    GraphicsContext gc = this.canvas.getGraphicsContext2D();
                    //dibujamos
                    this.draw(gc);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
