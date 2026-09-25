package avo.ombra;

// PASSO 2 — diamo ai parametri nomi chiari: stelle, autore, locale.
// Compila senza errori. Ma dentro il setter "stelle" è il PARAMETRO,
// che copre la variabile d'istanza con lo stesso nome:
// stelle = stelle copia il parametro in se stesso, e l'oggetto non cambia.
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
        locale = locale;
    }

    public void setAutore(String autore) {
        autore = autore;
    }

    public void setStelle(int stelle) {
        if (stelle >= 1 && stelle <= 5) {   // il controllo funziona: legge il parametro
            stelle = stelle;                // l'assegnazione no: non tocca l'oggetto
        } else {
            System.out.println("Rifiutato: " + stelle + " stelle non è un voto valido (da 1 a 5)");
        }
    }

    public void pubblica() {
        // qui non c'è nessun parametro: "autore" è la variabile d'istanza
        System.out.println(autore + " ha dato " + stelle + " stelle a " + locale);
    }
}
