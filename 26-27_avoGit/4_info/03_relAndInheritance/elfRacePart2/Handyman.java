package elfRacePart2;

public class Handyman extends Elf {
    public Handyman(String name) {
        super(name);
    }

    @Override
    public void work(Gift g) {
        g.advance();
        System.out.println(getName() + " fa quello che serve: il regalo " + g.getNumber()
                + " ora è " + g.getState());
    }

    @Override
    public String toString() {
        return getName() + " (tuttofare)";
    }
}
