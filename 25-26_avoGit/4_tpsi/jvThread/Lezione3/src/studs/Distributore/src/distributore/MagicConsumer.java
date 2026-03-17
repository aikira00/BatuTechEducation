/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distributore;

/**
 *
 * @author adam
 */
public class MagicConsumer extends Thread {

    private String name;
    private MagicCandyFactory magicFac;
    private boolean stop = false;

    public MagicConsumer(String name, MagicCandyFactory magicFac) {
        this.setName(name);
        this.magicFac = magicFac;
    }

    public void run() {
        int n_consumato = 0;
        while (stop == false) {
            n_consumato = magicFac.consume(stop);
            if (stop){
                System.out.println(Thread.currentThread().getName() + " STA PER FINIRE LA SUA ESECUZIONE."); 
                break;
            }
                
        }
        
        
        System.out.println(Thread.currentThread().getName() + " ha finito.");
    }

    public boolean isStop() {
        return stop;
    }

   public void fermaStop() {
        this.stop = true;
    }
    
    
}
