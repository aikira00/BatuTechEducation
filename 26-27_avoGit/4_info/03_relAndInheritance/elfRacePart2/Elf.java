package elfRacePart2;

public class Elf {
    private String name;

    public Elf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void work(Gift g) {
        System.out.println(name + ": non so fare niente");
    }

    @Override
    public String toString() {
        return name;
    }
}
