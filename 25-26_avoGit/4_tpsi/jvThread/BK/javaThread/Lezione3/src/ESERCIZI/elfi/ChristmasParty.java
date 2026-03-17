package ESERCIZI.elfi;
import java.util.ArrayList;
public class ChristmasParty {
    public static void main(String[] args) {
        ArrayList<String> gifts = new ArrayList<String>();
        gifts.add("orsetto");
        gifts.add("robot");
        gifts.add("bambola");
        gifts.add("libro");
        gifts.add("puzzle");
        gifts.add("bici");
        gifts.add("stop");

        GiftMachine gm = new GiftMachine(gifts);
        ElfShipper elfBlinky = new ElfShipper(gm, "Blinky");
        ElfProducer elfTwinky = new ElfProducer(gm, "Twinky");
        elfBlinky.start();
        elfTwinky.start();
        try{
            elfBlinky.join();
            elfTwinky.join();
        }
        catch (InterruptedException e){
            throw new RuntimeException();
        }

        System.out.println("Sono stati prodotti " + gm.getTotalGitfsProduced() + " regali");
        System.out.println("Sono stati spediti " + gm.getTotalGiftsShipped() + " regali");
    }
}
