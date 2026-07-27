package complicata;

import java.util.Random;

public class Bunny extends Thread {

    private static final int    VITA_INIZIALE     = 10;
    private static final int    DANNO_ESPLOSIONE  = 2;
    private static final double SOGLIA_ESPLOSIONE = 0.7;

    private int uovaRaccolte;
    private int vita;
    private boolean inGara;
    private Random rand = new Random();

    public Bunny(String nome) {
        super(nome);
        this.uovaRaccolte = 0;
        this.vita = VITA_INIZIALE;
        this.inGara = true;
    }

    // Tenta la raccolta. Ritorna il numero di uova (0 se l'uovo esplode).
    // In caso di esplosione applica il danno al coniglietto.
    private int raccogliUova() {
        double prob = rand.nextDouble();   // valore in [0.0, 1.0)
        if (prob > SOGLIA_ESPLOSIONE) {
            vita -= DANNO_ESPLOSIONE;
            return 0;
        }
        return rand.nextInt(10) + 1;       // raccolta riuscita: 1-10 uova
    }

    @Override
    public void run() {
        System.out.println(getName() + " parte alla ricerca delle uova!");
        try {
            while (inGara && vita > 0) {
                int uova = raccogliUova();

                if (uova > 0) {
                    uovaRaccolte += uova;
                    System.out.println(getName() + " ha trovato " + uova
                            + " uova (totale: " + uovaRaccolte + ")");
                    Thread.sleep(rand.nextInt(500) + 200);     // riposo normale
                } else {
                    System.out.println(getName() + " - BOOM! Uovo esploso! Vita: "
                            + vita);
                    if (vita > 0) {
                        Thread.sleep(rand.nextInt(1000) + 1000); // pausa più lunga
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " è stato svegliato dal riposo.");
        }

        if (vita <= 0) {
            System.out.println(getName() + " è KO! Uova raccolte: " + uovaRaccolte);
        } else {
            System.out.println(getName() + " termina la gara con "
                    + uovaRaccolte + " uova e " + vita + " punti vita.");
        }
    }

    public void fermaGara() {
        inGara = false;
    }

    public int     getUovaRaccolte() { return uovaRaccolte; }
    public int     getVita()         { return vita; }
    public boolean isVivo()          { return vita > 0; }
}