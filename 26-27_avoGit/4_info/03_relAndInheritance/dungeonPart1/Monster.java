package dungeonPart1;

// Il campo type e i due switch sono voluti: si tolgono con l'ereditarietà.
public class Monster {
    private String name;
    private String type;   // "goblin" oppure "scheletro"
    private int health;

    public Monster(String name, String type, int health) {
        this.name = name;
        this.type = type;
        if (health > 0) {
            this.health = health;
        } else {
            System.out.println("Vita non valida, uso 10");
            this.health = 10;
        }
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

    public void describe() {
        switch (type) {
            case "goblin":
                System.out.println(name + " ringhia nell'ombra");
                break;
            case "scheletro":
                System.out.println(name + " si alza facendo rumore di ossa");
                break;
            default:
                System.out.println(name + " ti guarda");
        }
    }

    public void attack(Hero h, Die d) {
        int damage;
        switch (type) {
            case "goblin":
                damage = d.roll();    // colpisce come capita
                break;
            case "scheletro":
                damage = 2;           // lento ma costante
                break;
            default:
                damage = 1;
        }
        System.out.println(name + " colpisce: " + damage + " danni");
        h.takeDamage(damage);
    }

    @Override
    public String toString() {
        return name + " (" + type + ", vita " + health + ")";
    }
}
