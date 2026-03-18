package JvTh02;

public class MyThread extends Thread {

    public void run() {
        System.out.println("Esecuzione del thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
