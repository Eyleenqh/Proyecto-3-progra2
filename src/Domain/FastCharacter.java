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
public class FastCharacter extends Character {

    //atributos
    private Buffer sharedBuffer;
    private int time;
    private int[][] matrix;
    private DrawingMethods drawing;
    private ArrayList<Image> energy;
    private int[] startPoint;

    //constructores
    public FastCharacter(int x, int y, int speed, int imageNum, Buffer shared) {
        super(x, y, speed, imageNum);
        this.sharedBuffer = shared;
        this.time = 0;
        this.energy = null;
        this.startPoint = new int[2];
        try {
            setSprite();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FastCharacter(int time, ArrayList<Image> energy, int[][] matrix, int x, int y, int speed, int imageNum) {
        super(x, y, speed, imageNum);
        this.time = time;
        this.energy = energy;
        this.matrix = matrix;
        try {
            setSprite();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/paD" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }

    //metodos accesores
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<Image> getEnergy() {
        return energy;
    }

    public void setEnergy(ArrayList<Image> energy) {
        this.energy = energy;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[i][x] == 8) {
                    this.startPoint[0] = i;//fila
                    this.startPoint[1] = x;//columna
                    /*super.setX(x*13);
                    super.setY(i*13);*/
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            this.right();
        }
    }

    //Metodos random
    public double randomCoord() {
        double coord = Math.random() * (3 - 1 + 1) + 1;
        return coord;
    }//randomCoordX

    public void right() {
        ArrayList<Image> sprite = super.getSprite();
        int j = this.getX() / 10;
        int i = this.getY();

        for (int count = 0;; count++) {
            if (this.sharedBuffer.move(1, this.matrix, this.startPoint)) {
                if (count == 2) {
                    this.startPoint[1]++;
                }
                if (count == 3) {
                    count = 0;
                }
                super.setImage(sprite.get(count));
                super.setX(super.getX() + 10);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.up();
                }
                if (m == 2) {
                    this.down();
                }
                if (m == 3) {
                    this.right();
                }
            }
        }
    }

    public void left() {
        ArrayList<Image> sprite = super.getSprite();
        int j = this.getX() / 10;
        int i = this.getY();

        //this.setX(j);
        for (int count = 0;; count++) {
            if (this.sharedBuffer.move(2, this.matrix, this.startPoint)) {
                if (count == 2) {
                    this.startPoint[1]--;
                }
                if (count == 3) {
                    count = 0;
                }
                super.setImage(sprite.get(count));
                super.setX(super.getX()-10);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.up();
                }
                if (m == 2) {
                    this.down();
                }
                if (m == 3) {
                    this.left();
                }
            }
        }
    }

    public void down() {
        ArrayList<Image> sprite2 = super.getSprite();
        int j = this.getX();
        int i = this.getY() / 10;

        for (int count = 0;; count++) {
            if (this.sharedBuffer.move(3, this.matrix, this.startPoint)) {
                if (count == 2) {
                    this.startPoint[0]++;
                }
                if (count == 3) {
                    count = 0;
                }
                super.setImage(sprite2.get(count));
                super.setY(super.getY()+10);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.down();
                }
                if (m == 2) {
                    this.right();
                }
                if (m == 3) {
                    this.left();
                }
            }
        }
    }

    public void up() {
        ArrayList<Image> sprite2 = super.getSprite();
        int j = this.getX();
        int i = this.getY() / 10;

        for (int count = 0;; count++) {
            if (this.sharedBuffer.move(4, this.matrix, this.startPoint)) {
                if (count == 2) {
                    this.startPoint[0]--;
                }
                if (count == 3) {
                    count = 0;
                }
                super.setImage(sprite2.get(count));
                super.setY(super.getY()-10);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.up();
                }
                if (m == 2) {
                    this.right();
                }
                if (m == 3) {
                    this.left();
                }
            }
        }
    }
}
