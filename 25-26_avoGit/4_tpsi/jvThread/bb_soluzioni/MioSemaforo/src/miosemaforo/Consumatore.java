
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
public class Consumatore extends Thread{
    private Condivisa condivisa;
    public Consumatore(Condivisa condivisa){
        this.condivisa = condivisa;
    }

    @Override
    public void run() {
        super.run(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        while(true){
            try {
                Thread.sleep(new Random().nextInt(100,501));
                System.out.println("Consumatore: "+condivisa.get());
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumatore.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     
        }
    }
    
}
