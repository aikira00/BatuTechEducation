package JvTh07.parteTreSafeStop;

public class MainSafeStop {
    public static void main(String[] args) {

        MyThreadSafeStop counterThread = new MyThreadSafeStop(false);
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
