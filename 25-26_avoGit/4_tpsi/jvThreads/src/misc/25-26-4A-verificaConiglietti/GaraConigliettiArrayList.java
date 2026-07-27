import java.util.ArrayList;

public class GaraConigliettiArrayList {

    public static void main(String[] args) throws InterruptedException {

        String[] nomi = {"Pasqualino", "Pelosetto", "Ponpon", "Birichino"};
        ArrayList<Bunny> coniglietti = new ArrayList<>();

        // Creazione e avvio
        for (String n : nomi) {
            coniglietti.add(new Bunny(n));
        }

        System.out.println("=== INIZIO DELLA GARA ===");
        for (Bunny b : coniglietti) {
            b.start();
        }

        // Durata gara
        int durataSecondi = 5;
        Thread.sleep(durataSecondi * 1000L);

        // Stop sicuro di tutti i thread
        System.out.println("=== TEMPO SCADUTO! ===");
        for (Bunny b : coniglietti) {
            b.fermaGara();
        }

        // Attendiamo che TUTTI abbiano davvero terminato prima di proclamare il vincitore
        for (Bunny b : coniglietti) {
            b.join();
        }

        // Ricerca del massimo
        Bunny vincitore = coniglietti.get(0);
        for (Bunny b : coniglietti) {
            if (b.getUovaRaccolte() > vincitore.getUovaRaccolte()) {
                vincitore = b;
            }
        }

        // Classifica finale
        System.out.println("=== CLASSIFICA FINALE ===");
        for (Bunny b : coniglietti) {
            System.out.println(b.getName() + ": " + b.getUovaRaccolte() + " uova");
        }
        System.out.println("Campione Magico: " + vincitore.getName()
                + " con " + vincitore.getUovaRaccolte() + " uova!");
    }
}