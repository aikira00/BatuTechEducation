/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package esercizio4thread;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Esercizio4Thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Prodotto prodotto=new Prodotto();
       IObserver observer=new Observer();
       Produttrice produttrice=new Produttrice(observer, prodotto);
       Consumatrice consumatrice=new Consumatrice(observer, prodotto);
       produttrice.start();
       consumatrice.start();
    }
    
}
