package Jvth04;

public class ScuolaDiMagia {
    public static void main(String[] args) {
        Incantesimo t1 = new Incantesimo("Harry", "Expelliarmus");
        Incantesimo t2 = new Incantesimo("Hermione", "Alohomora");
        Incantesimo t3 = new Incantesimo("Ron", "Lumos");
        t1.start();
        t2.start();
        t3.start();
        System.out.println("Il main termina, ma gli incantesimi potrebbero non essere finiti!");
    }
}