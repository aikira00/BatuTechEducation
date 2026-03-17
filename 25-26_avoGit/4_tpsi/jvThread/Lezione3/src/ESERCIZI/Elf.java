package ESERCIZI;

public class Elf extends Thread {
    private String name;
    private ElfGiftWrappingStation station;

    public Elf(String name, ElfGiftWrappingStation station) {
        this.name = name;
        this.station = station;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 1000)); // Simulazione del tempo per svolgere il compito
                station.wrapGift(name);
                Thread.sleep((int) (Math.random() * 1000)); // Simulazione del tempo per svolgere il compito
                station.decorateGift(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
