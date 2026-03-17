package prodCons.syncCoordMore;

public class Producer extends Thread{

    private Product prod;
    private int times;

    public Producer(Product prod, String name, int times){
        this.prod = prod;
        this.setName(name);
        this.times = times;
    }

    public void run(){
        try{
            for(int i = 0; i < times; i++ ){
                Thread.sleep(1000);
                int tmp = (int)Math.round(Math.floor(Math.random() *(10 - 1 + 1) + 1));
                prod.produce(tmp);

            }
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " sono stato interrotto ");
        }

    }
}
