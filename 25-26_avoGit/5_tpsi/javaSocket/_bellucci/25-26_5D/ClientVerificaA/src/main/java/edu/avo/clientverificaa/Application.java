/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.clientverificaa;

/**
 *
 * @author palma
 */
public class Application {
    
    private SenderProtocolManager sender;
    private IApplicationObserver observer;

    public Application(SenderProtocolManager sender) {
        this.sender = sender;
    }

    public void setObserver(IApplicationObserver observer) {
        this.observer = observer;
    }
    
    public void performLimits(int min, int max){
        observer.viewLimits(min, max);
    }
    
    public void performResult(String status){
        observer.viewResult(status);
    }
    
    public void performWon(){
        observer.viewWon();
    }

    void sendQuit() {
        sender.sendQuit();
    }

    void sendStart() {
        sender.sendStart();
    }

    void sendAttempt(int n) {
        sender.sendAttempt(n);
    }

    void performParametersError() {
       observer.viewParametersError();
    }

    void performUnknownCommand() {
        observer.viewUnknownCommand();
    }
    
    void sendUnknownCommand() {
        sender.sendUnknownCommand();
    }
    
    void sendParametersError() {
        sender.sendParametersError();
    }
    
}
