package orologioConSveglie;

// La Sveglia del main interattivo (02_02), senza modifiche.
public class Sveglia {
    private int ora;
    private int minuti;
    private String etichetta;

    public Sveglia(int ora, int minuti, String etichetta) {
        if (ora >= 0 && ora <= 23 && minuti >= 0 && minuti <= 59) {
            this.ora = ora;
            this.minuti = minuti;
        } else {
            System.out.println("Orario " + ora + ":" + minuti + " non valido, imposto le 7:00");
            this.ora = 7;
            this.minuti = 0;
        }
        this.etichetta = etichetta;
    }

    public Sveglia(int ora, int minuti) {
        this(ora, minuti, "senza etichetta");   // prima istruzione: riusa il costruttore completo
    }

    public int getOra() {
        return ora;
    }

    public int getMinuti() {
        return minuti;
    }

    public String getEtichetta() {
        return etichetta;
    }

    public void setOra(int ora) {
        if (ora >= 0 && ora <= 23) {
            this.ora = ora;
        } else {
            System.out.println("Rifiutato: l'ora " + ora + " non esiste");
        }
    }

    public void setMinuti(int minuti) {
        if (minuti >= 0 && minuti <= 59) {
            this.minuti = minuti;
        } else {
            System.out.println("Rifiutato: " + minuti + " minuti non esistono");
        }
    }

    public void setEtichetta(String etichetta) {
        this.etichetta = etichetta;
    }

    public void suona() {
        System.out.println("DRIIIN!");
    }

    public void posticipa() {
        posticipa(5);                 // senza parametri: i classici 5 minuti
    }

    public void posticipa(int minuti) {
        if (minuti <= 0) {
            System.out.println("Rifiutato: posticipare di " + minuti + " minuti non ha senso");
            return;
        }
        int totale = ora * 60 + this.minuti + minuti;
        this.ora = (totale / 60) % 24;
        this.minuti = totale % 60;
    }

    @Override
    public String toString() {
        String mm;
        if (minuti < 10) {
            mm = "0" + minuti;
        } else {
            mm = "" + minuti;
        }
        return "Sveglia alle " + ora + ":" + mm + " (" + etichetta + ")";
    }
}
