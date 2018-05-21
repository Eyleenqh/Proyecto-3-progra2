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
public class FuriousCharacter extends Character{

    //constructores
     public FuriousCharacter() {
        super(0, 0, 0, null);
    }
    public FuriousCharacter(int x, int y, int speed, ArrayList<Image> image) {
        super(x, y, speed, image);
    }
    
}
