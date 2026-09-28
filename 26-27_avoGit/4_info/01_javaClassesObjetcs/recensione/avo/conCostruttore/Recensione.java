package avo.conCostruttore;

// PASSO 5 — la soluzione delle slide: costruttore, toString e this.
// Il costruttore fa nascere la recensione già con i suoi dati,
// e per le stelle riusa il controllo che sta nel setter.
public class Recensione {
    private String locale;
    private String autore;
    private int stelle;

    public Recensione(String locale, String autore, int stelle) {
        this.locale = locale;
        this.autore = autore;
        setStelle(stelle);            // il controllo è già scritto lì
    }

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
        return this.stelle > altra.stelle;
    }

    public void pubblica() {
        System.out.println(autore + " ha dato " + stelle + " stelle a " + locale);
    }

    @Override
    public String toString() {
        return autore + " ha dato " + stelle + " stelle a " + locale;
    }
}
