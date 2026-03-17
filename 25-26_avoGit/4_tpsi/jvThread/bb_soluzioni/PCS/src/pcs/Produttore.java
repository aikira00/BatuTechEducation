/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcs;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Produttore extends Thread{
    private IObserver observer;
    private Prodotto prodotto; 

    public Produttore(IObserver observer, Prodotto prodotto) {
        super("Produttore");
        this.observer = observer;
        this.prodotto = prodotto;
    }
    
    public void run(){
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep((int)(Math.random()*2000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Produttore.class.getName()).log(Level.SEVERE, null, ex);
            }
            int valore=(int)(Math.random()*10+1);
            prodotto.setValore(valore);
            observer.mostra(getName(), valore);
        }
    }
    
    
}
