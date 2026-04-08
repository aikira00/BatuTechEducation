/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buffercircolare;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Prodotto {
    private int[] valori;
    private int indexProd;
    private int indexCons;
    private boolean scrivibile;
    private boolean leggibile;
    
    public Prodotto(int dimensione) {
        this.valori = new int[dimensione];
        this.scrivibile = true;
        this.leggibile = false;
        this.indexProd = 0;
        this.indexCons = 0;
    }
    
    public synchronized void creaPezzo(int pezzo) {
        if (!scrivibile) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Prodotto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.valori[indexProd] = pezzo;
        indexProd = (indexProd + 1) % this.valori.length;
        this.leggibile = true;
        
        if (indexProd == indexCons) {
            this.scrivibile = false;
        }
        notifyAll();
    }
    
    public synchronized int getPezzo() {
        if (!leggibile) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Prodotto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int buffer = this.valori[indexCons];
        this.scrivibile = true;
        indexCons = (indexCons + 1) % this.valori.length;
        
        if (indexProd == indexCons) {
            this.leggibile = false;
        }
        notifyAll();
        
        return buffer;
    }
}