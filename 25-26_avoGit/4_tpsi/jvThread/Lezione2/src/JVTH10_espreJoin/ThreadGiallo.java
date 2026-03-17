package JVTH10_espreJoin;

public class ThreadGiallo extends ThreadColor implements Runnable{
    public ThreadGiallo(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        ThreadGialloChiaro IThGC = new ThreadGialloChiaro(a, b, c);
        ThreadGialloScuro IThGS = new ThreadGialloScuro(a,b,c);
        Thread thGC = new Thread(IThGC);
        Thread thGS = new Thread(IThGS);
        thGC.start();
        thGS.start();
        try{
            thGC.join();
            thGS.join();
        }
        catch(InterruptedException e){
            System.out.println("Eccezione avvenuta " + e.getMessage());
        }
        double res = IThGC.getResult() * IThGS.getResult();
        System.out.println("Giallo => " + res);
        this.setResult(res);
    }
}
