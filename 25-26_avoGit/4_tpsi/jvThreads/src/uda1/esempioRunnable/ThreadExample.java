package esempioRunnable;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable());
        t1.setName("Thread-Runnable");
        t1.start();
        //cosa succede se aggiungiamo questa istruzione?
        //t1.join();
        System.out.println("Thread principale: " + Thread.currentThread().getName());
    }
}