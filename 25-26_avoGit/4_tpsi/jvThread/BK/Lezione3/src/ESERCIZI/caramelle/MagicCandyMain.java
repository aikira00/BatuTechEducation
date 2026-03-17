package ESERCIZI.caramelle;

public class MagicCandyMain {
    public static void main(String[] args) {
        MagicCandyFactory d = new MagicCandyFactory();
        //creo threads
      MagicCandyProd prod1 = new MagicCandyProd(d);
      MagicCandyCons cons1 = new MagicCandyCons(d);

      prod1.start();
      cons1.start();
      //thread principale dorme
        try {
            Thread.sleep(10000);
            //termino thread
            prod1.stopCandyProducer();
            cons1.stopCandyConsumer();

            prod1.join();
            cons1.join();
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Totale caramelle ancora nel distributore: " + d.getValue());
    }
}
