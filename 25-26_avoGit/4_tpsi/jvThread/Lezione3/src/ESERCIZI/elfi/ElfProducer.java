package ESERCIZI.elfi;

public class ElfProducer extends Thread{
    private GiftWarehouse gift;

    public ElfProducer(GiftWarehouse g, String n){
        this.gift = g;
        this.setName(n);
    }

    public void run(){
        int randomIndex = (int) Math.round(Math.floor(Math.random() * (gift.getGiftsList().size())));
        System.out.println("random index " + randomIndex);
        String giftToPrepare = gift.getGiftsList().get(randomIndex);
        while(!giftToPrepare.equalsIgnoreCase("stop")){
            System.out.println(this.getName() + " => cerco di prepare regalo.");
            gift.produceGift(giftToPrepare);
            randomIndex = (int) Math.round(Math.floor(Math.random() * (gift.getGiftsList().size())));
            System.out.println("random index " + randomIndex);
            giftToPrepare = gift.getGiftsList().get(randomIndex);
        }
        gift.produceGift(giftToPrepare);//qui produce regalo stop per spedizione
        System.out.println(this.getName() + " ho prodotto regalo stop, termino.");
    }


}
