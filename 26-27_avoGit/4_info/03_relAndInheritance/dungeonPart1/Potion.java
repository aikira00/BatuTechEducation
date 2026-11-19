package dungeonPart1;

public class Potion {
    private int healing;
    private boolean drunk;

    public Potion(int healing) {
        if (healing > 0) {
            this.healing = healing;
        } else {
            System.out.println("Cura non valida, uso 6");
            this.healing = 6;
        }
        this.drunk = false;
    }

    // La prima volta dà la cura, poi 0: la pozione è finita.
    public int drink() {
        if (drunk) {
            return 0;
        }
        drunk = true;
        return healing;
    }
}
