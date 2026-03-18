package JvTh08;

import java.util.Random;

public class Es2CorridoreThreadV2 extends Thread {
    private final int distanzaTotale;
    private int distanzaPercorsa;
    private boolean corsaCompletata;
    // variabile osservazione per fermare il thread in modo safe
    private boolean corri;
    Random generatore;

    public Es2CorridoreThreadV2(int distanzaTotale, String nomeCorridore) {
        this.distanzaTotale = distanzaTotale;
        this.distanzaPercorsa = 0;
        this.corri = true;
        this.corsaCompletata = false;
        this.setName(nomeCorridore);
        this.generatore = new Random();
    }

    public void stopCorridore() {
        this.corri = false;
    }


    @Override
    public void run() {
        while (corri && distanzaPercorsa < distanzaTotale) {

            //iniziamo le stampe con this.getName così capite quali sono le stampe del thread main e quali del thread
            System.out.println(this.getName() + ": sto correndo...");
            // Simuliamo la corsa aumentando la distanza percorsa di 10 metri ogni ciclo
            //distanzaPercorsa += 10;
            //produce un numero casuale compreso tra uno (compreso) e 20 (escluso).
            distanzaPercorsa +=  generatore.nextInt(1,20);
            try {
                System.out.println(this.getName() + ": ho corso " + distanzaPercorsa);
                System.out.println(this.getName() + ": sto riposando...");
               Thread.sleep( 1000); // Simuliamo il tempo di corsa (1 secondo)
            } catch (InterruptedException e) {
                // Se il thread è interrotto, esce dal ciclo
                System.out.println(this.getName() + ": sono stato interrotto.");
            }
        }
        // controllo distanza percorsa
        if (distanzaPercorsa >= distanzaTotale) {
            corsaCompletata = true;
        }

        //rindondante ma serve a voi per capire bene la concorrenza
        System.out.println(this.getName() + ": ho corso " + distanzaPercorsa);
        System.out.println(this.getName() + ": il main mi ha fermato " + !corri);
        System.out.println(this.getName() + ": ho completato la corsa  " + corsaCompletata);
        System.out.println(this.getName() + ": ho finito di eseguire il metodo run");

    }

    public int getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public int getDistanzaTotale() {
        return distanzaTotale;
    }

    public boolean isCorsaCompletata() {
        return corsaCompletata;
    }
}
