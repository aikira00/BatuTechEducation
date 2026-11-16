package dungeon2;

public class Pozione {
    private int cura;
    private boolean bevuta;

    public Pozione(int cura) {
        if (cura > 0) {
            this.cura = cura;
        } else {
            System.out.println("Cura non valida, uso 6");
            this.cura = 6;
        }
        this.bevuta = false;
    }

    // La prima volta dà la cura, poi 0: la pozione è finita.
    public int bevi() {
        if (bevuta) {
            return 0;
        }
        bevuta = true;
        return cura;
    }
}
