package Jvth04.parteTre;

public class Incantesimo extends Thread {

    private String mago;
    private String incantesimo;
    private boolean successo;

    public Incantesimo(String mago, String incantesimo) {
        this.mago = mago;
        this.incantesimo = incantesimo;
    }
    public void run() {
        System.out.println(mago + " lancia " + incantesimo + "...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
        successo = Math.random() > 0.4; // 80% di successo
        System.out.println(mago + " ha " + (successo ? "successo" : "fallito") + " con " +
                incantesimo + "!");
    }
    public boolean getSuccesso() {
        return successo;
    }
}
