package JVTH10_espreJoin;

public class ThreadVerde extends ThreadColor implements Runnable{

    public ThreadVerde(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        ThreadVerdeChiaro IThVC = new ThreadVerdeChiaro(a, b, c);
        ThreadVerdeScuro IThVS = new ThreadVerdeScuro(a,b,c);
        Thread thVC = new Thread(IThVC);
        Thread thVS = new Thread(IThVS);
        thVC.start();
        thVS.start();
        try{
            thVC.join();
            thVS.join();
        }
        catch(InterruptedException e){
            System.out.println("Eccezione avvenuta " + e.getMessage());
        }
        double res = IThVC.getResult() * IThVS.getResult();
        System.out.println("Verde => " + res);
        this.setResult(res);
    }
}
