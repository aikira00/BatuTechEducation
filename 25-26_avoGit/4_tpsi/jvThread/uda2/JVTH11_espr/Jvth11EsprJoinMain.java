public class Jvth11EsprJoinMain {

    public static void main(String[] args){
        Jvth10EsprJoin rtha = new Jvth10EsprJoin(2,6, "mul");
        Thread tha = new Thread(rtha);
        Jvth10EsprJoin rthb = new Jvth10EsprJoin(1,4, "add");
        Thread thb = new Thread(rthb);
        Jvth10EsprJoin rthc = new Jvth10EsprJoin(5,2, "sub");
        Thread thc = new Thread(rthc);
        tha.start();
        thb.start();
        thc.start();
        try{
            tha.join();
            thb.join();
            thc.join();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //recupero ris e stampo calcolo finale
        float d = rthb.getResult() * rthc.getResult();
        float e = rtha.getResult() + d;
        System.out.println(e);
    }
}
