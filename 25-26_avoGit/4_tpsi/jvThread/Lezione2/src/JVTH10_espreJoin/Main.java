package JVTH10_espreJoin;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leggi 4 numeri interi da utente
        System.out.println("Inserisci il primo numero intero:");
        int num1 = scanner.nextInt();

        System.out.println("Inserisci il secondo numero intero:");
        int num2 = scanner.nextInt();

        System.out.println("Inserisci il terzo numero intero:");
        int num3 = scanner.nextInt();


        // Output dei numeri inseriti
        System.out.println("I numeri inseriti sono:");
        System.out.println("Numero 1: " + num1);
        System.out.println("Numero 2: " + num2);
        System.out.println("Numero 3: " + num3);
        // Chiudi lo scanner
        scanner.close();

        //avvio thread
        ThreadGiallo ITG = new ThreadGiallo(num1, num2, num3);
        ThreadVerde ITV = new ThreadVerde(num1, num2, num3);
        Thread tG = new Thread(ITG);
        Thread tV = new Thread(ITV);
        tG.start();
        tV.start();
        try{
            tG.join();
            tV.join();
        }
        catch(InterruptedException e){
            System.out.println("Eccezione avvenuta " + e.getMessage());
        }
        System.out.println("Finito i calcoli");
        double res = ITG.getResult() + ITV.getResult();
        System.out.println("Il risultato finale è: " + res);
    }
}
