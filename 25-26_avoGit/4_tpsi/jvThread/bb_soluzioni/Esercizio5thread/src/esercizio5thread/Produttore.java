/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio5thread;

/**
 *
 * @author palma
 */
public class Produttore extends Thread{
    
    private Deposito deposito;
    private IObserver observer;
    
    public Produttore(Deposito deposito, IObserver observer){
        this.deposito=deposito;
        this.observer=observer;
    }
    
    public void run(){
        while(true){
            double livello=deposito.carica(0.1);
            observer.mostra(livello);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
