/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miosemaforo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Produttore extends Thread{
    private Condivisa condivisa;

    public Produttore(Condivisa condivisa) {
        this.condivisa = condivisa;
    }

    @Override
    public void run() {
        super.run(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        while(true){
            try {
                Thread.sleep(new Random().nextInt(100,501));
                int tmp = new Random().nextInt(1,101);
                condivisa.set(tmp);
                System.out.println("Produttore: "+tmp);
            } catch (InterruptedException ex) {
                Logger.getLogger(Produttore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
