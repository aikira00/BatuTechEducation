package JVTH10_espreJoin;

public class ThreadGialloChiaro extends ThreadColor implements Runnable{

    public ThreadGialloChiaro(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        double res = (double) (3 * a + 5) /(b-c);
        System.out.println("GC: (3 * a + 5) /(b-c) => " + res);
        this.setResult(res);
    }
}
