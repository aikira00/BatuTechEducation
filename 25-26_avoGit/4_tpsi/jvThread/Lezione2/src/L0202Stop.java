public class L0202Stop extends Thread {
    // Variabile di osservazione booleana per indicare se il thread deve terminare
    private boolean stop;

    public L0202Stop(boolean stop){
        this.stop = stop;
    }

    public void run() {
        int counter = 0;
        while (!stop && counter < 10000) {
            System.out.println("Contatore: " + counter++);
            try {
                Thread.sleep(500); // Attendiamo 500 millisecondi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.stop) {
            System.out.println("Richiesta di stop ricevuta.");
        } else {
            System.out.println("Conteggio completato.");
        }
        System.out.println("Fine metodo run.");
    }

    public void requestStop() {
        this.stop = true;
    }

    public static void main(String[] args) {

        L0202Stop counterThread = new L0202Stop(false);
        counterThread.start();

        // Simula una richiesta di stop dopo un certo periodo di tempo
        try {
            Thread.sleep(5000); // Attendiamo 500 millisecondi
            System.out.println(counterThread.getName() + " stato: " + counterThread.getState());
            counterThread.requestStop(); // Richiediamo lo stop del thread
            System.out.println("Ho richiesto lo stop.");
            System.out.println(counterThread.getName() + " stato: " + counterThread.getState());
            counterThread.join(); //per sincronizzare le stampe dopo e aspettare che thread elabori interrupt
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counterThread.getName() + " è interrotto: " + counterThread.isInterrupted());
        System.out.println(counterThread.getName() + " è interrotto: " + counterThread.isInterrupted());
        System.out.println(counterThread.getName() + " è vivo: " + counterThread.isAlive());
        System.out.println(Thread.currentThread().getName() + " è interrotto: " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + " è interrotto: " + Thread.interrupted());

    }
}
