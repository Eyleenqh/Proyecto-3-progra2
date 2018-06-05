/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Eyleen
 */
public class Item extends Character{

    private int[] firstStart;
    private int typeItem;
    private int [][] matriz;
    private int time, size;
    private ArrayList<Image> sprite;

    public Item() throws FileNotFoundException {
        super();
        this.firstStart=new int[2];
        this.time=0;
        this.size=0;
        this.typeItem=0;
        this.setSprite();
    }

    public Item(int x, int y, int speed, int imageNum, int type, int size) throws FileNotFoundException {
        super(x, y, 5, 0);
        this.firstStart=new int[2];
        this.time=0;
        this.size=size;
        this.typeItem=type;
        this.setSprite();
    }
    
    public void setMatrix(int [][] ma){
        this.matriz=ma;
        if(this.typeItem==1){
            if(this.matriz.length==22){

            }

            if(this.matriz.length==42){

            }

            if(this.matriz.length==52){
                this.firstStart[0]=17;
                this.firstStart[1]=17;
                super.setX(17*13);
                super.setY(17*13);
            }
        }else{
            System.out.println("g");
            if(this.matriz.length==22){

            }

            if(this.matriz.length==42){

            }

            if(this.matriz.length==52){
                this.firstStart[0]=40;
                this.firstStart[1]=40;
                super.setX(40*13);
                super.setY(40*13);
            }
        }
    }
    
    public int[][] getMatrix(){
        return this.matriz;
    }
    
    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 2; i++) {
            sprite.add(new Image(new FileInputStream("assets/Fan" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);   
    }

    @Override
    public void run() {
        this.sprite=super.getSprite();
        int x=super.getX();
        int y=super.getY();
        int num=0;
        
        while(true){
            try{
                Thread.sleep(400);
                if(this.typeItem==1){
                    if(this.up()){
                        super.setImage(this.sprite.get(num));
                        super.setY(super.getY()-this.size);
                        this.firstStart[0]++;
                    }else{
                        if(this.down()){
                            super.setImage(this.sprite.get(num));
                            super.setY(super.getY()+this.size);
                            this.firstStart[0]++;
                        }
                    }
                }else{
                    if(this.left()){
                        super.setImage(this.sprite.get(num));
                        super.setY(super.getY()-this.size);
                        this.firstStart[1]--;
                    }else{
                        if(this.rigth()){
                            super.setImage(this.sprite.get(num));
                            super.setY(super.getY()+this.size);
                            this.firstStart[1]++;
                        }
                    }
                }
                
                if(num==0){
                    num++;
                }else{
                    num=0;
                }
            }catch(InterruptedException ine){}
        }
    }
    
    public boolean up(){
        if(this.matriz[this.firstStart[0]+1][this.firstStart[1]]==0){
            return true;
        }
        return false;
    }
    
    public boolean down(){
        if(this.matriz[this.firstStart[0]-1][this.firstStart[1]]==0){
            return true;
        }
        return false;
    }
    
    public boolean left(){
        if(this.matriz[this.firstStart[0]][this.firstStart[1]-1]==0){
            return true;
        }
        return false;
    }
    
    public boolean rigth(){
        if(this.matriz[this.firstStart[0]][this.firstStart[1]+1]==0){
            return true;
        }
        return false;
    }
}