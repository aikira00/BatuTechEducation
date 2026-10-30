package playlist;

// Associazione N:M con Canzone: le canzoni le riceve già fatte (dal main) e se le tiene.
public class Playlist {
    private String nome;
    private Canzone[] canzoni;
    private int quante;

    public Playlist(String nome) {
        this.nome = nome;
        this.canzoni = new Canzone[4];   // al massimo 4 canzoni
        this.quante = 0;                 // nasce vuota
    }

    public String getNome() {
        return nome;
    }

    public void aggiungi(Canzone c) {
        if (quante == canzoni.length) {
            System.out.println(nome + ": playlist piena, " + c.getTitolo() + " resta fuori");
        } else if (durataTotale() + c.getDurata() > 15 * 60) {
            System.out.println(nome + ": " + c.getTitolo() + " dura " + minSec(c.getDurata())
                    + ", restano solo " + minSec(15 * 60 - durataTotale()));
        } else {
            canzoni[quante] = c;
            quante++;
        }
    }

    public int durataTotale() {
        int totale = 0;
        for (int i = 0; i < quante; i++) {
            totale = totale + canzoni[i].getDurata();
        }
        return totale;
    }

    public void suona() {
        System.out.println("=== " + nome + " ===");
        for (int i = 0; i < quante; i++) {
            canzoni[i].suona();
        }
    }

    // uguale a quello della Canzone: per ora si ripete
    private String minSec(int secondi) {
        int m = secondi / 60;
        int s = secondi % 60;
        if (s < 10) {
            return m + ":0" + s;
        }
        return m + ":" + s;
    }

    @Override
    public String toString() {
        return nome + ": " + quante + " canzoni, " + minSec(durataTotale());
    }
}
