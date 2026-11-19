package dungeonPart1;

import java.util.Random;

public class Die {
    private int faces;
    private Random generator;

    public Die(int faces, int seed) {
        if (faces >= 2) {
            this.faces = faces;
        } else {
            System.out.println("Facce non valide, uso 6");
            this.faces = 6;
        }
        this.generator = new Random(seed);   // stesso seme, stessi tiri
    }

    public Die(int faces) {
        this(faces, 42);
    }

    public int roll() {
        return generator.nextInt(faces) + 1;
    }

    @Override
    public String toString() {
        return "Dado a " + faces + " facce";
    }
}
