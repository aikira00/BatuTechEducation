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
public class Betoniera extends Thread{
    
    private Deposito deposito;
    private IObserver observer;
    
    public Betoniera(Deposito deposito, IObserver observer){
        this.deposito=deposito;
        this.observer=observer;
    }
    
    @Override
    public void run(){
        double livello=deposito.preleva(1);
        observer.mostra(livello);
    }
}
