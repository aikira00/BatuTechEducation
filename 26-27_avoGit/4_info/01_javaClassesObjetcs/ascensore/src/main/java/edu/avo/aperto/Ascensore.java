package edu.avo.aperto;

// PASSO 1 — lo scenario B dell'esercitazione "Siete il compilatore", corretto
// nel secondo modo: si aggiunge il metodo apriPorte() che mancava.
// Variabili aperte e nessun limite: l'ascensore va dove gli si dice.
class Ascensore {
    int piano;
    int capienza;

    void sali() {
        piano = piano + 1;
        System.out.println("Salgo al piano " + piano);
    }

    void scendi() {
        piano = piano - 1;
        System.out.println("Scendo al piano " + piano);
    }

    void apriPorte() {
        System.out.println("Porte aperte al piano " + piano);
    }
}
