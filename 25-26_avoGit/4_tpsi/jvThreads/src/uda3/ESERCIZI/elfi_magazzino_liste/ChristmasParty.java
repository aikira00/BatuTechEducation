package ESERCIZI.elfi_magazzino_liste;

import java.util.ArrayList;

public class ChristmasParty {
    public static void main(String[] args) throws InterruptedException {
        int m = 5;  // capacità magazzino
        ArrayList<String> gifts = new ArrayList<String>();
        gifts.add("orsetto");
        gifts.add("robot");
        gifts.add("bambola");
        gifts.add("libro");
        gifts.add("puzzle");
        gifts.add("bici");
        // "stop" sarà scelto casualmente dall'elfo produttore
        gifts.add("stop");
        GiftWarehouse warehouse = new GiftWarehouse(gifts, m);

        ElfProducer twinky = new ElfProducer(warehouse, "Twinky");
        ElfShipper blinky = new ElfShipper(warehouse, "Blinky");

        twinky.start();
        blinky.start();

        //Thread.sleep(10000);
        
        //twinky.stopElfProducer();
        //blinky.stopElfShipper();

        twinky.join();
        blinky.join();

        System.out.println("Festa finita!");
        System.out.println("️Twinky ha prodotto " + twinky.getProducedGifts().size() + " regali:");
        for (String g : twinky.getProducedGifts()) System.out.println("  - " + g);

        System.out.println("Blinky ha spedito " + blinky.getShippedGifts().size() + " regali:");
        for (String g : blinky.getShippedGifts()) System.out.println("  - " + g);

        System.out.println("Regali rimasti nel magazzino (" + warehouse.getCurrentGiftsSize() + "):");
        for (String g : warehouse.getCurrentGifts()) System.out.println("  - " + g);
    }
}