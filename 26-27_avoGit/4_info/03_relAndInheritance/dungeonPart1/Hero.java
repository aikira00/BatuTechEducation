package dungeonPart1;

public class Hero {
    private String name;
    private int health;
    private Weapon weapon;
    private Potion potion;

    public Hero(String name, int health, Weapon weapon) {
        this.name = name;
        if (health > 0) {
            this.health = health;
        } else {
            System.out.println("Vita non valida, uso 20");
            this.health = 20;
        }
        this.weapon = weapon;            // la riceve: c'era già
        this.potion = new Potion(6);     // la crea: è sua
    }

    public Hero(String name, Weapon weapon) {
        this(name, 20, weapon);
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            System.out.println("Rifiutato: " + name + " non guarisce con " + damage + " danni");
            return;
        }
        health = health - damage;
        if (health < 0) {
            health = 0;
        }
    }

    public void attack(Monster m, Die d) {
        int damage = weapon.hit(d);
        System.out.println(name + " colpisce " + m.getName() + " con " + weapon + ": " + damage + " danni");
        m.takeDamage(damage);
    }

    public void heal() {
        int points = potion.drink();
        if (points > 0) {
            health = health + points;
            System.out.println(name + " beve la pozione: +" + points);
        } else {
            System.out.println(name + " ha già bevuto la pozione");
        }
    }

    @Override
    public String toString() {
        return name + " (vita " + health + ") con " + weapon;
    }
}
