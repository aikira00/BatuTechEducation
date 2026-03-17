public class EsCountInterruptMain {

    public static void main(String[] args) {
        EsCountInterruptThread rnbl = new EsCountInterruptThread();
        Thread myThread = new Thread(rnbl);
        myThread.start();

        for(int i=0;i<10;i++){
            try {
                Thread.sleep(2000); // Attendiamo 2 secondi
            } catch (InterruptedException e) {
                System.out.println("Main interrotto");
            }

            // Interrumpiamo il thread dopo 2 secondi
            myThread.interrupt();
            System.out.println("Ho interrotto il thread...");
        }
        try {
            Thread.sleep(2000); // Attendiamo 2 secondi
        } catch (InterruptedException e) {
            System.out.println("Main interrotto");
        }
        System.out.println("Ho interroto il thread " + rnbl.getCountInterrupt() + " volte!");
        rnbl.setCntLoop(false);


    }
}
