public class Jvth11EsprJoinMainV2 {

    public static void main(String[] args){
        Jvth10EsprJoin rtha = new Jvth10EsprJoin(2,6, "mul");
        Thread tha = new Thread(rtha);
        Jvth11EsprJoinV2 rthd = new Jvth11EsprJoinV2();
        Thread thd = new Thread(rthd);
        tha.start();
        thd.start();
        try{
            tha.join();
            thd.join();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //recupero ris e stampo calcolo finale
        float e = rtha.getResult() + rthd.getResult();
        System.out.println(e);
    }
}
