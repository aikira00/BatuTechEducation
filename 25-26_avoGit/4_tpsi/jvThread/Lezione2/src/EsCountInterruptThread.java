public class EsCountInterruptThread implements Runnable {

    private int countInterrupt;

    public EsCountInterruptThread() {
        this.countInterrupt = 0;
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + " Thread in esecuzione...");
            try {
                Thread.sleep(500); // Simuliamo un'attività
            } catch (InterruptedException e) {
                System.out.println("Thread interrotto! " + countInterrupt + " volte!");
                countInterrupt ++;
            }
        }
    }

    public int getCountInterrupt() {return this.countInterrupt;}
}
