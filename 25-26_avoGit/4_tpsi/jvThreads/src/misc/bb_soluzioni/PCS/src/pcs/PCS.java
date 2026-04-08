/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pcs;

/**
 *
 * @author Sistinformatici PC 4
 */
public class PCS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IObserver observer=new Observer();
        Prodotto prodotto=new Prodotto();
        Consumatore consumatore=new Consumatore(observer, prodotto);
        Produttore produttore=new Produttore(observer, prodotto);
        produttore.start();
        consumatore.start();
    }
    
}
