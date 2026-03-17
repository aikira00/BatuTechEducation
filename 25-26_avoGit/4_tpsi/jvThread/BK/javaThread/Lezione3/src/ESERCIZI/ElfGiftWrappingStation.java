package ESERCIZI;

public class ElfGiftWrappingStation {
    private boolean isPaperAvailable = true;
    private boolean isRibbonAvailable = true;

    public synchronized void wrapGift(String elfName) throws InterruptedException {
        while (!isPaperAvailable) {
            wait();
        }
        System.out.println(elfName + " ho preso la carta.");
        isPaperAvailable = false;
        System.out.println(elfName + " sta avvolgendo il regalo.");
        isPaperAvailable = true;
        System.out.println(elfName + " rilascio la carta.");
        notifyAll();
    }

    public synchronized void decorateGift(String elfName) throws InterruptedException {
        while (!isRibbonAvailable) {
            wait();
        }
        isRibbonAvailable = false;
        System.out.println(elfName + " sta decorando il regalo.");
        isRibbonAvailable = true;
        System.out.println(elfName + " rilascia la carta.");
        notifyAll();
    }
}