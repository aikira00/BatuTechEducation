package JvTh07.parteDue.sol;

public class MainInterruptWhile {

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(3000); // Attendiamo 2 secondi
        } catch (InterruptedException e) {
            System.out.println("Main interrotto");
        }

        // Interrumpiamo il thread dopo 2 secondi
        myThread.interrupt();
        System.out.println("Ho interrotto il thread...");
        System.out.println("Thread interrotto: " + myThread.isInterrupted());
        System.out.println("Thread vivo?: " + myThread.isAlive());
    }
}

