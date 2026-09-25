package edu.avo.attrPriv;

// PASSO 2 — variabili private, metodi public.
// Da fuori nessuno può più toccare lo stato... ma nemmeno impostarlo.
public class Cane {
    private String nome;
    private String razza;
    private String taglia;
    private int eta;

    public void abbaia() {
        System.out.println(nome + ": Bau bau!");
    }

    public void presentati() {
        System.out.println(nome + ", " + razza + ", taglia " + taglia + ", " + eta + " anni");
    }
}
