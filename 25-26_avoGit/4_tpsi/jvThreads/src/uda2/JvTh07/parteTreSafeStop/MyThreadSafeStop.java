package JvTh07.parteTreSafeStop;

public class MyThreadSafeStop extends Thread {
    // Variabile di osservazione booleana per indicare se il thread deve terminare
    private boolean stop;

    public MyThreadSafeStop(boolean stop){
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


}
