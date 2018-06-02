/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Steven
 */
public class DrawingMethods {

    private Image wall;
    private Image path;
    private Image finish;
    private Image character;
    private int[][] matriz;

    public DrawingMethods() throws FileNotFoundException {
        this.wall = new Image(new FileInputStream("assets/wall.jpg"));
        this.path = new Image(new FileInputStream("assets/path.jpg"));
        this.finish = new Image(new FileInputStream("assets/finish.jpg"));
        this.character = new Image(new FileInputStream("assets/piD2.png"));
    }

    public void drawMaze(GraphicsContext gc, int referenceMatrix[][], int exit, int start, int size) {
        this.matriz = referenceMatrix;
        gc.clearRect(0, 0, 1920, 1080);
        for (int i = 0; i < referenceMatrix.length; i++) {
            for (int j = 0; j < referenceMatrix.length; j++) {
                if (referenceMatrix[i][j] == 0) {
                    gc.drawImage(this.path, j * size, i * size, size, size);
                } else {
                    if (referenceMatrix[i][j] == 1) {
                        gc.drawImage(this.wall, j * size, i * size, size, size);
                    } else {
                        if (referenceMatrix[i][j] == start) {
                            gc.drawImage(this.path, j * size, i * size, size, size);
                            gc.drawImage(this.character, j * size, i * size, size, size);
                        } else {
                            if (referenceMatrix[i][j] == exit) {
                                gc.drawImage(this.finish, j * size, i * size, size, size);
                            }
                        }
                    }
                }
            }
        }
    }

    //Metodo que agrega y quita paredes del laberinto
    public void removeOrAdd(GraphicsContext gc, int x, int y, int referenceMatrix[][], int exit, int start, int size) {
        if (referenceMatrix[y / size][x / size] == 1) {
            referenceMatrix[y / size][x / size] = 0;
        } else {
            if (referenceMatrix[y / size][x / size] == 0) {
                referenceMatrix[y / size][x / size] = 1;
            }
        }
        this.drawMaze(gc, referenceMatrix, exit, start, size);
    }

    public int[][] getMatrix() {
        return this.matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
}
