package Jvth04.dado;

public class Es3Main {

    public static void main(String[] args) {
        Es3DadoThread t1 = new Es3DadoThread("Gino");
        Es3DadoThread t2 = new Es3DadoThread("Pina");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Determine the winner
        System.out.println();
        System.out.println("=== RESULTS ===");
        System.out.println(t1.getName() + ": " + t1.getScore() + " in " + t1.getRolls() + " rolls");
        System.out.println(t2.getName() + ": " + t2.getScore() + " in " + t2.getRolls() + " rolls");
        System.out.println();

        if (t1.getRolls() < t2.getRolls()) {
            System.out.println(t1.getName() + " vince con meno lanci!");
        } else if (t2.getRolls() < t1.getRolls()) {
            System.out.println(t2.getName() + " vince con meno lanci!");
        } else {
            System.out.println("UAO! Patta!");
        }
    }
}

