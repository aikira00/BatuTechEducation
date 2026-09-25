package edu.avo.conCostruttori;

// PASSO 2 — costruttori (due, in overloading), this(...), toString,
// e un metodo in overloading: posticipa() e posticipa(int minuti).
public class Sveglia {
    private int ora;
    private int minuti;
    private String etichetta;
    private boolean ripeti;

    public Sveglia(int ora, int minuti) {
        if (ora >= 0 && ora <= 23 && minuti >= 0 && minuti <= 59) {
            this.ora = ora;
            this.minuti = minuti;
        } else {
            System.out.println("Orario " + ora + ":" + minuti + " non valido, imposto le 7:00");
            this.ora = 7;
            this.minuti = 0;
        }
        this.etichetta = "sveglia";
        this.ripeti = false;
    }

    public Sveglia(int ora, int minuti, String etichetta) {
        this(ora, minuti);            // prima istruzione: riusa il costruttore sopra
        this.etichetta = etichetta;
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

    public boolean isRipeti() {
        return ripeti;
    }

    public void setRipeti(boolean ripeti) {
        this.ripeti = ripeti;
    }

    public void suona() {
        System.out.println("DRIIIN! " + etichetta);
    }

    public void posticipa() {
        posticipa(5);                 // senza parametri: i classici 5 minuti
    }

    public void posticipa(int minuti) {
        if (minuti <= 0) {
            System.out.println("Posticipare di " + minuti + " minuti non ha senso");
            return;
        }
        int totale = ora * 60 + this.minuti + minuti;
        this.ora = (totale / 60) % 24;
        this.minuti = totale % 60;
    }

    public String toString() {
        String orario = String.format("%02d:%02d", ora, minuti);
        if (ripeti) {
            return orario + " (" + etichetta + ", ogni giorno)";
        }
        return orario + " (" + etichetta + ")";
    }
}
