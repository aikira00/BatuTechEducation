package edu.avo.senzaCostruttore;

// PASSO 1 — lo scenario A dell'esercitazione "Siete il compilatore", corretto.
// Nessun costruttore: una sveglia appena creata ha i valori di default.
class Sveglia {
    int ora;
    int minuti;
    String etichetta;
    boolean ripeti;

    void suona() {
        System.out.println("DRIIIN!");
    }

    void mostra() {
        System.out.println("Sveglia alle " + ora + ":" + minuti + " (" + etichetta + ")");
    }
}
