/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miosemaforo;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Semaforo {
    private int permessi;
    
    public Semaforo(int permessi){
        this.permessi = permessi;
    }
    
    public synchronized void acquire() throws InterruptedException{
        if(permessi==0){
            wait();
        }
        permessi--;
    }
    
    public synchronized void release(){
        permessi++;
        notifyAll();
    }
}
