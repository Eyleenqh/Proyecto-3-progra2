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

    @Override
    public synchronized boolean move(int direction, int[][] matrix, int[] startPoint) {
        //derecha
        if (direction == 1) {
            //valida que hay en la siguiente posicion
            if (matrix[startPoint[0]][startPoint[1] + 1] == 0 || matrix[startPoint[0]][startPoint[1] + 1] == 7) {
                return true;
            } else {
                return false;
            }

        }
        //izquierda
        if (direction == 2) {
            //valida que hay en la siguiente posicion
            if (matrix[startPoint[0]][startPoint[1] - 1] == 0 || matrix[startPoint[0]][startPoint[1] - 1] == 7) {
                return true;
            } else {
                return false;
            }

        }
        //abajo
        if (direction == 3) {
            //valida que hay en la siguiente posicion
            if (matrix[startPoint[0] + 1][startPoint[1]] == 0) {
                return true;
            } else {
                return false;
            }

        }
        //arriba
        if (direction == 4) {
            //valida que hay en la siguiente posicion
            if (matrix[startPoint[0] - 1][startPoint[1]] == 0) {
                return true;
            } else {
                return false;
            }

        }
        return true;
    }

} // end class SynchronizedBuffer
