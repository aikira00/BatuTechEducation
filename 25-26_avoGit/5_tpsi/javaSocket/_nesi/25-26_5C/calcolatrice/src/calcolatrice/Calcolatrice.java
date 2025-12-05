/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calcolatrice;

/**
 *
 * @author adamr
 */
public class Calcolatrice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread client = new Thread(new CalcClient());
        client.start();
    }
    
}
