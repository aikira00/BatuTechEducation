package dungeonPart2;

public class Weapon {
    private String name;
    private int bonus;

    public Weapon(String name, int bonus) {
        this.name = name;
        if (bonus >= 0) {
            this.bonus = bonus;
        } else {
            System.out.println("Bonus non valido, uso 0");
            this.bonus = 0;
        }
    }

    public int hit(Die d) {
        return d.roll() + bonus;
    }

    @Override
    public String toString() {
        return name;
    }
}
