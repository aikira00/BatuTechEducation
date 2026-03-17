public class Es2CorridoreThread extends Thread {
    private final int distanzaTotale;
    private int distanzaPercorsa;

    private boolean corri;

    public Es2CorridoreThread(int distanzaTotale) {
        this.distanzaTotale = distanzaTotale;
        this.distanzaPercorsa = 0;
        this.corri = true;
    }

    public void stopCorridore(){
        this.corri = false;
    }


    @Override
    public void run() {
        while (corri && distanzaPercorsa < distanzaTotale) {
            System.out.println("Il corridore sta correndo...");
            // Simuliamo la corsa aumentando la distanza percorsa di 10 metri ogni ciclo
            distanzaPercorsa += 10;
            try {
                System.out.println("Il corridore ha corso " + distanzaPercorsa);
                System.out.println("Il corridore sta riposando...");
                Thread.sleep(1000); // Simuliamo il tempo di corsa (1 secondo)
            } catch (InterruptedException e) {
                // Se il thread è interrotto, esce dal ciclo
                System.out.println("Il corridore è stato interrotto.");

            }
        }

        if (!corri) {
            System.out.println("Il corridore non ha finito di correre!");
        } else {
            System.out.println("Il corridore ha completato la corsa. Distanza percorsa: " + distanzaPercorsa + " su " + distanzaTotale);
        }
    }
}
