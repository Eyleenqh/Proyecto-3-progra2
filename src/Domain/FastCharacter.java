/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Eyleen
 */
public class FastCharacter extends Character {

    //atributos
    private int time;
    private ArrayList<Image> energy;

    //constructores
    public FastCharacter() {
        super(0, 0, 0, null);
        this.time = 0;
        this.energy = null;
    }

    public FastCharacter(int time, ArrayList<Image> energy, int x, int y, int speed, ArrayList<Image> image) {
        super(x, y, speed, image);
        this.time = time;
        this.energy = energy;
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
}
