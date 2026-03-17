package JVTH10_espreJoin;

public class ThreadGialloScuro extends ThreadColor implements Runnable{

    public ThreadGialloScuro(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        int res = 3 * a  + 5 * c;
        System.out.println("GS: 3 * a  + 5 * c => " + res);
        this.setResult(res);
    }
}
