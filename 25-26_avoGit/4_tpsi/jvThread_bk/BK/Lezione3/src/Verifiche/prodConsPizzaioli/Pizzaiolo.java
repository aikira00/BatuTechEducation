// Pizzaiolo.java - Thread che produce pizze
import java.util.Random;

public class Pizzaiolo extends Thread {
    private String nome;
    private BanconeCaldo bancone;
    private int pizzeTotaliSfornate;
    private Random rand;
    private volatile boolean running;

    public Pizzaiolo(String nome, BanconeCaldo bancone) {
        this.nome = nome;
        this.bancone = bancone;
        this.pizzeTotaliSfornate = 0;
        this.rand = new Random();
        this.running = true;
    }

    @Override
    public void run() {
        System.out.println("🍕 Chef " + nome + " inizia a lavorare!");

        while (running) {
            try {
                // Tempo di preparazione (800-1500 ms)
                int tempoPreparazione = rand.nextInt(701) + 800;
                Thread.sleep(tempoPreparazione);

                // Numero di pizze da sfornare (1-6)
                int pizzeDaSfornare = rand.nextInt(6) + 1;

                // Easter Egg per Mario con 5 pizze
                if (nome.equals("Mario") && pizzeDaSfornare == 5) {
                    System.out.println("🌟 It's-a me, Mario! Cinque pizze perfette! 🌟");
                }

                // Deposita le pizze sul bancone
                bancone.depositaPizze(pizzeDaSfornare, nome);
                pizzeTotaliSfornate += pizzeDaSfornare;

                // Piccola pausa per leggibilità output
                Thread.sleep(100);

            } catch (InterruptedException e) {
                // Gestione interruzione per terminare il thread
                System.out.println("Chef " + nome + " sta chiudendo il forno...");
                break;
            }
        }

        System.out.println("👨‍🍳 Chef " + nome + " ha finito il turno. Pizze sfornate: " +
                pizzeTotaliSfornate);
    }

    // Metodo per fermare il thread in modo sicuro
    public void ferma() {
        running = false;
        interrupt();
    }

    // Getter per le statistiche
    public String getNome() {
        return nome;
    }

    public int getPizzeTotaliSfornate() {
        return pizzeTotaliSfornate;
    }
}