package com.marcoschiavello;

import com.marcoschiavello.Interfaces.Bank;

/**
 * Implementazione di una banca che implementa l'interface {@link Bank}
 */
public class ClassicBank implements Bank {
    /**
     * Saldo della banca
     */
    private double balance;

    /**
     * Indica se stampare alcuni messagi durante l'esecuzione
     */
    private boolean verbose;

    /**
     * Indica se l'oggetto puo essere ancora inizializzato
     */
    private boolean init = true;

    /**
     * Istanza stastica che sara l'unica della classe come descritto dal design pattern Singleton
     */
    private static ClassicBank instance = null;

    /**
     * Costruttore privato che construira un oggetto con valori base
     */
    private ClassicBank() {
        this.balance = 0;
        this.verbose = true;
    }

    /**
     * Metodo che ritorna la instanza della classe se presenta altrimenti chiama il costruttore privato per crearlo, come descritto dal design pattern Singleton
     *
     * @return istanza statica della classe
     */
    public static ClassicBank getInstance() {
        if(ClassicBank.instance == null)
            ClassicBank.instance = new ClassicBank();

        return ClassicBank.instance;
    }

    /**
     * Metodo che puo essere chiamato solo una volta e inizializza con del valori gli attributi della classe
     *
     * @param amount saldo iniziale della banca
     * @param verbose Indica se stampare alcuni messagi durante l'esecuzione
     * @return istanza statica della classe
     */
    public ClassicBank init(double amount, boolean verbose) {
        if(init) {
            this.balance = amount;
            this.verbose = verbose;
            this.init = false;

            if(verbose)
                System.out.println("La banca ha un saldo iniziale di " + this.balance + " euro");
        }

        return ClassicBank.instance;
    }

    public void setVerbose(boolean verbose) { this.verbose = verbose; }

    @Override
    public synchronized double preleva(double balance) {
        return this.balance -= balance;
    }

    @Override
    public synchronized double deposita(double balance) {
        return this.balance += balance;
    }

    @Override
    public synchronized double getBalance() { return this.balance; }
}
