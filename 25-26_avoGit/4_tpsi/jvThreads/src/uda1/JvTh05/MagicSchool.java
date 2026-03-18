package JvTh05;

public class MagicSchool {
    public static void main(String[] args) {
        Spell t1 = new Spell("Harry", "Expelliarmus", 5);
        Spell t2 = new Spell("Hermione", "Alohomora", 5);
        Spell t3 = new Spell("Ron", "Lumos", 5);
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
        System.out.println("Harry ha lanciato con succcesso " + t1.getSuccededSpells() + " incantesimi.");
        System.out.println("Hermione ha lanciato con succcesso " + t2.getSuccededSpells() + " incantesimi.");
        System.out.println("Ron ha lanciato con succcesso " + t3.getSuccededSpells() + " incantesimi.");
    }
}
