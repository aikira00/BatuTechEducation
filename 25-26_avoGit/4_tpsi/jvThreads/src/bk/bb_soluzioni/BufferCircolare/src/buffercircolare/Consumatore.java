/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buffercircolare;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Consumatore extends Thread {
    private IObserver observer;
    private Prodotto deposito;

    public Consumatore(IObserver observer, Prodotto deposito) {
        super("Consumatore");
        this.observer = observer;
        this.deposito = deposito;
    }
    
    @Override
    public void run() {
        while (true) {
            int pezzo = deposito.getPezzo();
            observer.mostra(getName(), pezzo);
            try {
                Thread.sleep(new Random().nextInt(200, 701));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumatore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}