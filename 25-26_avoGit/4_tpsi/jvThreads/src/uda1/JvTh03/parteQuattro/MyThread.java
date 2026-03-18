package JvTh03.parteQuattro;

public class MyThread extends Thread{
    private int result;
    public void run() {
        result = (int) (Math.random() * 100); // Simula un calcolo
        System.out.println("Thread " + Thread.currentThread().threadId() + " ha calcolato il valore: " + result);
    }
    public int getResult() {
        return result;
    }
}
