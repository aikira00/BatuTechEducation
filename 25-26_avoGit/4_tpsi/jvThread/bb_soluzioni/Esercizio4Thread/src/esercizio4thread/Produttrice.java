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
public class Produttrice extends Thread{
    private Random rnd;
    private IObserver observer;
    private Prodotto prodotto;

    public Produttrice(IObserver observer,Prodotto prodotto) {
        rnd = new Random();
        this.observer=observer;
        this.prodotto = prodotto;
    }

    @Override
    public void run() {
        while (true) {
            int numero=rnd.nextInt(100);
            prodotto.setNumero(numero);
            observer.mostra("Produttrice: ", numero);
            try {
                Thread.sleep(rnd.nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}
