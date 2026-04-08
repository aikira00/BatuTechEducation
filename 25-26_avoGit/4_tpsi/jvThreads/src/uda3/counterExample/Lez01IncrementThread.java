public class Lez01IncrementThread extends Thread {

    private Lez01Counter counter;
    private int times;

    public Lez01IncrementThread(Lez01Counter counter, int times) {
        this.counter = counter;
        this.times = times;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            //System.out.println(getName() + " counter vale " + counter.getValue());
            //System.out.println(getName() + " eseguo incremento " + i + " volta");
            counter.increment();
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