package dungeonPart1;

import java.util.Scanner;

public class DungeonTestDrive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Die die = new Die(6);
        Weapon dagger = new Weapon("il pugnale", 1);
        Hero hero = new Hero("Anna", dagger);

        Monster[] monsters = new Monster[2];
        monsters[0] = new Monster("Grunt", "goblin", 8);
        monsters[1] = new Monster("Ossetto", "scheletro", 12);

        for (int i = 0; i < monsters.length && hero.isAlive(); i++) {
            Monster m = monsters[i];
            m.describe();
            boolean fled = false;

            while (hero.isAlive() && m.isAlive() && !fled) {
                System.out.println(hero + " contro " + m);
                System.out.print("a = attacca, c = cura, f = fuggi: ");
                String choice = in.next();

                if (choice.equals("a")) {
                    hero.attack(m, die);
                    if (m.isAlive()) {
                        m.attack(hero, die);
                    }
                } else if (choice.equals("c")) {
                    hero.heal();
                    m.attack(hero, die);   // curarsi costa il turno
                } else {
                    System.out.println(hero.getName() + " scappa");
                    fled = true;
                }
            }
        }

        if (hero.isAlive()) {
            System.out.println(hero + " esce dal dungeon");
        } else {
            System.out.println(hero.getName() + " non ce l'ha fatta");
        }
    }
}
