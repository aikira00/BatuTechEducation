package avo.conThis;

// PASSO 3 — this.stelle è la variabile dell'oggetto su cui è stato chiamato il metodo,
// stelle da solo è il parametro. Nomi chiari e nessuna ambiguità.
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

    public void pubblica() {
        // nessun parametro che copra le variabili: this si può omettere
        System.out.println(autore + " ha dato " + stelle + " stelle a " + locale);
    }
}
