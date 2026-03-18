package esempioRunnable;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Esecuzione del thread: " + Thread.currentThread().getName());

        System.out.println("Sono un thread");
        System.out.println("nome " + Thread.currentThread().getName());
        System.out.println("priorità " + Thread.currentThread().getPriority());
        System.out.println("stato " + Thread.currentThread().getState());
        System.out.println("gruppo " + Thread.currentThread().getThreadGroup());
        System.out.println("sono interrotto? " + Thread.currentThread().isInterrupted());
        System.out.println("sono vivo? " + Thread.currentThread().isAlive());
        System.out.println("toString " + Thread.currentThread().toString());
        Thread.currentThread().setName("FRED");
        System.out.println("nome " + Thread.currentThread().getName());
    }
}
