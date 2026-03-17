import java.util.Random;

class Pettirosso extends Thread {
    private String nome;
    private int vermiTotali;
    private int tentativiRiusciti;
    private int tentativiFalliti;
    private volatile boolean fermarsi;
    private Random rand;
    private static final int MAX_VERMI = 80; // obiettivo massimo
    
    public Pettirosso(String nome) {
        this.nome = nome;
        this.vermiTotali = 0;
        this.tentativiRiusciti = 0;
        this.tentativiFalliti = 0;
        this.fermarsi = false;
        this.rand = new Random();
    }
    
    @Override
    public void run() {
        System.out.println(nome + " inizia la caccia ai vermi!");
        
        while (!fermarsi && vermiTotali < MAX_VERMI) {
            int vermiCatturati = cacciaVermi();
            
            if (vermiCatturati > 0) {
                tentativiRiusciti++;
                vermiTotali += vermiCatturati;
                System.out.println(nome + " ha catturato " + vermiCatturati + 
                                 " vermi! (Totale: " + vermiTotali + ")");
            } else {
                tentativiFalliti++;
                System.out.println(nome + " non ha trovato nessun verme...");
            }
            
            // Pausa breve tra un tentativo e l'altro
            try {
                Thread.sleep(100 + rand.nextInt(200)); // 100-300ms
            } catch (InterruptedException e) {
                break;
            }
        }
        
        if (vermiTotali >= MAX_VERMI) {
            System.out.println(nome + " ha raggiunto l'obiettivo di " + MAX_VERMI + " vermi!");
        } else {
            System.out.println(nome + " si ferma per fine tempo.");
        }
    }
    
    private int cacciaVermi() {
        // Genera probabilità di successo nella caccia
        double probabilitaCaccia = rand.nextDouble();
        
        if (probabilitaCaccia > 0.7) {
            // Caccia riuscita! Genera numero casuale di vermi (1-8)
            int vermi = rand.nextInt(8) + 1;
            
            // Controlla se appare Micio il gatto
            double probabilitaMicio = rand.nextDouble();
            
            if (probabilitaMicio > 0.6) {
                // Micio appare e spaventa il pettirosso!
                int vermiPersi = vermi / 2;
                vermi = vermi - vermiPersi;
                System.out.println("  😱 MIAOOOO! " + nome + " perde " + vermiPersi + 
                                 " vermi per lo spavento!");
            }
            
            return vermi;
        } else {
            // Caccia fallita
            return 0;
        }
    }
    
    public void fermaGara() {
        this.fermarsi = true;
    }
    
    // Metodi getter per le statistiche
    public String getNome() { return nome; }
    public int getVermiTotali() { return vermiTotali; }
    public int getTentativiRiusciti() { return tentativiRiusciti; }
    public int getTentativiFalliti() { return tentativiFalliti; }
}

public class GrandeCacciaVermi {
    public static void main(String[] args) {
        System.out.println("🐦 BENVENUTI ALLA GRANDE CACCIA AI VERMI! 🪱");
        System.out.println("=" .repeat(50));
        
        // Crea i quattro pettirossi
        Pettirosso[] pettirossi = {
            new Pettirosso("Cinguettino"),
            new Pettirosso("Svolazzino"), 
            new Pettirosso("Becchettino"),
            new Pettirosso("Saltellino")
        };
        
        // Avvia tutti i thread
        for (Pettirosso pettirosso : pettirossi) {
            pettirosso.start();
        }
        
        // Durata della gara: 12 secondi
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            System.out.println("Gara interrotta!");
        }
        
        // Ferma tutti i pettirossi in modo safe
        System.out.println("\n⏰ TEMPO SCADUTO! Fermando tutti i pettirossi...");
        for (Pettirosso pettirosso : pettirossi) {
            pettirosso.fermaGara();
        }
        
        // Attende che tutti i thread terminino
        for (Pettirosso pettirosso : pettirossi) {
            try {
                pettirosso.join();
            } catch (InterruptedException e) {
                System.out.println("Errore nell'attesa del thread: " + pettirosso.getNome());
            }
        }
        
        // Stampa le statistiche finali
        System.out.println("\n" + "=" .repeat(50));
        System.out.println("📊 STATISTICHE FINALI DELLA GARA");
        System.out.println("=" .repeat(50));
        
        for (Pettirosso pettirosso : pettirossi) {
            System.out.printf("🐦 %-15s | Vermi: %3d | Successi: %3d | Fallimenti: %3d%n",
                pettirosso.getNome(),
                pettirosso.getVermiTotali(),
                pettirosso.getTentativiRiusciti(),
                pettirosso.getTentativiFalliti());
        }
        
        // Determina vincitore e ultimo classificato
        Pettirosso vincitore = pettirossi[0];
        Pettirosso ultimoClassificato = pettirossi[0];
        
        for (Pettirosso pettirosso : pettirossi) {
            if (pettirosso.getVermiTotali() > vincitore.getVermiTotali()) {
                vincitore = pettirosso;
            }
            if (pettirosso.getVermiTotali() < ultimoClassificato.getVermiTotali()) {
                ultimoClassificato = pettirosso;
            }
        }
        
        System.out.println("\n" + "=" .repeat(50));
        System.out.println("🏆 IL GRAN MAESTRO CACCIATORE DI VERMI È: " + vincitore.getNome().toUpperCase());
        System.out.println("   Con ben " + vincitore.getVermiTotali() + " vermi catturati! 🎉");
        
        if (!vincitore.equals(ultimoClassificato)) {
            System.out.println("\n💪 Incoraggiamento speciale per " + ultimoClassificato.getNome());
            System.out.println("   Continua ad allenarti, la prossima volta andrà meglio! 🌟");
        }
        
        System.out.println("\n🎭 Grazie a tutti i partecipanti per questa fantastica gara!");
        System.out.println("=" .repeat(50));
    }
}