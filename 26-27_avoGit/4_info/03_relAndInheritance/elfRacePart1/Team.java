package elfRacePart1;

public class Team {
    private String name;
    private Elf[] elves;
    private int count;
    private Gift[] gifts;
    private int turns;

    public Team(String name, int giftCount) {
        this.name = name;
        this.elves = new Elf[4];          // al massimo 4 elfi
        this.count = 0;
        if (giftCount > 0) {
            this.gifts = new Gift[giftCount];
        } else {
            System.out.println(name + ": numero di regali non valido, ne preparo 1");
            this.gifts = new Gift[1];
        }
        for (int i = 0; i < gifts.length; i++) {
            gifts[i] = new Gift(i + 1);   // i regali li crea la squadra
        }
        this.turns = 0;
    }

    public String getName() {
        return name;
    }

    public void add(Elf e) {
        if (count == elves.length) {
            System.out.println(name + ": squadra piena, " + e.getName() + " resta fuori");
        } else {
            elves[count] = e;
            count++;
        }
    }

    // il primo regalo non ancora pronto; null se sono tutti pronti
    private Gift nextGift() {
        for (int i = 0; i < gifts.length; i++) {
            if (!gifts[i].isReady()) {
                return gifts[i];
            }
        }
        return null;
    }

    public boolean isFinished() {
        return nextGift() == null;
    }

    public int readyCount() {
        int ready = 0;
        for (int i = 0; i < gifts.length; i++) {
            if (gifts[i].isReady()) {
                ready++;
            }
        }
        return ready;
    }

    public void playTurn() {
        if (!isFinished()) {
            turns++;
            System.out.println(name + ":");
            for (int i = 0; i < count; i++) {
                Gift g = nextGift();          // per ogni elfo, di nuovo
                if (g != null) {
                    System.out.print("  ");
                    elves[i].work(g);
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = name + " [";
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                s = s + ", ";
            }
            s = s + elves[i];
        }
        s = s + "]: " + readyCount() + " regali pronti su " + gifts.length;
        if (isFinished()) {
            s = s + ", finito al turno " + turns;
        }
        return s;
    }
}
