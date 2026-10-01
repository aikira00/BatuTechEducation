package conCostruttore;

public class RecensioneTestDrive {
    public static void main(String[] args) {
        // Una riga sola: ogni recensione nasce già con i suoi dati.
        Recensione r1 = new Recensione("Pizzeria Vesuvio", "marta_07", 4);

        Recensione r4 = new Recensione("Gelateria Aurora", "giulia.b", 5);
        // Recensione r = new Recensione();   // non compila più: il costruttore di default è sparito

        // println chiede a ogni recensione il suo toString()
        r1.pubblica();
        r4.pubblica();


        // Il costruttore rifiuta lo 0, ma la recensione nasce lo stesso.
        Recensione r2 = new Recensione("Trattoria da Gino", "samir.k", 0);
        r2.pubblica();
    }

}
