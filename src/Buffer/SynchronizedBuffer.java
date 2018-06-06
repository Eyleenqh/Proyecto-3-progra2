/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer;

/**
 *
 * @author Eyleen
 */
public class SynchronizedBuffer implements Buffer {

    private boolean pos = false;

    @Override
    public synchronized boolean move(int direction, int[][] matrix, int[] startPoint) {
        this.pos = false;
        //derecha
        if (direction == 1) {
            if (matrix[startPoint[0]][startPoint[1] + 1] == 2) {
                pos = true;
                return false;
            }
            if (matrix[startPoint[0]][startPoint[1] + 1] == 0 || matrix[startPoint[0]][startPoint[1] + 1] == 3) {
                return true;
            } else {
                return false;
            }

        }
        //izquierda
        if (direction == 2) {
            if (matrix[startPoint[0]][startPoint[1] - 1] == 3) {
                pos = true;
                return false;
            }
            if (matrix[startPoint[0]][startPoint[1] - 1] == 0 || matrix[startPoint[0]][startPoint[1] - 1] == 2
                    || matrix[startPoint[0]][startPoint[1] - 1] == 8) {
                return true;
            } else {
                return false;
            }

        }
        //abajo
        if (direction == 3) {
            if (matrix[startPoint[0] + 1][startPoint[1]] == 4) {
                pos = true;
                return false;
            }
            if (matrix[startPoint[0] + 1][startPoint[1]] == 0 || matrix[startPoint[0] + 1][startPoint[1]] == 5) {
                return true;
            } else {
                return false;
            }

        }
        //arriba
        if (direction == 4) {
            if (matrix[startPoint[0] - 1][startPoint[1]] == 5) {
                pos = true;
                return false;
            }
            if (matrix[startPoint[0] - 1][startPoint[1]] == 0 || matrix[startPoint[0] - 1][startPoint[1]] == 4) {
                return true;
            } else {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean getPos() {
        return this.pos;
    }

} // end class SynchronizedBuffer
