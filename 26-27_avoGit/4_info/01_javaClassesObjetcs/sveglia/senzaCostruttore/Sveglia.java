package senzaCostruttore;

// PASSO 1 — lo scenario A dell'esercitazione "Siete il compilatore", corretto.
// Nessun costruttore: una sveglia appena creata ha i valori di default.
class Sveglia {
    int ora;
    int minuti;
    String etichetta;


    void suona() {
        System.out.println(etichetta + " DRIIIN!");
    }

    void mostra() {
        System.out.println("Sveglia alle " + ora + ":" + minuti + " (" + etichetta + ")");
    }
}
