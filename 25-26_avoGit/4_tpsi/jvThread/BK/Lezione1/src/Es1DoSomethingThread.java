import java.util.Random;

public class Es1DoSomethingThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                // Creazione di un oggetto generatore di numeri casuali
                Random r = new Random();
                // Generazione di un numero casuale tra 0 e 3000 che sarà usato
                // come numero di millisecondi di attesa
                int ms = r.nextInt(3000);
                // Visualizzazione del nome del thread corrente e del tempo di
                // attesa
                System.out.println(Thread.currentThread().getName()
                        + " sarà occupato per i prossimi " + ms
                        + " millisecondi");
                // Il thread è posto in sleep per il valore random di
                // millisecondi precedentemente selezionato
                Thread.sleep(ms);
            } catch (InterruptedException ex) {
                System.out.println("Thread uscito dallo sleep per interruzione");
            }
        }
    }
}
