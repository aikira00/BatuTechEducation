package prodCons.sync;

//classe Monitor
public class Product {

    private int element;

    public Product(int n){
        this.element = n;
    }

    public synchronized void setElement(int element) {
        this.element = element;
    }

    public synchronized int getElement(){
        return this.element;
    }
}
