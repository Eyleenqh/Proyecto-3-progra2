/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Buffer.Buffer;
import Methods.DrawingMethods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Eyleen
 */
public class SmartCharacter extends Character {

    //atributos
    private int effect;
    private Buffer sharedBuffer;
    private String name;
    private int[] startPoint;
    private int time, size;
    private int[][] matrix;
    private DrawingMethods drawing;

    //constructores
    public SmartCharacter(int x, int y, int size, int speed, int imageNum, Buffer shared) throws FileNotFoundException {
        super(x, y, speed, imageNum);
        this.sharedBuffer = shared;
        this.time = 0;
        this.size = size;
        this.startPoint = new int[2];

        this.effect = 0;
        setRightSprite();
    }

    //define los sprite
    //sprite derecha
    public void setRightSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        sprite.clear();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/piD" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }

    //sprite abajo
    public void setDownSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        sprite.clear();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/piDa" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }

    //sprite arriba
    public void setUpSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        sprite.clear();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/piU" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }

    //sprite izquierda
    public void setLeftSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        sprite.clear();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/piI" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }

    //metodos accesores
    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public String getNames() {
        return name;
    }

    public void setNames(String name) {
        this.name = name;
    }

    public void setMatrix(int[][] matrix, int start) {
        this.matrix = matrix;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[i][x] == start) {
                    this.startPoint[0] = i;//fila
                    this.startPoint[1] = x;//columna
                    super.setX(x * this.size);
                    super.setY(i * this.size);
                }
            }
        }
    }

    //metodo run del hilo
    @Override
    public void run() {
        while (true) {
            //inicia moviendose hacia la derecha
            try {
                this.right();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SmartCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Metodos random
    public double randomCoord() {
        double coord = Math.random() * (3 - 1 + 1) + 1;
        return coord;
    }//randomCoordX

    //moverse a la derecha
    public void right() throws FileNotFoundException {
        //se establece el sprite con las imagenes hacia la derecha
        this.setRightSprite();
        ArrayList<Image> sprite = super.getSprite();

        for (int count = 0;; count++) {
            //valida si ya llego a la meta
            if (this.matrix[startPoint[0]][startPoint[1]] != 7) {
                //se consulta en el buffer si es posible seguir avanzando en caso de que no haya un muro
                if (this.sharedBuffer.move(1, this.matrix, this.startPoint)) {
                    if (count == 2) {
                        this.startPoint[1]++;
                    }
                    if (count == 3) {
                        count = 0;
                    }
                    //se le da una nueva posicion para que avance
                    super.setImage(sprite.get(count));
                    super.setX(super.getX() + (this.size / 3));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {//hay un muro
                    //entonces elige una nueva direccion aleatoria
                    int m = (int) randomCoord();
                    if (m == 1) {
                        this.right();
                    }
                    if (m == 2) {
                        this.down();
                    }
                    if (m == 3) {
                        this.up();
                    }
                }
            } else {
                Thread.interrupted();
            }
        }
    }

    //moverse a la izquierda
    public void left() throws FileNotFoundException {
        //se establece el sprite con las imagenes hacia la izquierda
        this.setLeftSprite();
        ArrayList<Image> sprite = super.getSprite();

        for (int count = 0;; count++) {
            //valida si ya llego a la meta
            if (this.matrix[startPoint[0]][startPoint[1]] != 7) {
                //se consulta en el buffer si es posible seguir avanzando en caso de que no haya un muro
                if (this.sharedBuffer.move(2, this.matrix, this.startPoint)) {
                    if (count == 2) {
                        this.startPoint[1]--;
                    }
                    if (count == 3) {
                        count = 0;
                    }
                    //se le da una nueva posicion para que avance
                    super.setImage(sprite.get(count));
                    super.setX(super.getX() - (this.size / 3));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {//hay un muro
                    //entonces elige una nueva direccion aleatoria
                    int m = (int) randomCoord();
                    if (m == 1) {
                        this.up();
                    }
                    if (m == 2) {
                        this.left();
                    }
                    if (m == 3) {
                        this.down();
                    }
                }
            } else {
                Thread.interrupted();
            }
        }
    }

    //moverse hacia abajo
    public void down() throws FileNotFoundException {
        //se establece el sprite con las imagenes hacia abajo
        this.setDownSprite();
        ArrayList<Image> sprite2 = super.getSprite();

        for (int count = 0;; count++) {
            //valida si ya llego a la meta
            if (this.matrix[startPoint[0]][startPoint[1]] != 7) {
                //se consulta en el buffer si es posible seguir avanzando en caso de que no haya un muro
                if (this.sharedBuffer.move(3, this.matrix, this.startPoint)) {
                    if (count == 2) {
                        this.startPoint[0]++;
                    }
                    if (count == 3) {
                        count = 0;
                    }
                    //se le da una nueva posicion para que avance
                    super.setImage(sprite2.get(count));
                    super.setY(super.getY() + (this.size / 3));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {//hay un muro
                    //entonces elige una nueva direccion aleatoria
                    int m = (int) randomCoord();
                    if (m == 1) {
                        this.left();
                    }
                    if (m == 2) {
                        this.right();
                    }
                    if (m == 3) {
                        this.down();
                    }
                }
            } else {
                Thread.interrupted();
            }
        }
    }

    //moverse hacia arriba
    public void up() throws FileNotFoundException {
        //se establece el sprite con las imagenes hacia arriba
        this.setUpSprite();
        ArrayList<Image> sprite2 = super.getSprite();

        for (int count = 0;; count++) {
            //valida si ya llego a la meta
            if (this.matrix[startPoint[0]][startPoint[1]] != 7) {
                //se consulta en el buffer si es posible seguir avanzando en caso de que no haya un muro
                if (this.sharedBuffer.move(4, this.matrix, this.startPoint)) {
                    if (count == 2) {
                        this.startPoint[0]--;
                    }
                    if (count == 3) {
                        count = 0;
                    }
                    //se le da una nueva posicion para que avance
                    super.setImage(sprite2.get(count));
                    super.setY(super.getY() - (this.size / 3));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {//hay un muro
                    //entonces elige una nueva direccion aleatoria
                    int m = (int) randomCoord();
                    if (m == 1) {
                        this.left();
                    }
                    if (m == 2) {
                        this.right();
                    }
                    if (m == 3) {
                        this.up();
                    }
                }
            } else {
                Thread.interrupted();
            }
        }
    }
}
