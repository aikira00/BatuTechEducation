package avo.chiEThis;

// PASSO 4 — chi è this? L'oggetto su cui è stato chiamato il metodo.
// In piuGenerosaDi ci sono DUE recensioni: this e altra.
// Con r2.piuGenerosaDi(r4), this è r2 e altra è r4.
public class Recensione {
    private String locale;
    private String autore;
    private int stelle;

    public String getLocale() {
        return locale;
    }

    public String getAutore() {
        return autore;
    }

    public int getStelle() {
        return stelle;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setStelle(int stelle) {
        if (stelle >= 1 && stelle <= 5) {
            this.stelle = stelle;
        } else {
            System.out.println("Rifiutato: " + stelle + " stelle non è un voto valido (da 1 a 5)");
        }
    }

    public boolean piuGenerosaDi(Recensione altra) {
        // altra.stelle è private, eppure si legge: siamo dentro la classe Recensione
        return this.stelle > altra.stelle;
    }

    public void pubblica() {
        System.out.println(autore + " ha dato " + stelle + " stelle a " + locale);
    }
}
