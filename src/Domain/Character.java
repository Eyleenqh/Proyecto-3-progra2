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
public class Character extends Thread{

    private int x;
    private int y;
    private int speed;
    private Image image;
    private int imageNum;
    private ArrayList<Image> sprite;

    public Character() {
        this.x = 0;
        this.y = 0;
        this.speed=0;
        this.sprite =null;
    }
    
    public Character(int x, int y,int speed, int imageNum) {
        this.x = x;
        this.y = y;
        this.speed=speed;
        this.imageNum = imageNum;
        this.sprite = new ArrayList<Image>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public int getImageNum() {
        return imageNum;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }
}
