package JvTh03.parteTre;

class MyParamThread extends Thread {
    private int delay;
    public MyParamThread(int delay) {
        this.delay = delay;
    }
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() +
                " in esecuzione con delay " + delay + "ms.");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " terminato.");
    }
}