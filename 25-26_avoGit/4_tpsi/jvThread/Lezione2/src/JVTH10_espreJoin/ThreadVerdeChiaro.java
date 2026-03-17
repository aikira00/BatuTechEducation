package JVTH10_espreJoin;

public class ThreadVerdeChiaro extends ThreadColor implements Runnable{

    public ThreadVerdeChiaro(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        double res = (double) (7-a) /(b+22);
        System.out.println("VC: (7-a) /(b+22) => " + res);
        this.setResult(res);
    }
}
