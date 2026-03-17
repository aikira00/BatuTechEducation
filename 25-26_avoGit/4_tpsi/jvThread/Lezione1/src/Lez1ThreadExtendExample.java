
public class Lez1ThreadExtendExample extends Thread{

    public void run(){
        System.out.println("Sono un thread");
        System.out.println("nome " + this.getName());
        System.out.println("priorità " + this.getPriority());
        System.out.println("stato " + this.getState());
        System.out.println("gruppo " + this.getThreadGroup());
        System.out.println("sono interrotto? " + this.isInterrupted());
        System.out.println("sono vivo? " + this.isAlive());
        System.out.println("toString " + this.toString());
        this.setName("FRED");
        System.out.println("nome " + this.getName());
    }

    public static void main(String[] args) {
        System.out.println("Creiamo il thread");
        Lez1ThreadExtendExample myThread = new Lez1ThreadExtendExample();
        System.out.println("Eseguiamo il thread");
        myThread.start();
        //sleep(3000);
        System.out.println("Dove sarà questa stampa?");
    }
}