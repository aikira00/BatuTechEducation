package tappa1Swing;

import java.util.Random;

public class Dado {
    private int facce;
    private Random generatore;

    public Dado(int facce, int seme) {
        if (facce >= 2) {
            this.facce = facce;
        } else {
            System.out.println("Facce non valide, uso 6");
            this.facce = 6;
        }
        this.generatore = new Random(seme);   // stesso seme, stessi tiri
    }

    public Dado(int facce) {
        this(facce, 42);
    }

    public int tira() {
        return generatore.nextInt(facce) + 1;
    }

    @Override
    public String toString() {
        return "Dado a " + facce + " facce";
    }
}
