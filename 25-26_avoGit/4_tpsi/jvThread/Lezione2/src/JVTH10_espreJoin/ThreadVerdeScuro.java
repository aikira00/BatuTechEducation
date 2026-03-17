package JVTH10_espreJoin;

public class ThreadVerdeScuro extends ThreadColor implements Runnable{

    public ThreadVerdeScuro(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        double res = c + b + a;
        System.out.println("VS: c + b + a => " + res);
        this.setResult(res);
    }
}
