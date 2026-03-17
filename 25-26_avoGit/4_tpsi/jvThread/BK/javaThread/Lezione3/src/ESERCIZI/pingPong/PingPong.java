package ESERCIZI.pingPong;

public class PingPong {
    private boolean isPongComming;

    public synchronized void ping() {
        while (isPongComming) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isPongComming = true;
        notify();

        System.out.println(Thread.currentThread().getName() + " Ping");
    }

    public synchronized void pong() {
        while (!isPongComming) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isPongComming = false;
        notify();

        System.out.println("    "+ Thread.currentThread().getName() + " Pong");
    }
}
