/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercizio4thread;

import java.util.Random;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Consumatrice extends Thread{
    private Random rnd = new Random();
    private IObserver observer;
    private Prodotto prodotto;

    public Consumatrice(IObserver observer,Prodotto prodotto) {
        rnd = new Random();
        this.prodotto = prodotto;
        this.observer=observer;
    }

    @Override
    public void run() {
        while (true) {           
            int numero = prodotto.getNumero();            
            observer.mostra("Consumatrice: ", numero);
            try {
                Thread.sleep(rnd.nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}
