/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.server;

import edu.avo.tcpiolibrary.MySender;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {
    
    private final MySender sender;
    private final String toClose;

    public SenderProtocolManager(MySender sender, String toClose) {
        this.sender = sender;
        this.toClose=toClose;
    }
    
    public void sendEsplicita(double m, double q){
        sender.send("Esplicita§"+m+"#"+q);
    }
    
    public void sendEsplicita(double x){
        sender.send("Esplicita§"+x);
    }
    
    public void sendParallela(double a, double b, double c){
        sender.send("Parallela§"+a+"#"+b+"#"+c);
    }
    
    public void sendOrtogonale(double a, double b, double c){
        sender.send("Ortogonale§"+a+"#"+b+"#"+c);
    }
    
    public void sendFine(){
        sender.send(toClose);
        sender.close();
    }
    
    public void sendError(int code){
        sender.send("Errore§"+code);
    }
}
