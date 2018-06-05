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
    public synchronized boolean move(int direction, int[][] matrix, int[] startPoint) {
        if(direction==1){
            if (matrix[startPoint[0]][startPoint[1]+1] == 0) {
                return true;
            }else{
                return false;
            }
        }
        if(direction==2){
            if (matrix[startPoint[0]][startPoint[1]-1] == 0){
                return true;
            }else{
                return false;
            }
        }
        if(direction==3){
            if(matrix[startPoint[0]+1][startPoint[1]] == 0){
                return true;
            }else{
                return false;
            }
        }
        if(direction==4){
            if(matrix[startPoint[0]-1][startPoint[1]] == 0){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

} // end class SynchronizedBuffer
