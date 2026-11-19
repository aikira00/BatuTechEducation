package elfRacePart2;

public class Carpenter extends Elf {
    public Carpenter(String name) {
        super(name);
    }

    @Override
    public void work(Gift g) {
        if (g.getState().equals("da costruire")) {
            g.advance();
            System.out.println(getName() + " costruisce il regalo " + g.getNumber());
        } else {
            System.out.println(getName() + ": non tocca a me");
        }
    }

    @Override
    public String toString() {
        return getName() + " (falegname)";
    }
}
