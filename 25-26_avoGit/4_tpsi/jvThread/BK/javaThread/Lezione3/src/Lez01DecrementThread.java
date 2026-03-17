public class Lez01DecrementThread extends Thread {

    private Lez01Counter counter;
    private int times;


    public Lez01DecrementThread(   Lez01Counter counter, int times) {
        this.counter = counter;
        this.times = times;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            //System.out.println(getName() + " counter vale " + counter.getValue());
            //System.out.println(getName() + " eseguo decremento " + i + " volta");
            counter.decrement();
            //System.out.println(getName() + " counter vale " + counter.getValue());
            //System.out.println(getName() + "ora dormo");
            try{
               Thread.sleep(10);
            }
            catch(InterruptedException e){
                System.out.println(getName() + "sono stato interrotto, termino!");
                return;
            }
        }
        System.out.println(getName() + "ho finito!");
    }
}