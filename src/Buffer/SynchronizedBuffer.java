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

    private Character buffer = null; // shared by producer and consumer threads
    private int occupiedBufferCount = 0; // count of occupied buffers

    @Override
    public synchronized boolean move(int direction, int[][] matrix, int x, int y) {
        if(direction==1){
            if (matrix[y / 30][(x / 30) + 1] == 1) {
                return false;
            }else{
                return true;
            }
        }
        if(direction==2){
            if (matrix[y / 30][(x / 30)] == 1){
                return false;
            }else{
                return true;
            }
        }
        if(direction==3){
            if(matrix[(y / 30) + 1][(x / 30)] == 1){
                return false;
            }else{
                return true;
            }
        }
        if(direction==4){
            if(matrix[(y / 30)][(x / 30)] == 1){
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

} // end class SynchronizedBuffer
