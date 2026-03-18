package JvTh01;

public class MyThreadRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int ms = 6000; // 6 secondi
                // Visualizzazione del nome del thread corrente e del tempo di
                // attesa
                System.out.println(Thread.currentThread().getName() + " sarà occupato per i prossimi " + ms + " millisecondi");
                // il thread è posto in sleep per 6000 millisecondi
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
