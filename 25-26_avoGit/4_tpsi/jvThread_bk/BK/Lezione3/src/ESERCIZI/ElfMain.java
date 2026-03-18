package ESERCIZI;

public class ElfMain {
    public static void main(String[] args) {
        ElfGiftWrappingStation station = new ElfGiftWrappingStation();

        Elf blinky = new Elf("Blinky", station);
        Elf winky = new Elf("Winky", station);

        blinky.start();
        winky.start();
    }
}
