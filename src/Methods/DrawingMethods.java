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

    public DrawingMethods() throws FileNotFoundException {
        this.wall = new Image(new FileInputStream("assets/wall.jpg"));
        this.path = new Image(new FileInputStream("assets/path.jpg"));
        this.finish = new Image(new FileInputStream("assets/finish.jpg"));
        this.character = new Image(new FileInputStream("assets/piD2.png"));
    }

    public void drawMaze(GraphicsContext gc, int referenceMatrix[][], int exit, int start, int size) {
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
}
