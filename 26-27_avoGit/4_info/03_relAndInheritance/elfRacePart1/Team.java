package elfRacePart1;

public class Team {
    private String name;
    private Elf[] elves;
    private int count;
    private Gift[] gifts;
    private int giftCount;
    private Letter[] letters;
    private int letterCount;
    private int turns;

    public Team(String name, int numberOfGifts) {
        this.name = name;
        this.elves = new Elf[4];          // al massimo 4 elfi
        this.count = 0;
        if (numberOfGifts > 0) {
            this.gifts = new Gift[numberOfGifts];
        } else {
            System.out.println(name + ": numero di regali non valido, ne aspetto 1");
            this.gifts = new Gift[1];
        }
        this.giftCount = 0;               // i regali nascono durante la gara
        this.letters = new Letter[gifts.length];   // una letterina per regalo
        this.letterCount = 0;
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

    public void receive(Letter l) {
        if (letterCount == letters.length) {
            System.out.println(name + ": non aspetto altri regali, una letterina resta fuori");
        } else {
            letters[letterCount] = l;
            letterCount++;
        }
    }

    // il primo regalo non ancora pronto; se sono tutti pronti, quello nuovo dalla prossima letterina; null se non ce ne sono
    private Gift nextGift() {
        for (int i = 0; i < giftCount; i++) {
            if (!gifts[i].isReady()) {
                return gifts[i];
            }
        }
        // nessun regalo in lavorazione: la squadra prende la prossima letterina e crea il regalo, con il numero 1, 2, 3, …
        if (giftCount < letterCount) {
            gifts[giftCount] = new Gift(giftCount + 1, letters[giftCount]);
            giftCount++;
            return gifts[giftCount - 1];
        }
        return null;
    }

    public boolean isFinished() {
        return readyCount() == gifts.length;   // tutti quelli che aspettava
    }

    public int readyCount() {
        int ready = 0;
        for (int i = 0; i < giftCount; i++) {
            if (gifts[i].isReady()) {
                ready++;
            }
        }
        return ready;
    }

    // Babbo Natale ritira i regali: un array nuovo, solo quelli pronti
    public Gift[] deliver() {
        Gift[] sack = new Gift[readyCount()];
        int n = 0;
        for (int i = 0; i < giftCount; i++) {
            if (gifts[i].isReady()) {
                sack[n] = gifts[i];
                n++;
            }
        }
        return sack;
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
