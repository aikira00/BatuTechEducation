public class L0200InterruptWhile_before {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        // Interrumpiamo il thread dopo 2 secondi
        myThread.interrupt();
        System.out.println("Ho interrotto il thread...");
    }
}

class MyThread_before extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");

        }
        System.out.println("Sono vivo: " + this.isAlive());
        // Utilizzando interrupted(), possiamo controllare lo stato dell'interruzione
        if (Thread.interrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }
    }
}
