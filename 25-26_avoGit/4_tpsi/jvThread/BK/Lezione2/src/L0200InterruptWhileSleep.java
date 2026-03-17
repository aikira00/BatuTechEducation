public class L0200InterruptWhileSleep {
    public static void main(String[] args) {
        Thread myThread = new MyThreadSleep();
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

class MyThreadSleep extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");
            try {
                Thread.sleep(500); // Simuliamo un'attività
            } catch (InterruptedException e) {
                System.out.println("Thread interrotto!");
                //break; //se thread interrotto il thread, esco da while
                return; //ancora meglio

            }
        }
        //se return nel catch questo non viene eseguito
        System.out.println("Sono vivo: " + this.isAlive());
        // Utilizzando interrupted(), possiamo controllare lo stato dell'interruzione
        // ma questa può essere già stata azzerata da un' eccezione mentre il thread era in sleep
        if (Thread.interrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }
    }
}
