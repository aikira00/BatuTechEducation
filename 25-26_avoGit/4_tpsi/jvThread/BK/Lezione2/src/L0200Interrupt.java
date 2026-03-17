public class L0200Interrupt extends Thread {
   private String name;
   private int counter;

   public L0200Interrupt(String name, int counter) {
       this.name = name;
       this.counter = counter;
   }

   @Override
   public void run() {
       System.out.println("Thread " + name + " avviato.");
       try {
           for (int i = 0; i < counter; i++) {
               System.out.println("Thread " + name + ": Contatore = " + i);
               Thread.sleep(1000); // Simula un'attività del thread
           }
       } catch (InterruptedException e) {
           System.out.println("Thread " + name + " interrotto.");
       }
       System.out.println("Thread " + name + " terminato.");
   }

    public static void main(String[] args) {
        // Creazione e avvio del thread
        Thread myThread = new L0200Interrupt("Thread-1", 5);
        myThread.start();

        System.out.println("Thread principale: Il thread è stato avviato.");

        // Aspetta un po' prima di interrompere il thread
        try {
            System.out.println("Thread principale: Aspetto 2 secondi prima di interrompere il thread.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrompi il thread
        System.out.println("Thread principale: Interrompo il thread.");
        myThread.interrupt();
        System.out.println(myThread.getName() + " è interrotto: " + myThread.isInterrupted());
        System.out.println(myThread.getName() + " è interrotto: " + myThread.isInterrupted());
        System.out.println(myThread.getName() + " è vivo: " + myThread.isAlive());

        System.out.println("Thread principale: thread interrotto.");
    }
}
