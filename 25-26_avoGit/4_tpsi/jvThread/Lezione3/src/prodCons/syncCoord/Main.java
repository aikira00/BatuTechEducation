package prodCons.syncCoord;

public class Main {
    public static void main(String[] args) {
        Product p = new Product(0);//monitor, sezione critica
        Consumer c1 = new Consumer(p,"C1", 5);
        Producer p1 = new Producer(p,"P1", 5);
        p1.start();
        c1.start();
        try{
            p1.join();
            c1.join();
        }
        catch (InterruptedException e){
            throw new RuntimeException();
        }
    }
}
