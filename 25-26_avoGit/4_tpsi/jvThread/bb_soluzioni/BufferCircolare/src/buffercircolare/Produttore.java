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
public class Produttore extends Thread {
    private IObserver observer;
    private Prodotto deposito;

    public Produttore(IObserver observer, Prodotto pezzo) {
        super("Produttore");
        this.observer = observer;
        this.deposito = pezzo;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                int pezzo = new Random().nextInt(1, 101);
                deposito.creaPezzo(pezzo);
                observer.mostra(getName(), pezzo);
                Thread.sleep(new Random().nextInt(200, 701));
            } catch (InterruptedException ex) {
                Logger.getLogger(Produttore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}