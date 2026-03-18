// BanconeCaldo.java - Classe Monitor per la gestione del bancone
public class BanconeCaldo {
    private int pizzePresenti;
    private final int CAPACITA_MAX = 30;

    public BanconeCaldo() {
        this.pizzePresenti = 0;
    }

    // Metodo sincronizzato per depositare pizze (usato dai pizzaioli)
    public synchronized void depositaPizze(int numeroPizze, String nomePizzaiolo) throws InterruptedException {
        // Controllo se c'è spazio sufficiente
        while (pizzePresenti + numeroPizze > CAPACITA_MAX) {
            System.out.println("[" + getTime() + "] " + nomePizzaiolo +
                    " grida: MAMMA MIA! Il bancone è pieno! Aspetto...");
            wait(); // Il pizzaiolo aspetta che si liberi spazio
        }

        // Deposita le pizze
        pizzePresenti += numeroPizze;
        System.out.println("[" + getTime() + "] Chef " + nomePizzaiolo +
                " sforna " + numeroPizze + " pizze fumanti! " +
                "(Totale sul bancone: " + pizzePresenti + ")");

        // Notifica i fattorini in attesa
        notifyAll();
    }

    // Metodo sincronizzato per prelevare pizze (usato dai fattorini)
    public synchronized void prelevaPizze(int numeroPizze, String nomeFattorino) throws InterruptedException {
        // Controllo se ci sono abbastanza pizze
        while (pizzePresenti < numeroPizze) {
            System.out.println("[" + getTime() + "] " + nomeFattorino +
                    " si lamenta: Niente pizze? Ma i clienti hanno fame! Aspetto...");
            wait(); // Il fattorino aspetta che arrivino pizze
        }

        // Preleva le pizze
        pizzePresenti -= numeroPizze;
        System.out.println("[" + getTime() + "] Rider " + nomeFattorino +
                " parte con " + numeroPizze + " pizze! Vroooom! " +
                "(Rimaste sul bancone: " + pizzePresenti + ")");

        // Notifica i pizzaioli in attesa
        notifyAll();
    }

    // Metodo per ottenere le pizze rimaste
    public synchronized int getPizzeRimaste() {
        return pizzePresenti;
    }

    // Utility per timestamp
    private String getTime() {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }
}