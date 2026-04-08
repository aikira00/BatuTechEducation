// PizzeriaMain.java - Classe principale per la simulazione
import java.util.ArrayList;
import java.util.List;

public class PizzeriaMain {
    private static final int DURATA_SIMULAZIONE = 45000; // 45 secondi

    public static void main(String[] args) {
        System.out.println("🍕🍕🍕 BENVENUTI ALLA PIZZERIA MAMMA MIA! 🍕🍕🍕");
        System.out.println("============================================\n");

        // Creazione del bancone condiviso
        BanconeCaldo bancone = new BanconeCaldo();

        // Creazione dei pizzaioli
        Pizzaiolo mario = new Pizzaiolo("Mario", bancone);
        Pizzaiolo luigi = new Pizzaiolo("Luigi", bancone);

        // Creazione dei fattorini
        Fattorino speedy = new Fattorino("Speedy", bancone);
        Fattorino flash = new Fattorino("Flash", bancone);
        Fattorino turbo = new Fattorino("Turbo", bancone);

        // Lista per gestire tutti i thread
        List<Pizzaiolo> pizzaioli = new ArrayList<>();
        pizzaioli.add(mario);
        pizzaioli.add(luigi);

        List<Fattorino> fattorini = new ArrayList<>();
        fattorini.add(speedy);
        fattorini.add(flash);
        fattorini.add(turbo);

        // Avvio di tutti i thread
        System.out.println("🎬 INIZIA LA SIMULAZIONE!\n");

        for (Pizzaiolo p : pizzaioli) {
            p.start();
        }

        for (Fattorino f : fattorini) {
            f.start();
        }

        // Attesa per la durata della simulazione
        try {
            Thread.sleep(DURATA_SIMULAZIONE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Arresto di tutti i thread
        System.out.println("\n🔔 FINE DEL TURNO! Chiusura in corso...\n");

        for (Pizzaiolo p : pizzaioli) {
            p.ferma();
        }

        for (Fattorino f : fattorini) {
            f.ferma();
        }

        // Attesa che tutti i thread terminino
        try {
            for (Pizzaiolo p : pizzaioli) {
                p.join();
            }
            for (Fattorino f : fattorini) {
                f.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stampa delle statistiche finali
        System.out.println("\n📊 STATISTICHE FINALI DELLA GIORNATA 📊");
        System.out.println("=====================================");

        // Statistiche pizzaioli
        System.out.println("\n👨‍🍳 PRODUZIONE PIZZAIOLI:");
        int totaleProduzioneGiornaliera = 0;
        for (Pizzaiolo p : pizzaioli) {
            System.out.println("- Chef " + p.getNome() + ": " +
                    p.getPizzeTotaliSfornate() + " pizze sfornate");
            totaleProduzioneGiornaliera += p.getPizzeTotaliSfornate();
        }

        // Statistiche fattorini
        System.out.println("\n🛵 CONSEGNE FATTORINI:");
        int totaleConsegneGiornaliere = 0;
        Fattorino migliore = null;
        int maxConsegne = 0;

        for (Fattorino f : fattorini) {
            int consegne = f.getPizzeTotaliConsegnate();
            System.out.println("- Rider " + f.getNome() + ": " +
                    consegne + " pizze consegnate");
            totaleConsegneGiornaliere += consegne;

            if (consegne > maxConsegne) {
                maxConsegne = consegne;
                migliore = f;
            }
        }

        // Pizze rimaste sul bancone
        int pizzeRimaste = bancone.getPizzeRimaste();
        System.out.println("\n📦 SITUAZIONE BANCONE:");
        System.out.println("- Pizze rimaste sul bancone: " + pizzeRimaste);

        // Messaggio speciale per zero sprechi
        if (pizzeRimaste == 0) {
            System.out.println("🌟 Perfetto! Nessuno spreco! Tutte le pizze sono state consegnate! 🌟");
        } else {
            System.out.println("⚠️  Attenzione: " + pizzeRimaste + " pizze fredde sprecate!");
        }

        // Premio fattorino del mese
        System.out.println("\n🏆 PREMIO FATTORINO DEL MESE 🏆");
        if (migliore != null) {
            System.out.println("Il vincitore è... " + migliore.getNome() +
                    " con " + maxConsegne + " pizze consegnate! 🎉");
        }

        // Riepilogo finale
        System.out.println("\n📈 RIEPILOGO GIORNALIERO:");
        System.out.println("- Pizze prodotte in totale: " + totaleProduzioneGiornaliera);
        System.out.println("- Pizze consegnate in totale: " + totaleConsegneGiornaliere);
        System.out.println("- Efficienza: " +
                String.format("%.1f%%",
                        (totaleConsegneGiornaliere * 100.0) / totaleProduzioneGiornaliera));

        System.out.println("\n🍕 Grazie per aver scelto la Pizzeria Mamma Mia! 🍕");
        System.out.println("============================================");
    }
}