package prodCons.syncCoordMore;

//classe Monitor
public class Product {

    private final int capacity;
    private int producedElements;

    public Product(int n){

        this.capacity = n;
        this.producedElements = 0;
    }

    public synchronized void produce(int numElements) {
        while(producedElements > capacity){ //buffer pieno
            try{
                System.out.println(Thread.currentThread().getName() + " aspetto di produrre " + numElements + " elementi");
                wait();
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre aspettavo di poter produrre");
            }
        }
        int elementsToProduce = numElements;
        if(producedElements+numElements > capacity){
            elementsToProduce = capacity - producedElements; // Calcola quanti elementi è possibile produrre senza superare la capacità
            System.out.println(Thread.currentThread().getName() + " volevo produrre " + numElements + " ma produco meno");
        }

        System.out.println(Thread.currentThread().getName() + " sto producendo: " + elementsToProduce + " elementi");
        try{
            Thread.sleep(6000);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre consumavo");
        }
        this.producedElements += elementsToProduce;
        System.out.println(Thread.currentThread().getName() + " Ora ci sono " + producedElements + " elementi " + " su una capacità di " + capacity);
        notifyAll();
    }

    public synchronized void consume(int numElements){

        while(producedElements <= 0){//buffer vuoto
            try{
                System.out.println(Thread.currentThread().getName() + " aspetto di consumare " + numElements + " elementi");
                wait();
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre aspettavo di poter consumare");
            }
        }


        int elementsToConsume = numElements;
        if(producedElements < numElements) {//se si cerca di consumare di più di quello che c'è
            elementsToConsume = producedElements;
            System.out.println(Thread.currentThread().getName() + " volevo consumare " + numElements + " ma consumo meno");
        }
        System.out.println(Thread.currentThread().getName() + " sto consumando: " + elementsToConsume + " elementi");
        try{
            Thread.sleep(6000);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre consumavo");
        }
        this.producedElements -= elementsToConsume;
        System.out.println(Thread.currentThread().getName() + " Ora ci sono " + producedElements + " elementi " + " su una capacità di " + capacity);
        notifyAll();
    }

    public synchronized int getCapacity(){
        return capacity;
    }

    public synchronized int getProducedElements(){
        return producedElements;
    }

}
