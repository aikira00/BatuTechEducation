package playlist;

// Esercitazione 13, le playlist dell'intervallo: la canzone non sa di stare in una playlist.
public class Canzone {
    private String titolo;
    private String cantante;
    private int durata;       // in secondi: fuori si vede come 3:12
    private int ascolti;

    public Canzone(String titolo, String cantante, int minuti, int secondi) {
        this.titolo = titolo;
        this.cantante = cantante;
        int totale = minuti * 60 + secondi;
        if (totale > 0) {
            this.durata = totale;
        } else {
            System.out.println(titolo + ": durata non valida, metto 3:00");
            this.durata = 180;
        }
        this.ascolti = 0;     // appena creata, nessuno l'ha ancora suonata
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCantante() {
        return cantante;
    }

    public int getDurata() {
        return durata;
    }

    public int getAscolti() {
        return ascolti;
    }

    public void suona() {
        System.out.println("  " + titolo + " - " + cantante + " (" + minSec(durata) + ")");
        ascolti++;
    }

    // da 245 secondi a "4:05": serve alla canzone, non a chi la usa
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
        return titolo + " - " + cantante + " (" + minSec(durata) + "), ascolti: " + ascolti;
    }
}
