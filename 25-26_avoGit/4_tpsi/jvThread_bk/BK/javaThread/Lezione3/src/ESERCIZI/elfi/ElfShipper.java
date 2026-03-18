package ESERCIZI.elfi;

public class ElfShipper extends Thread{

    private GiftMachine gift;

    public ElfShipper(GiftMachine g, String n){
        this.gift = g;
        this.setName(n);
    }

    public void run(){
        String giftToShip = gift.shipGift();
        while(!giftToShip.equalsIgnoreCase("stop")){
            System.out.println(this.getName() + " => cerco di spedire regalo.");
             giftToShip = gift.shipGift();
        }
        System.out.println(this.getName() + " ho ricevuto regalo stop, termino.");
    }


}
