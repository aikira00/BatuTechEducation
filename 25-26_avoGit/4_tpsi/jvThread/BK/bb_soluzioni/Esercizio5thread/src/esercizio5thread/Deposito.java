/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio5thread;

/**
 *
 * @author palma
 */
public class Deposito {
    
    private double livelloAttuale;
    private int capacita;
    
    public Deposito(int capacita, double livelloAttuale){
        this.capacita=capacita;this.livelloAttuale=livelloAttuale;
    }
    
    public double carica(double quantita){
        livelloAttuale+=quantita;
        return livelloAttuale;
    }
    
    public double preleva(double quantita){
        livelloAttuale-=quantita;
        return livelloAttuale;
    }
}
