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
public class Prodotto {

    private int valore;
    private boolean scrivibile;

    public Prodotto() {
        scrivibile = true;
    }

    public synchronized int getValore() {
        if (scrivibile) {
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        int buffer = valore;
        scrivibile = true;
        notifyAll();
        return buffer;
    }

    public synchronized void setValore(int valore) {
        if (!scrivibile) {
            try {
                wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        this.valore = valore;
        scrivibile = false;

        notifyAll();
    }

}
