public class GaraConigliettiIF {

    public static void main(String[] args) throws InterruptedException {

        // Creazione
        Bunny pasqualino = new Bunny("Pasqualino");
        Bunny pelosetto  = new Bunny("Pelosetto");
        Bunny ponpon     = new Bunny("Ponpon");
        Bunny birichino  = new Bunny("Birichino");

        // Avvio
        System.out.println("=== INIZIO DELLA GARA ===");
        pasqualino.start();
        pelosetto.start();
        ponpon.start();
        birichino.start();

        // Durata gara
        int durataSecondi = 5;
        Thread.sleep(durataSecondi * 1000L);

        // Stop sicuro
        System.out.println("=== TEMPO SCADUTO! ===");
        pasqualino.fermaGara();
        pelosetto.fermaGara();
        ponpon.fermaGara();
        birichino.fermaGara();

        // Join: attendiamo la fine effettiva di ciascun thread
        pasqualino.join();
        pelosetto.join();
        ponpon.join();
        birichino.join();

        // Ricerca del massimo con sequenza di if
        String nomeVincitore = pasqualino.getName();
        int    uovaMax       = pasqualino.getUovaRaccolte();

        if (pelosetto.getUovaRaccolte() > uovaMax) {
            uovaMax       = pelosetto.getUovaRaccolte();
            nomeVincitore = pelosetto.getName();
        }
        if (ponpon.getUovaRaccolte() > uovaMax) {
            uovaMax       = ponpon.getUovaRaccolte();
            nomeVincitore = ponpon.getName();
        }
        if (birichino.getUovaRaccolte() > uovaMax) {
            uovaMax       = birichino.getUovaRaccolte();
            nomeVincitore = birichino.getName();
        }

        // Classifica finale
        System.out.println("=== CLASSIFICA FINALE ===");
        System.out.println(pasqualino.getName() + ": " + pasqualino.getUovaRaccolte() + " uova");
        System.out.println(pelosetto.getName()  + ": " + pelosetto.getUovaRaccolte()  + " uova");
        System.out.println(ponpon.getName()     + ": " + ponpon.getUovaRaccolte()     + " uova");
        System.out.println(birichino.getName()  + ": " + birichino.getUovaRaccolte()  + " uova");

        System.out.println("Campione Magico: " + nomeVincitore
                + " con " + uovaMax + " uova!");
    }
}