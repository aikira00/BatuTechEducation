package edu.avo.senzaCostruttore;

public class SvegliaTestDrive {
    public static void main(String[] args) {
        Sveglia s = new Sveglia();   // la riga che mancava: senza, 5 errori "cannot find symbol"

        s.mostra();                  // prima di assegnare: i valori di default
        System.out.println("ripeti = " + s.ripeti);

        s.ora = 7;
        s.minuti = 30;
        s.etichetta = "scuola";
        s.mostra();
        s.suona();

        // Nessuno difende lo stato, e nessuno garantisce che la sveglia nasca completa.
        s.ora = 25;
        s.minuti = 75;
        s.mostra();
    }
}
