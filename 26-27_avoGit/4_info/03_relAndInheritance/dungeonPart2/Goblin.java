package dungeonPart2;

public class Goblin extends Monster {

    public Goblin(String name, int health) {
        super(name, health);
    }

    @Override
    public void describe() {
        System.out.println(getName() + " ringhia nell'ombra");
    }

    @Override
    public int hit(Die d) {
        return d.roll();       // colpisce come capita
    }
}
