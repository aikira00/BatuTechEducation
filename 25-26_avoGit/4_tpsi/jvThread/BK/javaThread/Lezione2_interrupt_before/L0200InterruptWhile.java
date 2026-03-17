public class L0200InterruptWhile {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(2000); // Attendiamo 2 secondi
        } catch (InterruptedException e) {
            System.out.println("Main interrotto");
        }

        // Interrumpiamo il thread dopo 2 secondi
        myThread.interrupt();
        System.out.println("Ho interrotto il thread...");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");
            try {
                Thread.sleep(500); // Simuliamo un'attività
            } catch (InterruptedException e) {
                System.out.println("Thread interrotto!"); //così abbiamo shadow exception
                //break; //se thread interrotto il thread, esco da while
                //return; //ancora meglio

            }
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
