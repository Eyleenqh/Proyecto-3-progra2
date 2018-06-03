/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

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
    
    private int[] startPos;
    private int time;
    private int direction=1;
    private int [][] matriz;
    ArrayList<Image> sprite;
    //constructores
    public FuriousCharacter() throws FileNotFoundException {
        super(0, 0, 0, 0);
        this.startPos=new int[2];
        this.time=0;
        this.setSprite();
    }
    public FuriousCharacter(int x, int y, int speed, int imageNum) throws FileNotFoundException {
        super(x, y, speed, imageNum);
        this.startPos=new int[2];
        this.time=5;
        this.setSprite();
    }
    //constructores
    
    //obtiene e inicializa la matriz, además define el punto de partida
    public void setMatriz(int[][] mat, int start){
        this.matriz=mat;
        for(int i=0; i<this.matriz.length; i++){
            for(int x=0; x<matriz.length; x++){
                if(matriz[i][x]==start){
                    this.startPos[0]=i;//fila
                    this.startPos[1]=x;//columna
                    super.setX(x*13);
                    super.setY(i*13);
                }
            }
        }
    }
    
    public int[][] getMatriz(){
        return this.matriz;
    }
    
    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("assets/paD" + (i + 1) + ".png")));
        }
        super.setSprite(sprite);
        
    }
    
    
    //corre el hilo
    @Override
    public void run() {
        this.sprite=super.getSprite();
        int x=super.getX();
        int y=super.getY();
        int num=0;
        
        while(true){
            try {
                
                if(this.direction==1 && this.rigth(startPos)){
                    super.setImage(this.sprite.get(num));
                    super.setX(super.getX()+13);
                    this.startPos[1]++;
                    Thread.sleep(400); 
                }else{
                    if(this.direction==2 && this.up(startPos)){
                        super.setImage(this.sprite.get(num));
                        super.setY(super.getY()-13);
                        this.startPos[0]++;
                        Thread.sleep(400);
                    }else{
                        if(this.direction==3 && this.down(startPos)){
                            super.setImage(this.sprite.get(num));
                            super.setY(super.getY()+13);
                            this.startPos[0]--;
                            Thread.sleep(400);
                        }else{
                            if(this.direction==0 && this.left(startPos)){
                                super.setImage(this.sprite.get(num));
                                super.setX(super.getX()-13);
                                this.startPos[1]--;
                                Thread.sleep(400);
                            }else{
                                //this.direction=(int)(Math.random()*4);
                            }//controla el movimiento hacia arriba
                        }//controla el movimiento hacia abajo
                    }//controla el movimiento hacia arriba
                }//controla movimiento a la derecha
                
                if(num==2){
                    num=0;
                }else{
                    num++;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FuriousCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    //control de matriz para dar movimiento
    private boolean up(int[] pos){
        if(this.matriz[pos[0]+1][pos[1]]==0){
            this.direction=2;
            return true;
        }
        this.direction=(int)(Math.random()*4);
        return false;
    }
    
    private boolean down(int[] pos){
        if(this.matriz[pos[0]-1][pos[1]]==0){
            this.direction=3;
            return true;
        }
        this.direction=(int)(Math.random()*4);
        return false;
    }
    
    private boolean rigth(int[] pos){
        if(this.matriz[pos[0]][pos[1]+1]==0){
            this.direction=1;
            return true;
        }
        this.direction=(int)(Math.random()*4);
        return false;
    }
    
    private boolean left(int[] pos){
        if(this.matriz[pos[0]][pos[1]-1]==0){
            this.direction=0;
            return true;
        }
        this.direction=(int)(Math.random()*4);
        return false;
    }
}
