public class Jvth11EsprJoinV2 implements Runnable {

    private float result;


    public void run(){
        try{
            Jvth10EsprJoin rthb = new Jvth10EsprJoin(1,4, "add");
            Thread thb = new Thread(rthb);
            Jvth10EsprJoin rthc = new Jvth10EsprJoin(5,2, "sub");
            Thread thc = new Thread(rthc);
            thb.start();
            thc.start();
            thb.join();
            thc.join();
            result = rthb.getResult()*rthc.getResult();


        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " errore: " + e.getMessage());
        }

    }

    public float getResult(){
        return result;
    }
}
