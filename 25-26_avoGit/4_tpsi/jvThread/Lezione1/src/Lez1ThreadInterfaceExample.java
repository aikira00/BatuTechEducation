
public class Lez1ThreadInterfaceExample implements Runnable{

    public void run(){
        System.out.println("Sono un thread");
        System.out.println("nome " + Thread.currentThread().getName());
        System.out.println("priorità " + Thread.currentThread().getPriority());
        System.out.println("stato " + Thread.currentThread().getState());
        System.out.println("gruppo " + Thread.currentThread().getThreadGroup());
        System.out.println("sono interrotto? " + Thread.currentThread().isInterrupted());
        System.out.println("sono vivo? " + Thread.currentThread().isAlive());
        System.out.println("toString " + Thread.currentThread().toString());
        Thread.currentThread().setName("FRED");
        System.out.println("nome " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Creiamo il thread");
        Lez1ThreadInterfaceExample interfaceExample = new Lez1ThreadInterfaceExample();
        Thread myThread = new Thread(interfaceExample);
        System.out.println("Eseguiamo il thread");
        myThread.start();
        //sleep(3000);
        System.out.println("Fine");
    }
}