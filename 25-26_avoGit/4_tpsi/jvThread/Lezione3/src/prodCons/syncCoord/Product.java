package prodCons.syncCoord;

//classe Monitor
public class Product {

    private int element;

    private boolean produced; // variabile guardia

    public Product(int n){

        this.element = n;
        this.produced = false;
    }

    public synchronized void setElement(int element) {
        while(produced){
            try{
                System.out.println(Thread.currentThread().getName() + " aspetto");
                wait();
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre aspettavo di poter produrre");
            }
        }

        this.element = element;
        produced = true;
        notifyAll();
    }

    public synchronized int getElement(){

        while(!produced){
            try{
                System.out.println(Thread.currentThread().getName() + " aspetto");
                wait();
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre aspettavo di poter consumare");
            }
        }
        produced = false;
        notifyAll();
        return element;
    }
}
