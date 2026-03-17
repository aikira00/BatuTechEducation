public class Jvth10EsprJoinMain {

    public static void main(String[] args){
        Jvth10EsprJoin rtha = new Jvth10EsprJoin(3,2, "sub");
        Thread tha = new Thread(rtha);
        Jvth10EsprJoin rthb = new Jvth10EsprJoin(5,4, "add");
        Thread thb = new Thread(rthb);
        tha.start();
        thb.start();
        try{
            tha.join();
            thb.join();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //recupero ris e stampo calcolo finale
        float c = rtha.getResult() * rthb.getResult();
        System.out.println(c);
    }
}
