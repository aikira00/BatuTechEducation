/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mains;

/**
 *
 * @author palma
 */
public class App {

    SenderProtocolManager sender;

    public App(SenderProtocolManager sender) {
        this.sender = sender;
    }

    public void add(int op1, int op2) {
        sender.send(op1, op2, 1, op1 + op2);
    }

    public void sub(int op1, int op2) {
        sender.send(op1, op2, 2, op1 - op2);
    }

    public void mul(int op1, int op2) {
        sender.send(op1, op2, 3, op1 * op2);
    }

    public void div(int op1, int op2) {
        sender.send(op1, op2, 4, op1 / op2);
    }
}
