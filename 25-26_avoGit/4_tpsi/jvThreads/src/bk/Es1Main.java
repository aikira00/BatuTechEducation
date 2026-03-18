
public class Es1Main {
    public static void main(String[] args) {
       System.out.println("Avvio thread messaggi");
       long startTime = System.currentTimeMillis();
        long patience = 20000;//1000 * 60 * 60;
        Thread t = new Thread(new Es1MessageThread());
        t.start();
        System.out.println("Aspetto che thread messaggi finisca");
        while (t.isAlive()) {
            System.out.println("Continuo ad aspettare...");
            try {
                t.join(4000);
            }
            catch(InterruptedException ex){
                System.out.println("Join interrupted ex");
            }
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                System.out.println("Sono stufo di aspettare una vita!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                /*try{

                    t.join();
                }
                catch(InterruptedException ex){
                    System.out.println("Join interrupted ex");
                }*/
            }
        }
        System.out.println("Finalmente ho finito di aspettare!");
    }
}