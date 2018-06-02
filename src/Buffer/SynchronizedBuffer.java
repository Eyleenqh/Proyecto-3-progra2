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
    public void move() {
        

    } // end class SynchronizedBuffer

}
