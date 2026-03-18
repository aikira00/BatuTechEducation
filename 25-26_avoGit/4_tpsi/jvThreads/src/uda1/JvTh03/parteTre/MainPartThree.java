package JvTh03.parteTre;

public class MainPartThree {
    public static void main(String[] args) {
        int delay1 = 1000;
        int delay2 = 2000;
        String message1 = "Messaggio per il primo thread";
        String message2 = "Messaggio per il secondo thread";

        MyParamThreadMsg t1 = new MyParamThreadMsg(delay1, message1);
        MyParamThreadMsg t2 = new MyParamThreadMsg(delay2, message2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main terminato dopo la fine dei thread con parametri.");
    }
}