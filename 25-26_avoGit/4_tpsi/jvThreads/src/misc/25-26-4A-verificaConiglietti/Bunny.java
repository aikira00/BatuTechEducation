import java.util.Random;

public class Bunny extends Thread {

    private int uovaRaccolte;
    private  boolean inGara;   // variabile di osservazione
    private Random rand = new Random();

    public Bunny(String nome) {
        super(nome);
        this.uovaRaccolte = 0;
        this.inGara = true;
    }

    // Metodo che simula la raccolta: ritorna un numero casuale di uova
    private int raccogliUova() {
        return rand.nextInt(10) + 1;   // da 1 a 10
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " parte alla ricerca delle uova!");
        try {
            while (inGara) {
                int uova = raccogliUova();
                uovaRaccolte += uova;
                System.out.println(this.getName() + " ha trovato " + uova
                        + " uova (totale: " + uovaRaccolte + ")");

                // Riposo: 200-700 ms
                Thread.sleep(rand.nextInt(500) + 200);
            }
        } catch (InterruptedException e) {
            // Interruzione durante il sonno: usciamo in modo pulito
            System.out.println(this.getName() + " è stato svegliato dal riposo.");
        }
        System.out.println(this.getName() + " termina la gara con "
                + uovaRaccolte + " uova.");
    }

    // Stop sicuro: alza il flag e sveglia il thread se sta dormendo
    public void fermaGara() {
        inGara = false;

    }

    public int    getUovaRaccolte() { return uovaRaccolte; }
}