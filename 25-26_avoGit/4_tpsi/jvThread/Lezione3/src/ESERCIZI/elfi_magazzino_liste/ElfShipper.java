package ESERCIZI.elfi_magazzino_liste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElfShipper extends Thread {
    private GiftWarehouse warehouse;
    private List<String> shippedGifts;
    private boolean stop = false;

    public ElfShipper(GiftWarehouse warehouse,  String name) {
        this.warehouse = warehouse;
        this.setName(name);
        shippedGifts = new ArrayList<>();
    }

    public void run() {
        Random rand = new Random();
        try {
            String giftToShip = warehouse.take();
            while(!giftToShip.equalsIgnoreCase("stop") && !stop) {
                System.out.println(this.getName() +  " ha spedito " + giftToShip);
                shippedGifts.add(giftToShip);
                Thread.sleep(rand.nextInt(3000));
                giftToShip = warehouse.take();
            }
            // regalo stop
            System.out.println(this.getName() +  " ha spedito " + giftToShip);
            shippedGifts.add(giftToShip);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() +  " ha finito!!");
    }

    public List<String> getShippedGifts() {
        return new ArrayList<>(shippedGifts); // Ritorna una copia
    }

    public void stopElfShipper(){
        this.stop = true;
    }
}