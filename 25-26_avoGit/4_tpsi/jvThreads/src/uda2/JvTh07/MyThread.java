package JvTh07;

public class MyThread extends Thread {
    private String name;
    private int counter;

    public MyThread(String name, int counter) {
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " avviato.");
        try {
            for (int i = 0; i < counter; i++) {
                if (Thread.interrupted()) { // Controllo del flag di interruzione
                    System.out.println("Thread " + name + " interrotto.");
                    return; // Terminazione pulita del thread
                }
                System.out.println("Thread " + name + ": Contatore = " + i);
                Thread.sleep(1000); // Simula un'attività del thread
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + " interrotto durante sleep.");
        }
        System.out.println("Thread " + name + " terminato.");
    }
}