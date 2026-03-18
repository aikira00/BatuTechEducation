package JvTh02;

import java.util.ArrayList;

public class EsploraThread {
    public static void main(String[] args) {

        // ── 1. Esaminiamo il thread corrente (main) ──
        Thread main = Thread.currentThread();
        System.out.println("=== THREAD MAIN ===");
        System.out.println("currentThread().getName()    : " + main.getName());
        System.out.println("currentThread().getId() DEPRECATED     : " + main.getId());
        System.out.println("currentThread().threadId() <= usare questo     : " + main.threadId());
        System.out.println("currentThread().getPriority() : " + main.getPriority());
        System.out.println("currentThread().isAlive()    : " + main.isAlive());
        System.out.println("currentThread().getThreadGroup()    : " + main.getThreadGroup());
        System.out.println("currentThread().toString()   : " + main.toString());
        System.out.println();

        // Creiamo 5 thread e li salviamo in un ArrayList
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new MyThread());
            t.setName("Thread " + i);
            //cosa succede se metto t.join()?
            threads.add(t);
        }

        // Prima dello start: isAlive() è false
        System.out.println("=== PRIMA DELLO START ===");
        for (Thread t : threads) {
            System.out.println(t.getName() + " isAlive() = " + t.isAlive());
            System.out.println(t.getName() + " isInterrupted() = " + t.isInterrupted());
        }
        System.out.println();

        // Avviamo tutti i thread
        System.out.println("=== AVVIO ===");
        for (Thread t : threads) {
            t.start();
            // Domanda: cosa succede se metto t.join() qui?
            // t.join();
        }

        // Attendiamo la terminazione di tutti
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println();
        System.out.println("=== DOPO IL JOIN ===");
        for (Thread t : threads) {
            System.out.println(t.getName() + " isAlive() = " + t.isAlive());
            System.out.println(t.getName() + " isInterrupted() = " + t.isInterrupted());
            System.out.println(t.getName() + " isInterrupted() = " + t.isInterrupted());
        }

        System.out.println("\n=== FINE ===");

        System.out.println("=== FINE ===");

    }
}