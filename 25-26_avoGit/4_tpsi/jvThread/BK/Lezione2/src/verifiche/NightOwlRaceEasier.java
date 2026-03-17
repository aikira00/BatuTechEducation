package verifiche;

public class NightOwlRaceEasier {
    public static void main(String[] args) {
        NightOwl owl1 = new NightOwl("Owl1");
        NightOwl owl2 = new NightOwl("Owl2");

        owl1.start();
        owl2.start();

        try {
            owl1.join();
            owl2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int owl1Stars = owl1.getStarsCount();
        int owl2Stars = owl2.getStarsCount();

        if (owl1Stars > owl2Stars) {
            System.out.println(owl1.getName() + " vince la gara con " + owl1Stars + " stelle!");
        } else if (owl2Stars > owl1Stars) {
            System.out.println(owl2.getName() + " vince la gara con " + owl2Stars + " stelle!");
        } else {
            System.out.println("La gara finisce in pareggio! Entrambi i gufi hanno contato " + owl1Stars + " stelle!");
        }
    }
}

class NightOwl extends Thread {
    private int starsCount;

    public NightOwl(String name) {
        super(name);
    }

    public void run() {
        countStars();
    }

    private void countStars() {
        System.out.println(getName() + " è pronto per contare le stelle...");

        // Simulazione del conteggio delle stelle
        starsCount = (int) (Math.random() * 100) + 1;

        System.out.println(getName() + " ha contato " + starsCount + " stelle!");
    }

    public int getStarsCount() {
        return starsCount;
    }
}
