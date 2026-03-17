package com.marcoschiavello;

import com.marcoschiavello.Interfaces.Bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int INIT_BANK_BALANCE = 1000;

    public static void main(String[] args) throws InterruptedException{
        List<BankUser> bankUsers = new ArrayList<>(); // array di user della banca
        int numOfUser = readNum("Quanti utenti dalla banca vuoi"); // chiedo in input il numero di bank user che agiranno parallelamente sulla banca
        Bank bank = ClassicBank.getInstance().init(INIT_BANK_BALANCE, true); // creao la banca con il metodo statico come descritto dal pattern singleton e poi chiamo un metodo init per dargli valori di base

        // creo un nuovi user della banca e li aggiungo a una lista in segito li faccio partire
        for(int i = 0; i < numOfUser; i++) {
            bankUsers.add(new BankUser(bank, new Dice(0.5)));
            bankUsers.get(i).start();
        }

        // aspetto che tutti gli user mandino in negativo il saldo della banca
        for(BankUser bankuser : bankUsers)
            bankuser.join();

        System.out.println("La banca e riamsta con un saldo di " + bank.getBalance() + " euro"); // stampo il saldo finale
    }

    /**
     * Metodo statico per leggere un intero positivo
     * @param question domanda da porre per avere un valore dall'utente
     * @return valore immesso dal utente
     */
    public static int readNum(String question) {
        try {
            int num;
            System.out.print(question + ": ");

            while ((num = Integer.valueOf(Main.reader.readLine())) < 1) {
                System.out.print(question + ": ");
            }

            return num;
        } catch (IOException e) {
            e.printStackTrace();

            return -1;
        }
    }
}
