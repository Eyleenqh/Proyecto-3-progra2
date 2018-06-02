/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

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
    private int time;
    private int start = 7;
    private int exit = 8;
    private int[][] matrix;
    private DrawingMethods drawing;
    private ArrayList<Image> energy;

    //constructores
    public FastCharacter(int x, int y, int speed, int imageNum) {
        super(x, y, speed, imageNum);
        this.time = 0;
        this.energy = null;
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
            sprite.add(new Image(new FileInputStream("assets/piD" + (i + 1) + ".png")));
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
    }

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        int i = this.getX();
        int j = this.getY();
        
        this.setY(j);
        while (true) {
                this.right();
        }
        /*ArrayList<Image> sprite = super.getSprite();
        int j = this.getX()/10;
        int i = this.getY();
        
        //this.setX(j);
>>>>>>> origin/master
        for (int count = 0;; count++) {
            if (count == 3) {
                count = 0;
            }
            if(this.matrix[this.getY()/30][(this.getX()/30)+1]==1){
                down();
            }
            super.setImage(sprite.get(count));
            super.setX(j * 10);
            j++;
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
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

        //this.setX(j);
        for (int count = 0;; count++) {
            if (count == 3) {
                count = 0;
            }
            if (this.matrix[this.getY() / 30][(this.getX() / 30) + 1] == 1) {
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
            super.setImage(sprite.get(count));
            super.setX(j * 10);
            j++;
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void left() {
        ArrayList<Image> sprite = super.getSprite();
        int j = this.getX() / 10;
        int i = this.getY();

        //this.setX(j);
        for (int count = 0;; count++) {
            if (count == 3) {
                count = 0;
            }
            if (this.matrix[this.getY() / 30][(this.getX() / 30)-1] == 1) {
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
            super.setImage(sprite.get(count));
            super.setX(j * 10);
            j--;
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void down() {
        ArrayList<Image> sprite2 = super.getSprite();
        int j = this.getX();
        int i = this.getY() / 10;

        for (int count = 0;; count++) {
            if (count == 3) {
                count = 0;
            }
            if (this.matrix[(this.getY() / 30) + 1][(this.getX() / 30)] == 1) {
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
            super.setImage(sprite2.get(count));
            super.setY(i * 10);
            i++;
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void up() {
        ArrayList<Image> sprite2 = super.getSprite();
        int j = this.getX();
        int i = this.getY() / 10;

        for (int count = 0;; count++) {
            if (count == 3) {
                count = 0;
            }
            if (this.matrix[(this.getY() / 30) - 1][(this.getX() / 30)] == 1) {
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
            super.setImage(sprite2.get(count));
            super.setY(i * 10);
            i--;
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
