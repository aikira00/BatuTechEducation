package esempioMoltiThreads;

public class NonDetExMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "Thread-1");
        Thread t2 = new Thread(new Task(), "Thread-2");
        Thread t3 = new Thread(new Task(), "Thread-3");
        t1.start();
        t2.start();
        t3.start();

        //cosa succede se aggiungiamo t1.join(), t2.join() e t3.join()?
        System.out.println("Thread principale: " + Thread.currentThread().getName() + "finisce esecuzione ");
    }
}

