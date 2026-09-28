package avo.conCostruttore;

public class RecensioneTestDrive {
    public static void main(String[] args) {
        // Una riga sola: ogni recensione nasce già con i suoi dati.
        Recensione r2 = new Recensione("Trattoria da Gino", "samir.k", 4);
        Recensione r4 = new Recensione("Gelateria Aurora", "giulia.b", 5);
        // Recensione r = new Recensione();   // non compila più: il costruttore di default è sparito

        // println chiede a ogni recensione il suo toString()
        System.out.println(r2);
        System.out.println(r4);

        System.out.println(r2.piuGenerosaDi(r4));   // this = r2, altra = r4: 4 > 5
        System.out.println(r4.piuGenerosaDi(r2));   // this = r4, altra = r2: 5 > 4

        // Il costruttore rifiuta lo 0, ma la recensione nasce lo stesso.
        Recensione r1 = new Recensione("Pizzeria Vesuvio", "marta_07", 0);
        System.out.println(r1);
    }
}
