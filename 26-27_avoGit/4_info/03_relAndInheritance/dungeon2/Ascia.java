package dungeon2;

public class Ascia extends Arma {

    public Ascia() {
        super("l'ascia", 0);
    }

    @Override
    public int colpo(Dado d) {
        int tiro = super.colpo(d);     // un tiro solo
        if (tiro == 1) {
            System.out.println("L'ascia scivola di mano!");
            return 0;
        }
        return tiro * 2;
    }
}
