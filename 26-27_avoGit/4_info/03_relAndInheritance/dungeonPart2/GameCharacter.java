package dungeonPart2;

public class GameCharacter {
    private String name;
    private int health;

    public GameCharacter(String name, int health) {
        this.name = name;
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

    public void recover(int points) {
        if (points < 0) {
            System.out.println("Rifiutato: " + name + " non guarisce di " + points);
            return;
        }
        health = health + points;
    }

    @Override
    public String toString() {
        return name + " (vita " + health + ")";
    }
}
