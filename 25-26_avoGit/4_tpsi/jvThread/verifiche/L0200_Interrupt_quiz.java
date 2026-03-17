package verifiche;

public class L0200_Interrupt_quiz  extends Thread {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // Codice del thread
            try {
                System.out.println("Il thread sta eseguendo...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Il thread è stato interrotto.");
                System.out.println("Flag interrotto è " + this.isInterrupted());
                // Possibile azione di pulizia o gestione dell'interruzione
                //Thread.currentThread().interrupt(); // Reimposta il flag di interruzione
                //oppure return o break
                // return;
            }
        }
    }

    public static void main(String[] args) {
        L0200_Interrupt_quiz thread = new L0200_Interrupt_quiz();
        thread.start();
        try {
            Thread.sleep(3000); // Attendi per 3 secondi
            thread.interrupt(); // Interruzione del thread dopo 3 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
