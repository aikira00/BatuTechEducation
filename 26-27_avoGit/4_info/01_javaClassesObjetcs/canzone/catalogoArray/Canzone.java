package catalogoArray;

// "Dallo scenario al codice: il jukebox dell'intervallo" (esercitazione 08): la classe che nasce dalla mail di Giulia.
// Nessun setter: una canzone inserita non si modifica, e gli ascolti crescono solo suonandola.
public class Canzone {
    private String titolo;
    private String artista;
    private String genere;
    private int durata;       // in secondi: fuori si vede come 3:12
    private String testo;     // un pezzo del testo, di solito il ritornello
    private int ascolti;

    public Canzone(String titolo, String artista, String genere, int durata, String testo) {
        this.titolo = titolo;
        this.artista = artista;

        String g = genere.toLowerCase();   // "Rock" e "rock" sono lo stesso genere
        if (g.equals("pop") || g.equals("rock") || g.equals("rap") || g.equals("indie") || g.equals("jazz")) {
            this.genere = g;
        } else {
            System.out.println(titolo + ": il genere \"" + genere + "\" non è fra quelli previsti, la metto in altro");
            this.genere = "altro";
        }

        if (durata > 0) {
            this.durata = durata;
        } else {
            System.out.println(titolo + ": durata " + durata + " non valida, metto 3:00");
            this.durata = 180;
        }

        this.testo = testo;
        this.ascolti = 0;     // appena inserita, nessuno l'ha ancora suonata
    }

    public Canzone(String titolo, String artista, String genere, int durata) {
        this(titolo, artista, genere, durata, "(brano strumentale)");   // prima istruzione: riusa il costruttore completo
    }

    public String getTitolo() {
        return titolo;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenere() {
        return genere;
    }

    public int getDurata() {
        return durata;
    }

    public int getAscolti() {
        return ascolti;
    }

    public void play() {
        System.out.println(">>> " + titolo + " - " + artista + " (" + durataMinSec() + ")");
        System.out.println(testo);
        ascolti++;
    }

    // serve a play() e a toString(), non a chi usa la classe
    private String durataMinSec() {
        int minuti = durata / 60;
        int secondi = durata % 60;
        String ss;
        if (secondi < 10) {
            ss = "0" + secondi;
        } else {
            ss = "" + secondi;
        }
        return minuti + ":" + ss;
    }

    @Override
    public String toString() {
        return titolo + " - " + artista + " [" + genere + ", " + durataMinSec() + "] ascolti: " + ascolti;
    }
}
