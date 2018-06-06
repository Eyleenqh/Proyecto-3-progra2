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
public class Item extends Character{

    //atributos
    private Buffer sharedBuffer;
    private int getBack=0;
    private int[] startPoint;
    private int time, size, typeMovement;
    private int [][] matrix;
    ArrayList<Image> energy;
    private DrawingMethods drawing;

    //constructores
    public Item(int x, int y, int size, int speed, int imageNum, Buffer shared) throws FileNotFoundException {
        super(x, y, speed, imageNum);
        this.sharedBuffer = shared;
        this.time = 0;
        this.size=size;
        this.energy = null;
        this.startPoint = new int[2];
        setSprite();
    }
    
    public Item(int time, int size, ArrayList<Image> energy, int[][] matrix, int x, int y, int speed, int imageNum) {
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
    
    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 2; i++) {
            sprite.add(new Image(new FileInputStream("assets/Fan" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
    }
    
    public void setMatrix(int[][] matrix, int typeMove) {
        this.matrix = matrix;
        this.typeMovement=typeMove;
        this.startPoint[0]= (int) (Math.random()*(this.matrix.length));//fila
        this.startPoint[1]= (int) (Math.random()*(this.matrix.length));//columna
        
        if(this.matrix[this.startPoint[0]][this.startPoint[1]]==0){
            this.setMatrix(this.matrix, this.typeMovement);
        }
    }

    @Override
    public void run() {
        if(this.typeMovement==1 && (this.matrix[this.startPoint[0]][this.startPoint[1]+1]==0 ||
                this.matrix[this.startPoint[0]][this.startPoint[1]-1]==0)){
            this.rigth();
        }else{
            this.up();
        }
        
        if(this.typeMovement==2 && (this.matrix[this.startPoint[0]-1][this.startPoint[1]]==0 ||
                this.matrix[this.startPoint[0]+1][this.startPoint[1]]==0)){
            this.up();
        }else{
            this.rigth();
        }
    }
    
    public void up(){
        ArrayList<Image> sprite = super.getSprite();
        int num=0;
        super.setX(this.startPoint[1]*this.size);
        
        while(this.sharedBuffer.move(4, this.matrix, this.startPoint)){
            super.setImage(sprite.get(num));
            this.startPoint[0]--;
            super.setY(this.startPoint[0] * this.size);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(num==1){
                num=0;
            }else{
                num=1;
            }
        }
        this.down();
    }
    
    public void down(){
        ArrayList<Image> sprite = super.getSprite();
        int num=0;
        super.setX(this.startPoint[1]*this.size);
        
        while(this.sharedBuffer.move(3, this.matrix, this.startPoint)){
            super.setImage(sprite.get(num));
            this.startPoint[0]++;
            super.setY(this.startPoint[0] * this.size);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(num==1){
                num=0;
            }else{
                num=1;
            }
        }
        this.up();
    }
    
    public void rigth(){
        ArrayList<Image> sprite = super.getSprite();
        int num=0;
        super.setY(this.startPoint[0]*this.size);
        
        while(this.sharedBuffer.move(1, this.matrix, this.startPoint)){
            super.setImage(sprite.get(num));
            this.startPoint[1]++;
            super.setX(this.startPoint[1] * this.size);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(num==1){
                num=0;
            }else{
                num=1;
            }
        }
        this.left();
    }
    
    public void left(){
        ArrayList<Image> sprite = super.getSprite();
        int num=0;
        super.setY(this.startPoint[0]*this.size);
        
        while(this.sharedBuffer.move(2, this.matrix, this.startPoint)){
            super.setImage(sprite.get(num));
            this.startPoint[1]--;
            super.setX(this.startPoint[1] * this.size);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(num==1){
                num=0;
            }else{
                num=1;
            }
        }
        this.rigth();
    }
}