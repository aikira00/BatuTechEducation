package verifiche;

public class L0200_Interrupt_quiz2 extends Thread {
    private boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Il thread sta eseguendo...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Il thread è stato interrotto.");
                System.out.println("Flag interrotto è " + this.isInterrupted());
            }
        }
        System.out.println("Il thread ha terminato l'esecuzione.");
    }

    public static void main(String[] args) {
        L0200_Interrupt_quiz2 thread = new L0200_Interrupt_quiz2();
        thread.start();
        try {
            Thread.sleep(3000); // Attendi per 3 secondi
            thread.interrupt(); // Interruzione del thread dopo 3 secondi
            thread.join(); // Attendi che il thread termini
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
