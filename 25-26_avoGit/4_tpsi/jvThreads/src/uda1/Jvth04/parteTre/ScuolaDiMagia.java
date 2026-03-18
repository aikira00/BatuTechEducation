package Jvth04.parteTre;

public class ScuolaDiMagia {
    public static void main(String[] args) {
        Incantesimo t1 = new Incantesimo("Harry", "Expelliarmus");
        Incantesimo t2 = new Incantesimo("Hermione", "Alohomora");
        Incantesimo t3 = new Incantesimo("Ron", "Lumos");
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Risultati degli incantesimi:");
        System.out.println("Harry successo: " + t1.getSuccesso());
        System.out.println("Hermione successo: " + t2.getSuccesso());
        System.out.println("Ron successo: " + t3.getSuccesso());
    }
}
