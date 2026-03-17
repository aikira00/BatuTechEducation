package com.marcoschiavello;

import com.marcoschiavello.Interfaces.Bank;

/**
 * Classe che descrive un utente della banca
 *
 * @author Marco Schiavello
 */
public class BankUser {
    /**
     * Contatore statico che indica l'utente della banca
     */
    public static int bankUserCounter = 0;

    /**
     * Dado usato per decidere se depositare o prelevare
     */
    private Dice depositDice;

    /**
     *  Thread che eseguirà le azioni di deposito e prelievo
     */
    private Thread bankUserThread;

    /**
     * Banca su cui verrano fatte le azioni di deposito e di prelievo
     */
    private Bank bank;

    /**
     * Costruttore completo che crea uno user per una determinata banca
     *
     * @param bank banca di cui si vuola fare lo user
     * @param depositDice dado che verra usato per decidere se prelevare o depositare
     * @param verbose
     */
    public BankUser(Bank bank, Dice depositDice, boolean verbose) {
        this.depositDice = depositDice;
        this.bank = bank;

        this.bankUserThread = new Thread(() -> {
            double balanceAfterAction = 1;
            while(balanceAfterAction > 0) {
                int amount = (int) Math.floor(500 + (Math.random() * 250));
                boolean isDeposit = depositDice.roll();

                balanceAfterAction = isDeposit ?  bank.deposita(amount) : bank.preleva(amount);
                if(verbose)
                    System.out.println(Thread.currentThread().getName() + " ha " + (isDeposit ? "depositato" : "prelevato")  + " " + amount + " euro, ne rimangono " + balanceAfterAction + " sulla banca");
            }
            if(verbose)
                System.out.println(Thread.currentThread().getName() + " ha finito");
        }, "BankUserThread-" + BankUser.bankUserCounter);

        BankUser.bankUserCounter ++;
    }

    /**
     * Construttore accorciato che sottointende che verbose è a true
     *
     * @param bank banca di cui si vuola fare lo user
     * @param depositDice dado che verra usato per decidere se prelevare o depositare
     */
    public BankUser(Bank bank, Dice depositDice) { this(bank, depositDice, true); }

    public Dice getDepositDice() { return this.depositDice; }
    public Bank getBank() { return this.bank; }

    /**
     * matodo wrapper di {@link Thread#start()}
     */
    public void start() { this.bankUserThread.start(); }


    /**
     * matodo wrapper di {@link Thread#isAlive()}
     */
    public boolean isAlive() { return this.bankUserThread.isAlive(); }

    /**
     * matodo wrapper di {@link Thread#getName()}
     */
    public String getThreadName() { return this.bankUserThread.getName(); }

    /**
     * matodo wrapper di {@link Thread#join()}
     */
    public void join() throws InterruptedException { this.bankUserThread.join(); }

}
