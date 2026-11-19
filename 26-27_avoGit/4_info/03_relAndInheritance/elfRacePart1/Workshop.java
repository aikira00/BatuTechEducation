package elfRacePart1;

public class Workshop {
    private Team[] teams;
    private int count;

    public Workshop() {
        this.teams = new Team[4];         // al massimo 4 squadre
        this.count = 0;
    }

    public void add(Team t) {
        if (count == teams.length) {
            System.out.println("Fabbrica piena, " + t.getName() + " resta fuori");
        } else {
            teams[count] = t;
            count++;
        }
    }

    public void race(int turns) {
        if (turns <= 0) {
            System.out.println("Con " + turns + " turni la gara non si fa");
        } else {
            System.out.println("Le squadre in gara:");
            for (int i = 0; i < count; i++) {
                System.out.println(teams[i]);
            }
            for (int t = 1; t <= turns; t++) {
                System.out.println("--- Turno " + t + " ---");
                for (int i = 0; i < count; i++) {
                    teams[i].playTurn();
                }
            }
            System.out.println("--- Risultati ---");
            for (int i = 0; i < count; i++) {
                System.out.println(teams[i]);
            }
        }
    }
}
