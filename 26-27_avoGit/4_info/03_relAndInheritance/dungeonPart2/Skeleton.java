package dungeonPart2;

public class Skeleton extends Monster {

    public Skeleton(String name, int health) {
        super(name, health);
    }

    @Override
    public void describe() {
        System.out.println(getName() + " si alza facendo rumore di ossa");
    }

    @Override
    public int hit(Die d) {
        return 2;              // lento ma costante
    }
}
