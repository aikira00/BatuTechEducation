package elfi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listaRegalo {

    private List<String> magazzino = new ArrayList<>(); //array di prodotti
    private int max_magazzino;
    private int indexTwinky = 0;
    private String TwinkyProduct = null; //NON USARE MAIUSCOLA PER String
    private int blinkys;
    private int twinkys;
    private int removeCounter = 0;

    public listaRegalo(int max_magazzino) {
        this.max_magazzino = max_magazzino;
    }

    //Produttore
    public synchronized String produce(Twinky twinky) {

        //altrimenti provo a produrre se magazzino pieno e ci sono ancora dei consumatori
        while (max_magazzino >= magazzino.size() && blinkys > 0) {

            try {
                System.out.println(Thread.currentThread().getName() + " ha rilevato il magazzino pieno. In attesa.");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Interruzione anomala segnalata per Twinky durante un wait()");
            }
        }
        //se non ci sono più consumatori produco regalo stop
        if(blinkys == 0){
            System.out.println("BLINKY = 0 per " + Thread.currentThread().getName());
            twinkys--;
            notifyAll();

            return "stop"; //però se non lo metto nella lista magazzio il consumatore non conusma mai regalo stop e non si ferma? qui ci andrebbe     this.getMagazzino().add("stop");
        }

        //getTwinkyI() sarebbe? regali prodotti fino adesso versus regali che deve produrre in totale?
        if (twinky.getTwinkyI() == twinky.getnTwinky()) {
            this.getMagazzino().add("stop");
            System.out.println(Thread.currentThread().getName() + " sta per finire normalmente." + "\n");
            twinkys--;
            //qui non dovrebbe essere come prima? produto stop lo aggiungo e ritorno stop?
        }

        //produttore non si ferma volontariamente, vuole ancora produrre
        magazzino.add("regalo " + indexTwinky);
        //ma il produttore non deve prendere da lista magazzino basta TwinkyPRoduct = "regalo " + indexTwinky
        TwinkyProduct = magazzino.get((indexTwinky - removeCounter));
        //questo meglio farlo fuori non qui, nel run si aggiunge alla sua lista il regalo prodotto
        twinky.getProdottoList().add(TwinkyProduct);
        indexTwinky++;// se usi una lista dinamica puoi usare la size della lista dei regali ArrayListMagazzino
        twinky.setTwinkyI(twinky.getTwinkyI() + 1);
        if(TwinkyProduct.equals("stop")){
            System.out.println(Thread.currentThread().getName() + " sta concludendo");
            twinkys--;
            //notifyAll();
            //return TwinkyProduct;
        }
        else    
            System.out.println(Thread.currentThread().getName() + " ha prodotto: " + TwinkyProduct + "\n");
        notifyAll();
        return TwinkyProduct;//può essere stop
    }

    //Consumatore
    public synchronized String spedisci(Blinky blinky) {
        while (magazzino.isEmpty() && twinkys > 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " ha rilevato il magazzino vuoto. In attesa.");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Interruzione anomala segnalata per Blinky durante un wait()");
            }
        }

        
        if(twinkys == 0){
            System.out.println("TWINKY = 0 per " + Thread.currentThread().getName());
            blinkys--;
            notifyAll();
            return "stop";
        }
        
        if (blinky.getBlinkyC() == blinky.getnBlinky()) {
            System.out.println(Thread.currentThread().getName() + " sta per finire normalmente." + "\n");
            blinkys--;
            notifyAll();
            return TwinkyProduct = "stop";
        }

        TwinkyProduct = magazzino.getFirst();
        if (!"stop".equals(TwinkyProduct)) {
            blinky.getSpeditoList().add(TwinkyProduct);//anche questo meglio farlo fuori e non avere la dipendenza circolare
            System.out.println(Thread.currentThread().getName() + " ha spedito: " + TwinkyProduct + "\n");
            magazzino.removeFirst();
            removeCounter++;// se usi una lista dinamica non ti serve removeCounter a meno che non vuoi contare il totale dei regali spediti
            blinky.setBlinkyC(blinky.getBlinkyC() + 1);
        }
        else
            blinkys--;
        
        notifyAll();
        return TwinkyProduct;
    }

    public List<String> getMagazzino() {
        return magazzino;
    }

    public String getTwinkyProduct() {
        return TwinkyProduct;
    }

    public void setTwinkyProduct(String TwinkyProduct) {
        this.TwinkyProduct = TwinkyProduct;
    }

    public int getBlinkys() {
        return blinkys;
    }

    public void setBlinkys(int Blinkys) {
        this.blinkys = Blinkys;
    }

    public int getTwinkys() {
        return twinkys;
    }

    public void setTwinkys(int Twinkys) {
        this.twinkys = Twinkys;
    }

    public void decrementTwinkys() {
        twinkys--;
    }

    public void decrementBlinkys() {
        blinkys--;
    }

}
