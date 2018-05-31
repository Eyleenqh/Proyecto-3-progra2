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
public class SmartCharacter extends Character {
    //atributos
    private int effect;

    //constructores
    public SmartCharacter() {
        super(0, 0, 0, 0);
        this.effect=0;
    }
    
    public SmartCharacter(int effect, int x, int y, int speed, ArrayList<Image> image, int imageNum) {
        super(x, y, speed, imageNum);
        this.effect = effect;
    }

    //metodos accesores
    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }
    
    
}
