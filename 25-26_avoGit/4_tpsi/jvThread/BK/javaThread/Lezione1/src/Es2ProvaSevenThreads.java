public class Es2ProvaSevenThreads {
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
            Thread thread = new Es2SevenThread(nomi[i]);
            thread.start();
        }

        /*Thread thOne = new Es2SevenThread("Pippo");
        Thread thTwo = new Es2SevenThread("Pluto");
        Thread thThree = new Es2SevenThread("Topolino");
        Thread thFour = new Es2SevenThread("Paperino");
        Thread thFive = new Es2SevenThread("Qui");
        Thread thSix = new Es2SevenThread("Quo");
        Thread thSeven= new Es2SevenThread("Qua");

        thOne.start();
        thTwo.start();
        thThree.start();
        thFour.start();
        thFive.start();
        thSix.start();
        thSeven.start();*/
    }
}
