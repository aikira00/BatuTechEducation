public class Lez01MainUnsafeSharedCounter {
    public static void main(String[] args)  {

        //oggetto condiviso
        Lez01Counter counter = new Lez01Counter();
        Lez01Counter counterDUe = new Lez01Counter();
        int times = 5;

        Lez01IncrementThread threadUno =
                new Lez01IncrementThread(counter, times);

        Lez01DecrementThread threadDue =
                new Lez01DecrementThread(counter, times);

        threadUno.start();
        threadDue.start();
        try{
                threadUno.join();
                threadDue.join();}
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println(counter.getValue());

    }
}
