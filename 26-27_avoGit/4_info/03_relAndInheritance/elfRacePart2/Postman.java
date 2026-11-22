package elfRacePart2;

public class Postman extends Elf {
    public Postman(String name) {
        super(name);
    }

    @Override
    public void work(Gift g) {
        if (g.getState().equals("in attesa")) {
            String text = g.readLetter();
            System.out.println(getName() + " legge la letterina del regalo " + g.getNumber()
                    + ": " + text);
        } else {
            System.out.println(getName() + ": non tocca a me");
        }
    }

    @Override
    public String toString() {
        return getName() + " (postino)";
    }
}
