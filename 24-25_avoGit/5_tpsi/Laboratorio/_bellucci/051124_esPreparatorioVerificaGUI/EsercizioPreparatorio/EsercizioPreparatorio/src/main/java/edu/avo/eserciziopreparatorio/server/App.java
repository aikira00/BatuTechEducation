/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.server;

/**
 *
 * @author palma
 */
public class App implements ICommandConsumer{

    private SenderProtocolManager spm;

    public App(SenderProtocolManager spm) {
        this.spm = spm;
    }
    
    
    @Override
    public void performEsplicita(double a, double b, double c) {
        if(b!=0){
            spm.sendEsplicita(-a/b, -c/b);
        }else{
            spm.sendEsplicita(-c/a);
        }
    }

    @Override
    public void performOrtogonale(double a, double b, double c, double x, double y) {
        spm.sendOrtogonale(b, -a, a*y-b*x);
    }

    @Override
    public void performParallela(double a, double b, double c, double x, double y) {
        spm.sendParallela(a, b, -b*x-a*y);
    }

    @Override
    public void performFine() {
        spm.sendFine();
    }

    @Override
    public void performError(int code) {
        spm.sendError(code);
    }
    
}
