package ESERCIZI.elfi;

import java.util.ArrayList;

public class GiftWarehouse {
    private ArrayList<String> giftsList;
    private boolean giftReady;
    private String giftProduced;

    private static int totalGitfsProduced = 0;
    private static int totalGiftsShipped = 0;

    public GiftWarehouse(ArrayList<String> gifts) {
        giftsList = gifts;
        giftReady = false;
    }

    public synchronized void produceGift(String giftToProduce) {
        while (giftReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre aspettavo di creare un regalo ");
            }
        }

        giftReady = true;
        giftProduced = giftToProduce;
        try{
            System.out.println(Thread.currentThread().getName() + " preparo il regalo " +
                    "  => " + giftToProduce);
            Thread.sleep(6000);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre preparavo il regalo");
        }
        totalGitfsProduced++;
        System.out.println(Thread.currentThread().getName() + " => Regalo numero " + totalGitfsProduced + " prodotto: " + giftProduced);
        notifyAll();
    }

    public synchronized String shipGift() {
        while (!giftReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre aspettavo di spedire un regalo ");
            }
        }
        giftReady = false;
        try{
            System.out.println(Thread.currentThread().getName() + " preparo la spedizione per  => " + giftProduced);
            Thread.sleep(6000);
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " sono stato interrotto mentre preparavo la spedizione");
        }

        totalGiftsShipped++;
        System.out.println(Thread.currentThread().getName() + " => Regalo numero " + totalGiftsShipped + " spedito: " + giftProduced);
        notifyAll();
        return giftProduced;
    }

    public ArrayList<String> getGiftsList(){
        return giftsList;
    }

    public int getTotalGitfsProduced(){
        return totalGitfsProduced;
    }

    public int getTotalGiftsShipped(){
        return totalGiftsShipped;
    }

}
