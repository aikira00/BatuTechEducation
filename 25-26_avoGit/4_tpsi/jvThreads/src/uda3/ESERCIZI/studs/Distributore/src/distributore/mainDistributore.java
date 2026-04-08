/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package distributore;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam
 */
public class mainDistributore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_OP = 50;

        System.out.println("Main ha iniziato ad eseguire");

        MagicCandyFactory magicFac = new MagicCandyFactory(100000);

        //Creazione della lista regalo

        //Creazione dei thread
        ArrayList<MagicProducer> listaProducer = new ArrayList<>();
        ArrayList<MagicConsumer> listaConsumer = new ArrayList<>();
        for (int i = 0; i < MAX_OP; i++) { //primitivi => stack, oggetti => heap
            listaProducer.add(new MagicProducer("Producer " + i, magicFac));
            listaConsumer.add(new MagicConsumer("Consumer " + i, magicFac));
        }

        for (int i = 0; i < MAX_OP; i++) {
            listaProducer.get(i).start();
            listaConsumer.get(i).start();
        }
        
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(mainDistributore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < MAX_OP; i++) {
            listaProducer.get(i).fermaStop();
            listaConsumer.get(i).fermaStop();
        }        
        
        for (int i = 0; i < MAX_OP; i++) {
            try {
                listaProducer.get(i).join();
                listaConsumer.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(mainDistributore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        System.out.println("Main ha finito di eseguire");
    }

}
