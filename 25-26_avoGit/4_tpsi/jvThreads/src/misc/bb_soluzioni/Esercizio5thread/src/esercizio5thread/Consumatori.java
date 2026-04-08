/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio5thread;

import java.util.Random;

/**
 *
 * @author palma
 */
public class Consumatori extends Thread{
    
    private IObserver observer;
    private Deposito deposito;
    private Random random;
    private Betoniera betoniera;
    
    public Consumatori( Deposito deposito,IObserver observer) {
        this.observer = observer;
        this.deposito = deposito;
        random=new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run(){
        while(true){
            betoniera=new Betoniera(deposito,observer);
            betoniera.start();
            int value=random.nextInt(100);
            if(value>75){
                betoniera=new Betoniera(deposito,observer);
                betoniera.start();
            }
            if (value>95){
                betoniera=new Betoniera(deposito,observer);
                betoniera.start();
            }
            try {
                Thread.sleep(random.nextInt(5000)+5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
}
