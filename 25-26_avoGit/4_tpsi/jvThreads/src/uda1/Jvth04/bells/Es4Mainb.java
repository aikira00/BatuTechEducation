package Jvth04.bells;

public class Es4Mainb {

    public static void main(String [] args){
        Runnable bell = new Es4Bellb("din", 5);
        Thread t1 = new Thread(bell);
        t1.start();
        Thread t2 = new Thread(new Es4Bellb("don", 5));
        t2.start();
        new Thread(new Es4Bellb("dan", 5)).start();
    }
}
