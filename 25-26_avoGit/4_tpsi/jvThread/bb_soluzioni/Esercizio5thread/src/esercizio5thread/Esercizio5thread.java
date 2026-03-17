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
public class Esercizio5thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IObserver observer=new Observer();
        Deposito deposito=new Deposito(100, 30);
        Consumatori consumatori=new Consumatori(deposito,observer);
        Produttore cementificio=new Produttore(deposito, observer);
        cementificio.start();
        consumatori.start();
    }
    
}
