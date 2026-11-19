package elfRacePart1;

public class Elf {
    private String name;
    private String job;

    public Elf(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void work(Gift g) {
        switch (job) {
            case "postino":
                if (g.getState().equals("da leggere")) {
                    g.advance();
                    System.out.println(name + " legge la letterina del regalo " + g.getNumber());
                } else {
                    System.out.println(name + ": non tocca a me");
                }
                break;
            case "falegname":
                if (g.getState().equals("da costruire")) {
                    g.advance();
                    System.out.println(name + " costruisce il regalo " + g.getNumber());
                } else {
                    System.out.println(name + ": non tocca a me");
                }
                break;
            case "impacchettatore":
                if (g.getState().equals("da incartare")) {
                    g.advance();
                    System.out.println(name + " incarta il regalo " + g.getNumber());
                } else {
                    System.out.println(name + ": non tocca a me");
                }
                break;
            case "tuttofare":      // passo 4
                g.advance();
                System.out.println(name + " fa quello che serve: il regalo " + g.getNumber()
                        + " ora è " + g.getState());
                break;
            default:
                System.out.println(name + ": non so fare niente");
        }
    }

    @Override
    public String toString() {
        return name + " (" + job + ")";
    }
}
