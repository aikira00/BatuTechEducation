/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distributore;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam
 */
public class MagicCandyFactory {

    private int nDolcettiProduce;
    private int nDolcettiConsume;
    private int mDolcetti;
    private int MAX_DOLCI;
    private int bufferConsume = 0;
    private int bufferProduce = 0;

    public MagicCandyFactory(int MAX_DOLCI) {
        this.MAX_DOLCI = MAX_DOLCI;
    }

    public synchronized int produce(boolean stop) {
        while (mDolcetti == MAX_DOLCI && stop == false) {
                try {
                    System.out.println("ASPETTA " + Thread.currentThread().getName() + "\n");
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println("Interruzione scatenata per " + Thread.currentThread().getName() + " TERMINE\n");
                    return -1;
                }
            

        }
        

        if (bufferProduce != 0) {
            nDolcettiProduce = bufferProduce;
            bufferProduce = 0;
        } else {
            nDolcettiProduce = (int) (Math.random() * MAX_DOLCI + 1);
        }

        if(mDolcetti + nDolcettiProduce > MAX_DOLCI){
            mDolcetti = MAX_DOLCI;
            bufferProduce = mDolcetti + nDolcettiProduce - MAX_DOLCI;
            System.out.println(Thread.currentThread().getName() + " ha prodotto: " + nDolcettiProduce + " caramelle.");
            System.out.println("Dolcetti nel buffer: " + bufferProduce);
            System.out.println("Caramelle (cond) nel distributore: " + mDolcetti + "\n");
            notifyAll();
            return nDolcettiProduce = MAX_DOLCI - mDolcetti; 
        }
        
        mDolcetti += nDolcettiProduce;

        System.out.println(Thread.currentThread().getName() + " ha prodotto: " + nDolcettiProduce + " caramelle.");
        System.out.println("Caramelle nel distributore: " + mDolcetti + "\n");
        
        notifyAll();

        return nDolcettiProduce;

    }

    public synchronized int consume(boolean stop) {
        if (bufferConsume == 0) {
            nDolcettiConsume = (int) (Math.random() * MAX_DOLCI + 1);
            bufferConsume = 0;
        }
        
        while (mDolcetti - nDolcettiConsume < 0 && stop == false) {
            try {
                bufferConsume = nDolcettiConsume;
                System.out.println("ASPETTA " + Thread.currentThread().getName() + "\n");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Interruzione scatenata per " + Thread.currentThread().getName() + " TERMINE");
                return -1;
            }
        }

        mDolcetti -= nDolcettiConsume;

        System.out.println(Thread.currentThread().getName() + " ha consumato: " + nDolcettiConsume + " caramelle.");
        System.out.println("Caramelle nel distributore: " + mDolcetti + "\n");
        
        notifyAll();

        return nDolcettiConsume;
    }

}
