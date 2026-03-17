public class Lez01SneakyCounter extends Lez01Counter{

    @Override
    public void increment() {
        int tmp = c;
        /*try{
            Thread.sleep(20);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + "sono stato interrotto, termino!");
            return;
        }*/
        c = tmp + 1;
        System.out.println(
                Thread.currentThread().getName() +
                        ": il contatore segna " + c);
    }

    @Override
    public void decrement() {
        int tmp = c;
        /*try{
            Thread.sleep(20);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + "sono stato interrotto, termino!");
            return;
        }*/
        c = tmp - 1;
        System.out.println(
                Thread.currentThread().getName() +
                        ": il contatore segna " + c);
    }

}
