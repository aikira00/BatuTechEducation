/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.clientverificaa;

import java.io.PrintWriter;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {
    private PrintWriter out;

    public SenderProtocolManager(PrintWriter out) {
        this.out = out;
    }
    
    public void sendStart(){
        out.println("START");
    }    
    
    public void sendAttempt(int value){
        out.println("ATTEMPT#"+value);
    }
    
    public void sendQuit(){
        out.println("QUIT");
    }

    void sendUnknownCommand() {
        out.println("ADFASFDS");
    }

    void sendParametersError() {
        out.println("ATTEMPT");
    }
}
