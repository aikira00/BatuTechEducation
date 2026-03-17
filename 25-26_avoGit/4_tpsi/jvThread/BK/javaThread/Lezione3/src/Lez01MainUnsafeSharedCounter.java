public class Lez01MainUnsafeSharedCounter {
    public static void main(String[] args) throws InterruptedException {

        //oggetto condiviso
        Lez01Counter counter = new Lez01Counter();
        int times = 5;

        Lez01IncrementThread threadUno =
                new Lez01IncrementThread(counter, times);

        Lez01DecrementThread threadDue =
                new Lez01DecrementThread(counter, times);

        threadUno.start();
        threadDue.start();

        threadUno.join();
        threadDue.join();
        System.out.println(counter.getValue());

    }
}
