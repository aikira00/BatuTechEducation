/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopersistenza;

import edu.avo.tcpiolibrary.MessageWriter;
import java.util.List;

/**
 *
 * @author MULTI01
 */
public class SenderProtocolManager {
    private MySender sender;
    private String forClose;

    public SenderProtocolManager(MySender sender, String forClose) {
        this.sender = sender;
        this.forClose = forClose;
    }
    
    public void sendLogin(){
        sender.send("login");
    }
    
    public void sendLoginOk(int token){
        sender.send("login ok:"+token);
    }
    
    public void sendLoginKo(){
        sender.send("login ko");
    }
    
    public void sendLoginNow(){
        sender.send("login now");
    }
    
    public void sendList(List<String> list){
        String message="list";
        for(String text: list){
            message+=":"+text;
        }
        sender.send(message);
    }
    
    public void sendTextSaved(){
        sender.send("text saved");
    }
    
    public void sendWrongToken(){
        sender.send("error:wrong token");
    }
    
    public void sendParametersError(){
        sender.send("error: parameters number error");
    }
    
    public void sendError(){
        sender.send("error: generic server error");
    }
    
    public void sendUnknownCommand(){
        sender.send("error:unknown command");
    }

    void sendClose() {
        sender.send(forClose);
        sender.close();
    }

    void sendLoginError() {
        sender.send("error:login already executed");
    }
}
