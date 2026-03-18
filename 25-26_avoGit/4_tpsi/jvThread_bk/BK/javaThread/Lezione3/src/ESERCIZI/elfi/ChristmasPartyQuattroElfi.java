package ESERCIZI.elfi;
import java.util.ArrayList;

public class ChristmasPartyQuattroElfi {
    public static void main(String[] args) {
        ArrayList<String> gifts = new ArrayList<String>();
        gifts.add("orsetto");
        gifts.add("robot");
        gifts.add("bambola");
        gifts.add("libro");
        gifts.add("puzzle");
        gifts.add("bici");
        gifts.add("lego");
        gifts.add("spiderman");
        gifts.add("unicorno");
        gifts.add("pennarelli");
        gifts.add("stop");

        GiftMachine gm = new GiftMachine(gifts);
        ElfShipper elfBlinky = new ElfShipper(gm, "Blinky");
        ElfShipper elfFlinky = new ElfShipper(gm, "Flinky");
        ElfProducer elfTwinky = new ElfProducer(gm, "Twinky");
        ElfProducer elfDwinky = new ElfProducer(gm, "Dwinky");
        elfBlinky.start();
        elfFlinky.start();
        elfTwinky.start();
        elfDwinky.start();
        try{
            elfBlinky.join();
            elfFlinky.join();
            elfTwinky.join();
            elfDwinky.join();
        }
        catch (InterruptedException e){
            throw new RuntimeException();
        }

        System.out.println("Sono stati prodotti " + gm.getTotalGitfsProduced() + " regali");
        System.out.println("Sono stati spediti " + gm.getTotalGiftsShipped() + " regali");
    }
}
