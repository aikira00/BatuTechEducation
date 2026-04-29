package JvTh08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Es2CorridoreMainV2 {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //uso un solo try per InterruptedException metodo sleep
        //e IOException metodo readLine che legge input
        try{
            System.out.print("Inserisci la distanza totale che devono percorrere i due corridori: ");
            int distanzaTotale = Integer.parseInt(reader.readLine());
            System.out.print("Inserisci il numero di secondi prima di interrompere i corridori: ");
            int secondi = Integer.parseInt(reader.readLine());

            Es2CorridoreThreadV2 primoCorridore = new Es2CorridoreThreadV2(distanzaTotale, "PRIMO");
            Es2CorridoreThreadV2 secondoCorridore = new Es2CorridoreThreadV2(distanzaTotale, "SECONDO");
            primoCorridore.start();
            secondoCorridore.start();

            Thread.sleep(secondi * 1000); // Convertiamo secondi in millisecondi

            System.out.println(Thread.currentThread().getName() + " sono stufo di aspettare, interrompo i corridori. ");
            //corridore.interrupt();
            primoCorridore.stopCorridore();
            secondoCorridore.stopCorridore();

            //buona convenzine programmazione con i thread. Quando decido di interrompere un altro thread aspetto
            //termini correttamente
            primoCorridore.join();
            secondoCorridore.join();

            boolean corsaCompletataPrimoCorridore = primoCorridore.isCorsaCompletata();
            boolean corsaCompletataSecondoCorridore = secondoCorridore.isCorsaCompletata();
            if(corsaCompletataPrimoCorridore){
                System.out.println("Corridore " + primoCorridore.getName() + " ha completato la corsa");
            }
            else{
                System.out.println("Corridore " + primoCorridore.getName() + " NON ha completato la corsa");
            }
            System.out.println("Corridore " + primoCorridore.getName() + " ha percorso " + primoCorridore.getDistanzaPercorsa());

            if(corsaCompletataSecondoCorridore){
                System.out.println("Corridore " + secondoCorridore.getName() + " ha completato la corsa");
            }
            else{
                System.out.println("Corridore " + secondoCorridore.getName() + " NON ha completato la corsa");
            }
            System.out.println("Corridore " + secondoCorridore.getName() + " ha percorso " + secondoCorridore.getDistanzaPercorsa());

        }
        catch (InterruptedException e) {
            System.out.println("Errore interrupted" + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Errore I/O" + e.getMessage());
        }

        System.out.println(Thread.currentThread().getName() + " Fine.");
    }
}

