package JvTh08;

import java.util.Scanner;
public class Es2CorridoreMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la distanza totale che il corridore deve percorrere: ");
        int distanzaTotale = scanner.nextInt();

        System.out.print("Inserisci il numero di secondi prima di interrompere il corridore: ");
        int secondiPrimaInterruzione = scanner.nextInt();

        Es2CorridoreThread corridore = new Es2CorridoreThread(distanzaTotale);
        corridore.start();

        try {
            Thread.sleep(secondiPrimaInterruzione * 1000); // Convertiamo secondi in millisecondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "sono stufo di aspettare, interrompo il corridore. ");
        //corridore.interrupt();
        corridore.stopCorridore();

       try{
           corridore.join(); // per sinc stampe
       }
       catch(InterruptedException e){
           e.printStackTrace();
       }

        System.out.println(Thread.currentThread().getName() + " => Il corridore ha completato la corsa.");
    }
}

