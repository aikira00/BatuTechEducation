/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miosemaforo;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Condivisa {
    private Semaphore p;
    private Semaphore c;
    private int item;
    public Condivisa(){
        p = new Semaphore(1);
        c = new Semaphore(0);
    }
    public int get(){
        try {
            c.acquire();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        int tmp = item;
        p.release();
        return tmp;
    }
    
    public void set(int n){
        try {
            p.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Condivisa.class.getName()).log(Level.SEVERE, null, ex);
        }
        item = n;
        c.release();
    }
}
