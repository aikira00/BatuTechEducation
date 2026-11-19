package dungeonPart2;

public class Hero extends GameCharacter {
    private Weapon weapon;
    private Potion potion;

    public Hero(String name, Weapon weapon) {
        super(name, 20);
        this.weapon = weapon;
        this.potion = new Potion(6);
    }

    public void wield(Weapon newWeapon) {
        weapon = newWeapon;
        System.out.println(getName() + " impugna " + weapon);
    }

    public void attack(Monster m, Die d) {
        int damage = weapon.hit(d);
        System.out.println(getName() + " colpisce " + m.getName() + " con " + weapon + ": " + damage + " danni");
        m.takeDamage(damage);
    }

    public void heal() {
        int points = potion.drink();
        if (points > 0) {
            recover(points);
            System.out.println(getName() + " beve la pozione: +" + points);
        } else {
            System.out.println(getName() + " ha già bevuto la pozione");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " con " + weapon;
    }
}
