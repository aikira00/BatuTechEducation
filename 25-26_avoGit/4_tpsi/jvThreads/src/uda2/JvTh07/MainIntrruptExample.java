package JvTh07;

public class MainIntrruptExample {

    public static void main(String[] args) {
// Creazione e avvio del thread
        Thread myThread = new MyThread("Thread-1", 5);
        myThread.start();
        System.out.println("Thread principale: Il thread è stato avviato.");

        // Aspetta un po' prima di interrompere il thread
        try {
            System.out.println("Thread principale: Aspetto 2 secondi prima di interrompere il thread.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Interrompi il thread
        System.out.println("Thread principale: Interrompo il thread.");
        myThread.interrupt();
        System.out.println("Thread principale: thread interrotto.");
    }
}