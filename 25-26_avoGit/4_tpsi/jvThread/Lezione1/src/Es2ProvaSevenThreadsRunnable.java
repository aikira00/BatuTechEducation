public class Es2ProvaSevenThreadsRunnable {
    public static void main (String [] args){
        String[] nomi = new String[7];
        nomi[0] = "Dotto";
        nomi[1] = "Brontolo";
        nomi[2] = "Pisolo";
        nomi[3] = "Mammolo";
        nomi[4] = "Gongolo";
        nomi[5] = "Eolo";
        nomi[6] = "Cucciolo";

        System.out.println(Thread.currentThread().getName());

        for(int i = 0; i < 7; i++) {
            Thread thread = new Thread(new Es2SevenRunnable(nomi[i]));
            thread.start();
        }
    }
}
