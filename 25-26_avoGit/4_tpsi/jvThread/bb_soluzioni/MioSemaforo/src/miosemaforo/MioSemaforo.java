/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miosemaforo;

/**
 *
 * @author Sistinformatici PC 4
 */
public class MioSemaforo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Condivisa condivisa = new Condivisa();
        Produttore p = new Produttore(condivisa);
        Consumatore c = new Consumatore(condivisa);
        p.start();
        c.start();
    }
    
}
