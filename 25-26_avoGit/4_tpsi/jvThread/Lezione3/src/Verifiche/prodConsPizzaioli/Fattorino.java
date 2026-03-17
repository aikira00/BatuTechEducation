// Fattorino.java - Thread che consegna pizze
import java.util.Random;

public class Fattorino extends Thread {
    private String nome;
    private BanconeCaldo bancone;
    private int pizzeTotaliConsegnate;
    private Random rand;
    private volatile boolean running;

    public Fattorino(String nome, BanconeCaldo bancone) {
        this.nome = nome;
        this.bancone = bancone;
        this.pizzeTotaliConsegnate = 0;
        this.rand = new Random();
        this.running = true;
    }

    @Override
    public void run() {
        System.out.println("🛵 Rider " + nome + " è pronto per le consegne!");

        while (running) {
            try {
                // Numero di pizze da prelevare (1-4)
                int pizzeDaConsegnare = rand.nextInt(4) + 1;

                // Preleva le pizze dal bancone
                bancone.prelevaPizze(pizzeDaConsegnare, nome);
                pizzeTotaliConsegnate += pizzeDaConsegnare;

                // Tempo di consegna (2000-4000 ms)
                int tempoConsegna = rand.nextInt(2001) + 2000;
                System.out.println("🚀 " + nome + " sta consegnando " +
                        pizzeDaConsegnare + " pizze ai clienti affamati!");
                Thread.sleep(tempoConsegna);

                System.out.println("✅ " + nome + " è tornato dalla consegna!");

                // Piccola pausa per leggibilità output
                Thread.sleep(100);

            } catch (InterruptedException e) {
                // Gestione interruzione per terminare il thread
                System.out.println("Rider " + nome + " sta parcheggiando la moto...");
                break;
            }
        }

        System.out.println("🏁 Rider " + nome + " ha finito il turno. Pizze consegnate: " +
                pizzeTotaliConsegnate);
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

    public int getPizzeTotaliConsegnate() {
        return pizzeTotaliConsegnate;
    }
}