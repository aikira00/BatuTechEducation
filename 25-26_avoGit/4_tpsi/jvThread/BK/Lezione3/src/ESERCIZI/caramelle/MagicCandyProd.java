package ESERCIZI.caramelle;

import java.util.Random;

public class MagicCandyProd extends Thread{
    private MagicCandyFactory distributore;
    private boolean stop = false;
    private Random random;

    public MagicCandyProd(MagicCandyFactory d) {
        this.distributore = d;
        this.random = new Random();
    }

    public void run() {
       while(!stop) {
           //genero casuale caramelle
            int n = random.nextInt(10) + 1;
           System.out.println(Thread.currentThread().getName() + " prova a produrre " + n + " caramelle");
           distributore.deposita(n);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public void stopCandyProducer(){
        this.stop = true;
    }
}
