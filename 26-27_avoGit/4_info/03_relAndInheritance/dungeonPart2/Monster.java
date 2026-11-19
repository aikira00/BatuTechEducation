package dungeonPart2;

public class Monster extends GameCharacter {

    public Monster(String name, int health) {
        super(name, health);
    }

    public void describe() {
        System.out.println(getName() + " ti guarda");
    }

    public int hit(Die d) {
        return 1;
    }

    public void attack(Hero h, Die d) {
        int damage = hit(d);
        System.out.println(getName() + " colpisce: " + damage + " danni");
        h.takeDamage(damage);
    }
}
