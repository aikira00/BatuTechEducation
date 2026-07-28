/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.newserveraritmetico;

import edu.avo.tcpiolibrary.MySender;

/**
 *
 * @author MULTI01
 */
public class SenderProtocolManager {
    private MySender sender;
    private String toClose;

    public SenderProtocolManager(MySender sender, String toClose) {
        this.sender = sender;
        this.toClose = toClose;
    }
    
    public void sendAddResult( int n1, int n2, int result){
        sender.send("ADD "+n1+" "+n2+" "+result);
    }
    
    public void sendSubResult( int n1, int n2, int result){
        sender.send("SUB "+n1+" "+n2+" "+result);
    }
    
    public void sendMulResult( int n1, int n2, int result){
        sender.send("MUL "+n1+" "+n2+" "+result);
    }
    
    public void sendDivResult( int n1, int n2, int result){
        sender.send("DIV "+n1+" "+n2+" "+result);
    }
    
    public void close(){
        sender.send(toClose);
        sender.close();
    }
}
