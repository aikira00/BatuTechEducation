public class Es1SimpleThread {

    public static void main(String[] args) {
        // Creazione del primo thread dalla classe che implementa l'interfaccia
        // Runnable: si istanzia l'oggetto e poi lo si usa per creare un thread
        // istanziando un oggetto Thread
        Es1DoSomethingRunnable job = new Es1DoSomethingRunnable();
        // Il secondo parametro (facoltativo) assegna il nome al thread
        Thread thread1 = new Thread(job, "***THREAD 1***");
        // Avvio del thread
        thread1.start();
        // Creazione del secondo thread dalla classe che estende la classe
        // Thread: si istanzia l'oggetto direttamente usando la classe che estende
        // la classe Thread
        Es1DoSomethingThread thread2 = new Es1DoSomethingThread();
        // viene assegnato un nome al thread
        thread2.setName("***THREAD 2***");
        // avvio del thread
        thread2.start();

        // Visualizzazione fatta dal main thread
        System.out.println("Thread principale (" +
                Thread.currentThread().getName() + ") finito");
    }
}