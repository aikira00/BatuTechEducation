package prodCons.syncCoordMore;

public class Main {
    public static void main(String[] args) {
        Product p = new Product(10);//monitor, sezione critica
        Consumer c1 = new Consumer(p,"C1", 5);
        Consumer c2 = new Consumer(p,"C2", 5);
        Producer p1 = new Producer(p,"P1", 5);
        Producer p2 = new Producer(p,"P2", 5);
        p1.start();
        c1.start();
        p2.start();
        c2.start();
        try{
            p1.join();
            c1.join();
            p2.join();
            c2.join();
        }
        catch (InterruptedException e){
            throw new RuntimeException();
        }
    }
}
