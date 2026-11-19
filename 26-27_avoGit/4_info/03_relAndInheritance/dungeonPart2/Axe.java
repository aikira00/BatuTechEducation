package dungeonPart2;

public class Axe extends Weapon {

    public Axe() {
        super("l'ascia", 0);
    }

    @Override
    public int hit(Die d) {
        int roll = super.hit(d);       // un tiro solo
        if (roll == 1) {
            System.out.println("L'ascia scivola di mano!");
            return 0;
        }
        return roll * 2;
    }
}
