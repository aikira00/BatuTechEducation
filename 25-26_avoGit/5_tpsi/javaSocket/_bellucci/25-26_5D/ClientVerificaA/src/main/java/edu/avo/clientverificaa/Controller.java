/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.clientverificaa;

/**
 *
 * @author palma
 */
public class Controller {

    private Application application;

    public Controller(Application application) {
        this.application = application;
    }

    void sendQuit() {
        application.sendQuit();
    }

    void sendStart() {
        application.sendStart();
    }

    void sendAttempt(int n) {
        application.sendAttempt(n);
    }

    public void setApplicationObserver(IApplicationObserver observer) {
        application.setObserver(observer);
    }

    void sendParametersError() {
        application.sendParametersError();
    }

    void sendUnknownCommand() {
        application.sendUnknownCommand();
    }
}
