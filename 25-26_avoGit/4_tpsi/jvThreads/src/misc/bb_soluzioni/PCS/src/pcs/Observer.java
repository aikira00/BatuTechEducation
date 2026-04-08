/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcs;

/**
 *
 * @author Sistinformatici PC 4
 */
public class Observer implements IObserver{

    @Override
    public void mostra(String nome, int numero) {
        System.out.println(nome+": "+numero);
    }
    
}
