package orologioConSveglie;

// Associazione: l'array di Sveglia è un attributo, l'orologio le sveglie se le tiene.
// In orologioSveglia le riceveva in controlla(Sveglia s) e le lasciava (dipendenza).
public class Orologio {
    private int ora;
    private int minuti;
    private Sveglia[] sveglie;
    private int quante;

    public Orologio(int ora, int minuti) {
        this.ora = ora;
        this.minuti = minuti;
        this.sveglie = new Sveglia[10];   // posto per 10 sveglie
        this.quante = 0;                  // nasce senza sveglie
    }

    public void aggiungi(Sveglia s) {
        if (quante == sveglie.length) {
            System.out.println("Orologio pieno: la sveglia " + s.getEtichetta() + " non entra");
        } else {
            sveglie[quante] = s;
            quante++;
        }
    }

    // niente parametro: le sveglie le ha già
    public void controlla() {
        for (int i = 0; i < quante; i++) {
            if (sveglie[i].getOra() == ora && sveglie[i].getMinuti() == minuti) {
                System.out.print(sveglie[i].getEtichetta() + ": ");
                sveglie[i].suona();
            }
        }
    }

    @Override
    public String toString() {
        String mm;
        if (minuti < 10) {
            mm = "0" + minuti;
        } else {
            mm = "" + minuti;
        }
        String elenco = "Orologio alle " + ora + ":" + mm + ", " + quante + " sveglie";
        for (int i = 0; i < quante; i++) {
            elenco = elenco + "\n" + (i + 1) + ". " + sveglie[i];
        }
        return elenco;
    }
}
