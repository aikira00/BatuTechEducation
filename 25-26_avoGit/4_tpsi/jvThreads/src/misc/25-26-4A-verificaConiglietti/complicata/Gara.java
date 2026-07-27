package complicata;

import java.util.ArrayList;

public class Gara {

    public static void main(String[] args) throws InterruptedException {

        String[] nomi = {"Pasqualino", "Pelosetto", "Ponpon", "Birichino"};
        ArrayList<Bunny> coniglietti = new ArrayList<>();
        for (String n : nomi) coniglietti.add(new Bunny(n));

        System.out.println("=== INIZIO DELLA GARA ===");
        for (Bunny b : coniglietti) b.start();

        Thread.sleep(5_000);

        System.out.println("=== TEMPO SCADUTO! ===");
        for (Bunny b : coniglietti) b.fermaGara();
        for (Bunny b : coniglietti) b.join();

        // Statistiche dei sopravvissuti + ricerca del vincitore
        System.out.println("\n=== CONIGLIETTI SOPRAVVISSUTI ===");
        Bunny vincitore = null;
        for (Bunny b : coniglietti) {
            if (b.isVivo()) {
                System.out.println(b.getName() + ": " + b.getUovaRaccolte()
                        + " uova, " + b.getVita() + " punti vita");
                if (vincitore == null
                        || b.getUovaRaccolte() > vincitore.getUovaRaccolte()) {
                    vincitore = b;
                }
            }
        }

        // Statistiche degli sconfitti
        System.out.println("\n=== CONIGLIETTI ESPLOSI ===");
        for (Bunny b : coniglietti) {
            if (!b.isVivo()) {
                System.out.println(b.getName() + ": " + b.getUovaRaccolte() + " uova (KO)");
            }
        }

        // Proclamazione
        if (vincitore != null) {
            System.out.println("\nCampione Magico: " + vincitore.getName()
                    + " con " + vincitore.getUovaRaccolte() + " uova!");
        } else {
            System.out.println("\nNessun sopravvissuto: nessun vincitore!");
        }
    }
}