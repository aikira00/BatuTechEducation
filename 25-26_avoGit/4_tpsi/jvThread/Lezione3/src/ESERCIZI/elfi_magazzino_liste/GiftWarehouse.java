package ESERCIZI.elfi_magazzino_liste;

import java.util.ArrayList;
import java.util.List;

public class GiftWarehouse {
    private int capacity;
    private List<String> availableGifts;
    private List<String> giftsList;

    public GiftWarehouse(ArrayList<String> gifts, int capacity) {
        this.capacity = capacity;
        this.availableGifts = new ArrayList<>();
        this.giftsList = new ArrayList<>(gifts);;
    }

    public synchronized void put(String gift) throws InterruptedException {
        while (availableGifts.size() == capacity) {
            System.out.println(Thread.currentThread().getName() + " magazzino pieno -> attende");
            wait();
        }
        availableGifts.add(gift);
        System.out.println("Magazzino riceve: " + gift + " | Magazzino: " + availableGifts.size());
        printWarehouseContents();
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (availableGifts.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " magazzino vuoto -> attende");
            wait();
        }
        String gift = availableGifts.remove(availableGifts.size()-1);
        //String gift = availableGifts.remove(0); //recupero primo elemento
        System.out.println("Magazzino consegna: " + gift + " | Magazzino: " + availableGifts.size());
        printWarehouseContents();
        notifyAll();
        return gift;
    }

    public List<String> getCurrentGifts() {
        return new ArrayList<>(availableGifts); // Ritorna una copia
    }

    public int getCurrentGiftsSize() {
        return availableGifts.size();
    }

    public  List<String> getListGifts() {
        return new ArrayList<>(giftsList); // copia per sicurezza
    }

    private void printWarehouseContents() {
        String magazzinoLog = "Magazzino contiene ";
        for(String s : availableGifts) {
            magazzinoLog += s.replaceAll("\n", " ") + " ";
        }
        System.out.println(magazzinoLog);
    }
}