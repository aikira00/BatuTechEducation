/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.client;

import edu.avo.eserciziopreparatoriogui.IAppObserver;
import edu.avo.eserciziopreparatoriogui.IEventObserver;

/**
 *
 * @author palma
 */
public class App implements ICommandConsumer, IEventObserver{

    private SenderProtocolManager spm;
    private IAppObserver observer;
    
    public App(SenderProtocolManager spm, IAppObserver observer) {
        this.spm = spm;
        this.observer=observer;
        observer.setObserver(this);
    }

    @Override
    public void performEsplicita(double m, double q) {
        observer.updateEsplicita(m, q);
    }

    @Override
    public void performEsplicita(double x) {
        observer.updateEsplicita(x);
    }

    @Override
    public void performParallela(double a, double b, double c) {
        observer.updateParallela(a, b, c);
    }

    @Override
    public void performOrtogonale(double a, double b, double c) {
        observer.updateOrtogonale(a, b, c);
    }

    @Override
    public void performErrore(String type) {
        observer.updateError(type);
    }

    @Override
    public void performFine() {
       observer.closeWindow();
    }    

    @Override
    public void sendFine() {
        spm.sendClose();
    }

    @Override
    public void sendEsplicita(double a, double b, double c) {
        spm.sendEsplicita(a, b, c);
    }

    @Override
    public void sendOrtogonale(double a, double b, double c,double x, double y) {
        spm.sendOrtogonale(a, b, c, x,y);
    }

    @Override
    public void sendParallela(double a, double b, double c,double x, double y) {
        spm.sendParallela(a, b, c, x, y);
    }
}
