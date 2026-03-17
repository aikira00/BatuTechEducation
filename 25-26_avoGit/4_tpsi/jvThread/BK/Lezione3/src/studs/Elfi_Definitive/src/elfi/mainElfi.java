/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package elfi;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adamr
 */
public class mainElfi {
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_OP = 10;
        final int MAX_MAGAZZINO = 40;
        final int MAX_TWINKY = 50;
        final int MAX_BLINKY = 10;

        System.out.println("Main ha iniziato ad eseguire\n");
        
        List<String> listaBabbo = new ArrayList<>();

        //Creazione della lista regalo

        //Creazione dei thread
        ArrayList<Twinky> listaProducer = new ArrayList<>();
        ArrayList<Blinky> listaConsumer = new ArrayList<>();
        
        listaRegalo magicFac = new listaRegalo(MAX_MAGAZZINO);
        
        for (int i = 0; i < MAX_OP; i++) { //primitivi => stack, oggetti => heap
            listaProducer.add(new Twinky(magicFac, "Twinky " + i, MAX_TWINKY));
            listaConsumer.add(new Blinky(magicFac, "Blinky " + i, MAX_BLINKY));
        }
        
        magicFac.setBlinkys(listaConsumer.size());
        magicFac.setTwinkys(listaProducer.size());
        
        for (int i = 0; i < MAX_OP; i++) {
            listaProducer.get(i).start();
            listaConsumer.get(i).start();
        }
        
        for (int i = 0; i < MAX_OP; i++) {
            try {
                listaProducer.get(i).join();
                listaConsumer.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(mainElfi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        for(int i = magicFac.getMagazzino().size() - 1; i > 0; i--){
            if(magicFac.getMagazzino().get(i).equals("stop")){
                magicFac.getMagazzino().remove(i);
            }
        }
        
        for(int i = 0; i < MAX_OP; i++){
            System.out.println("Regali prodotti: " + listaProducer.get(i).getProdottoList().size());
            System.out.println(listaProducer.get(i).getNome());
            System.out.println(Arrays.toString(listaProducer.get(i).getProdottoList().toArray()));
            System.out.println("Regali spediti: " + listaConsumer.get(i).getSpeditoList().size());
            System.out.println(listaConsumer.get(i).getNome());
            System.out.println(Arrays.toString(listaConsumer.get(i).getSpeditoList().toArray()));            
        }
        
        System.out.println("Elementi nel magazzino: " + (magicFac.getMagazzino().size()));
        
        System.out.println("Stampa del magazzino: ");
        
        System.out.println(Arrays.toString(magicFac.getMagazzino().toArray()));
        
        System.out.println("Main ha finito di eseguire");
    }
    
}
