package orologioSveglia;

// Dipendenza «usa»: l'orologio riceve una sveglia, la guarda e la lascia. Non la crea e non la tiene.
public class Orologio {
    private int ora;
    private int minuti;

    public Orologio(int ora, int minuti) {
        this.ora = ora;
        this.minuti = minuti;
    }

    public void controlla(Sveglia s) {
        if (s.getOra() == ora && s.getMinuti() == minuti) {
            System.out.print(s.getEtichetta() + ": ");
            s.suona();
        }
    }
}
