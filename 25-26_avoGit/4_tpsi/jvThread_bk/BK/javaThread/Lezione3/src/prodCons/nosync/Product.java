package prodCons.nosync;

//classe Monitor
public class Product {

    private int element;

    public Product(int n){
        this.element = n;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public int getElement(){
        return this.element;
    }
}
