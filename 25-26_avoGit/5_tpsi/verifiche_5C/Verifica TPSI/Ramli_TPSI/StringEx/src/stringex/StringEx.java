/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stringex;

/**
 *
 * @author RAMLI ADAM FILA B
 */
public class StringEx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread StringClient = new Thread(new StringClient());
        StringClient.start();
    }
    
}
