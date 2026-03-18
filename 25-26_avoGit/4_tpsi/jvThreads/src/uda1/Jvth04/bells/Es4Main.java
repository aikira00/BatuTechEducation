package Jvth04.bells;

public class Es4Main {

    public static void main(String [] args){
        Runnable bell = new Es4Bell("qui", 5);
        Thread t1 = new Thread(bell);
        t1.start();
        Thread t2 = new Thread(new Es4Bell("quo", 5));
        t2.start();
        new Thread(new Es4Bell("qua", 5)).start();
    }
}
