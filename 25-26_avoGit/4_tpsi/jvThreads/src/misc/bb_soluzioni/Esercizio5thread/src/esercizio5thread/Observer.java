/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio5thread;

import java.text.DecimalFormat;

/**
 *
 * @author palma
 */
public class Observer implements IObserver{

    private DecimalFormat df;
    
    public Observer(){
        df=new DecimalFormat("#.#");
    }
    @Override
    public void mostra(double livello) {
        System.out.println("Livello deposito: "+df.format(livello));
    }
    
}
