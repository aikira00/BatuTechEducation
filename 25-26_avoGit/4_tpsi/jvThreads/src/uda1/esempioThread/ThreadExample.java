package esempioThread;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread();
        t1.setName("Mio=thread");
        t1.start();
        //cosa succede se aggiungiamo questa istruzione?
        //t1.join();
        System.out.println("Thread principale: " + Thread.currentThread().getName());
        System.out.println("Dove sarà questa stampa?");
    }
}