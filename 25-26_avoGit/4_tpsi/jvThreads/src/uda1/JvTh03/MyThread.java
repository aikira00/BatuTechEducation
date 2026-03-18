package JvTh03;

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread " + Thread.currentThread().threadId() + " in esecuzione.");
        try {
            Thread.sleep(1000); // Simula un lavoro
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + Thread.currentThread().threadId() + " terminato.");
    }
}