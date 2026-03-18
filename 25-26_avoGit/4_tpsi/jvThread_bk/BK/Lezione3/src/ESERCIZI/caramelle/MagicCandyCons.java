package ESERCIZI.caramelle;

import java.util.Random;

public class MagicCandyCons extends Thread {

    private MagicCandyFactory distributore;
    private boolean stop = false;
    private Random random;

    public MagicCandyCons(MagicCandyFactory d) {
        this.distributore = d;
        this.random = new Random();
    }

    public void run() {
        while(!stop) {
            //genero casuale caramelle
            int n = random.nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName() + " prova a consumare " + n + " caramelle");
            distributore.preleva(n);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }

    public void stopCandyConsumer(){
        this.stop = true;
    }
}
