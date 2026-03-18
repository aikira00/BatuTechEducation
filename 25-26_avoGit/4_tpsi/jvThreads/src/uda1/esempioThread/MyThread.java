package esempioThread;

public class MyThread extends Thread {
    @Override
    public void run() {

        System.out.println("Esecuzione del thread: " + Thread.currentThread().getName());

        System.out.println("Sono un thread");
        System.out.println("nome " + this.getName());
        System.out.println("priorità " + this.getPriority());
        System.out.println("stato " + this.getState());
        System.out.println("gruppo " + this.getThreadGroup());
        System.out.println("sono interrotto? " + this.isInterrupted());
        System.out.println("sono vivo? " + this.isAlive());
        System.out.println("toString " + this.toString());
        this.setName("FRED");
        System.out.println("nome " + this.getName());
    }
}
