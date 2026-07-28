/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.client;

import edu.avo.tcpiolibrary.MySender;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {
    private final MySender sender;

    public SenderProtocolManager(MySender sender,String toClose) {
        this.sender = sender;
    }
    
    public void sendEsplicita(double a, double b, double c){
        sender.send("Esplicita§"+a+"#"+b+"#"+c);
    }
    
    public void sendParallela(double a, double b, double c, double x, double y){
        sender.send("Parallela§"+a+"#"+b+"#"+c+"#"+x+"#"+y);
    }
    
    public void sendOrtogonale(double a, double b, double c, double x, double y){
        sender.send("Ortogonale§"+a+"#"+b+"#"+c+"#"+x+"#"+y);
    }
    
    public void sendClose(){
        sender.send("Fine");
        sender.close();
    }
}
