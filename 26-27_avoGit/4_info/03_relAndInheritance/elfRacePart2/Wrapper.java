package elfRacePart2;

public class Wrapper extends Elf {
    public Wrapper(String name) {
        super(name);
    }

    @Override
    public void work(Gift g) {
        if (g.getState().equals("da incartare")) {
            g.advance();
            System.out.println(getName() + " incarta il regalo " + g.getNumber());
        } else {
            System.out.println(getName() + ": non tocca a me");
        }
    }

    @Override
    public String toString() {
        return getName() + " (impacchettatore)";
    }
}
