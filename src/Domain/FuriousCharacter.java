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
public class FuriousCharacter extends Character{

    //atributos
    private Buffer sharedBuffer;
    private int getBack=0;
    private int[] startPoint;
    private int time, size;
    private int [][] matrix;
    ArrayList<Image> energy;
    private DrawingMethods drawing;
    private String name;
    
    //constructores
    public FuriousCharacter(int x, int y, int size, int speed, int imageNum, Buffer shared) throws FileNotFoundException {
        super(x, y, speed, imageNum);
        this.sharedBuffer = shared;
        this.time = 0;
        this.size=size;
        this.energy = null;
        this.startPoint = new int[2];
        setSprite();
    }
    
    public FuriousCharacter(int time, int size, ArrayList<Image> energy, int[][] matrix, int x, int y, int speed, int imageNum) {
        super(x, y, speed, imageNum);
        this.time = time;
        this.energy = energy;
        this.matrix = matrix;
        this.size=size;
        try {
            setSprite();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //define los sprite
    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/paD" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }
    
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
    
    public void setNames(String name){
        this.name=name;
    }
    
    public String getNames(){
        return this.name;
    }

    public void setMatrix(int[][] matrix, int start) {
        this.matrix = matrix;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[i][x] == start) {
                    this.startPoint[0] = i;//fila
                    this.startPoint[1] = x;//columna
                    super.setX(x*this.size);
                    super.setY(i*this.size);
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
                this.getBack=1;
                super.setImage(sprite.get(count));
                super.setX(this.startPoint[1] * this.size);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1 && this.getBack==0) {
                    this.right();
                }
                if (m == 2) {
                    this.down();
                    this.getBack=0;
                }
                if (m == 3) {
                    this.up();
                    this.getBack=0;
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
                this.getBack=1;
                super.setImage(sprite.get(count));
                super.setX(this.startPoint[1] * this.size);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.up();
                    this.getBack=0;
                }
                if (m == 2 && this.getBack==0) {
                    this.left();
                }
                if (m == 3) {
                    this.down();
                    this.getBack=0;
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
                this.getBack=1;
                super.setImage(sprite2.get(count));
                super.setY(this.startPoint[0] * this.size);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.left();
                    this.getBack=0;
                }
                if (m == 2) {
                    this.right();
                    this.getBack=0;
                }
                if (m == 3 && this.getBack==0) {
                    this.down();
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
                this.getBack=1;
                super.setImage(sprite2.get(count));
                super.setY(this.startPoint[0] * this.size);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int m = (int) randomCoord();
                if (m == 1) {
                    this.left();
                    this.getBack=0;
                }
                if (m == 2) {
                    this.right();
                    this.getBack=0;
                }
                if (m == 3 && this.getBack==0) {
                    this.up();
                }
            }
        }
    }
}
