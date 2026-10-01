package conCostruttori;

// PASSO 2 — costruttori (due, in overloading): il secondo chiama il primo con this(...),
// come nelle slide 35-36. Getter e setter come nella 02 Parte 2: il costruttore
// non chiama i setter. Si stampa ancora con mostra(): toString arriva al passo 3.
public class Sveglia {
    private int ora;
    private int minuti;
    private String etichetta;

    public Sveglia(int ora, int minuti) {
        if (ora >= 0 && ora <= 23 && minuti >= 0 && minuti <= 59) {
            this.ora = ora;
            this.minuti = minuti;
        } else {
            System.out.println("Orario " + ora + ":" + minuti + " non valido, imposto le 7:00");
            this.ora = 7;
            this.minuti = 0;
        }
        this.etichetta = "senza etichetta";

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
        System.out.println("DRIIIN! " + etichetta);
    }
    public void mostra() {
        System.out.println("Sveglia alle " + ora + ":" + minuti + " (" + etichetta + ")");
    }



}
