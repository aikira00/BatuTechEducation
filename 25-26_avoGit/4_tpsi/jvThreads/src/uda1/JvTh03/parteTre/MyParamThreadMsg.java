package JvTh03.parteTre;

public class MyParamThreadMsg extends Thread {

    private int delay;
    private String message;

    public MyParamThreadMsg(int delay, String message) {
        this.delay = delay;
        this.message = message;
    }

    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() +
                " in esecuzione con delay " + delay + "ms e messaggio: " + message);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " terminato.");
    }
}
