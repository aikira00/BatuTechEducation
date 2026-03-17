/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distributore;

/**
 *
 * @author adam
 */
public class MagicProducer extends Thread {

    private String name;
    private MagicCandyFactory magicFac;
    private boolean stop = false;

    public MagicProducer(String name, MagicCandyFactory magicFac) {
        this.setName(name);
        this.magicFac = magicFac;
    }

    public void run() {
        int n_prodotto = 0;
        while (stop == false) {
            n_prodotto = magicFac.produce(stop);
            if (stop) {
                System.out.println(Thread.currentThread().getName() + " STA PER FINIRE LA SUA ESECUZIONE.");
                break;
            }

        }

        System.out.println(Thread.currentThread().getName() + " ha finito.\n");
    }

    public boolean isStop() {
        return stop;
    }

    public void fermaStop() {
        this.stop = true;
    }
}
