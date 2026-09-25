package avo.nomiDiversi;

// PASSO 1 — la soluzione dell'esercitazione: funziona.
// I parametri dei setter hanno nomi diversi dalle variabili d'istanza (l, a, s),
// quindi non c'è modo di confonderli. Però setStelle(int s) non dice molto:
// chi legge la firma non sa cosa sia "s".
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

    public void setLocale(String l) {
        locale = l;
    }

    public void setAutore(String a) {
        autore = a;
    }

    public void setStelle(int s) {
        if (s >= 1 && s <= 5) {
            stelle = s;
        } else {
            System.out.println("Rifiutato: " + s + " stelle non è un voto valido (da 1 a 5)");
        }
    }

    public void pubblica() {
        System.out.println(autore + " ha dato " + stelle + " stelle a " + locale);
    }
}
