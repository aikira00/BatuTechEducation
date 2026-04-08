package prodCons.syncCoord;

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
                System.out.println(this.getName() + " dormo...");
                Thread.sleep(5000);
                int tmp = (int)Math.round(Math.floor(Math.random() * 10 + 1));
                prod.setElement(tmp);
                System.out.println(this.getName() + " ho prodotto: " + tmp);
            }
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " sono stato interrotto ");
        }

    }
}
