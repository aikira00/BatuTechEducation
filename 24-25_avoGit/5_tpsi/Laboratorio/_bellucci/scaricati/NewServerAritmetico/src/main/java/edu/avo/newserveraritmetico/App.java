/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.newserveraritmetico;

/**
 *
 * @author MULTI01
 */
public class App implements ICommandConsumer{

    private SenderProtocolManager spm;

    public App(SenderProtocolManager spm) {
        this.spm = spm;
    }
       
    @Override
    public void add(int n1, int n2) {
        spm.sendAddResult( n1, n2, n1+n2);
    }

    @Override
    public void sub(int n1, int n2) {
        spm.sendSubResult(n1, n2, n1-n2);
    }

    @Override
    public void mul(int n1, int n2) {
        spm.sendMulResult( n1, n2, n1*n2);
    }

    @Override
    public void div(int n1, int n2) {
        spm.sendDivResult( n1, n2, n1/n2);
    }

    @Override
    public void close() {
        spm.close();
    }
    
}
