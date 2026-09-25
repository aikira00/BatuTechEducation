package edu.avo;

// PASSO 1 — tutto aperto: chiunque può scrivere qualunque cosa nelle variabili
public class Cane {
    String nome;
    String razza;
    String taglia;
    int eta;

    void abbaia() {
        System.out.println(nome + ": Bau bau!");
    }

    void presentati() {
        System.out.println(nome + ", " + razza + ", taglia " + taglia + ", " + eta + " anni");
    }
}
