package ESERCIZI.elfi_magazzino_liste;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ElfProducer extends Thread{
    private GiftWarehouse warehouse;
    private List<String> producedGifts;
    private boolean stop = false;

    public ElfProducer(GiftWarehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.setName(name);
        producedGifts = new ArrayList<>();
    }

    public void run() {
        Random rand = new Random();
        try {

            List<String> availableGifts = warehouse.getListGifts();
            String gift = availableGifts.get(rand.nextInt(availableGifts.size()));
            while(!gift.equalsIgnoreCase("stop") && !stop){
                System.out.println(this.getName() +  " ha creato " + gift);
                warehouse.put(gift);
                producedGifts.add(gift);
                Thread.sleep(rand.nextInt(100));
                gift = availableGifts.get(rand.nextInt(availableGifts.size()));
            }
            //per inserire regalo stop per elfo corriere
            System.out.println(this.getName() +  " ha creato " + gift);
            warehouse.put(gift);
            producedGifts.add(gift);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() +  " ha finito!!");
    }

    public List<String> getProducedGifts() {
        return new ArrayList<>(producedGifts); // Ritorna una copia
    }

    public void stopElfProducer(){
        this.stop = true;
    }
}
