/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buffercircolare;

import java.util.Vector;

/**
 *
 * @author Sistinformatici PC 4
 */
public class BufferCircolare {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IObserver observer = new Observer();
        Prodotto deposito = new Prodotto(10);
        Produttore produttore = new Produttore(observer, deposito);
        Consumatore consumatore = new Consumatore(observer, deposito);
        
        produttore.start();
        consumatore.start();
    }
    
}
