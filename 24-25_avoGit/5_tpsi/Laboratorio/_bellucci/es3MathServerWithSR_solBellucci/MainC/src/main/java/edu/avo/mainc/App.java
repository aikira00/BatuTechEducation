/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mainc;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author palma
 */
public class App {

    SenderProtocolManager sender;
    private final Map<String, Character> opMap;

    public App(SenderProtocolManager sender) {
        this.sender = sender;
        opMap = new HashMap<>();
        opMap.put("ADD", '+');
        opMap.put("SUB", '-');
        opMap.put("MUL", '*');
        opMap.put("DIV", '/');
    }

    public boolean read() {
        boolean done = false;
        String s = JOptionPane.showInputDialog("Insert first operand (quit for exit)");
        done = s == null || s.equals("quit");
        if (!done) {
            int n1 = Integer.parseInt(s);
            char op = JOptionPane.showInputDialog("Insert operation (+ - * /)").charAt(0);
            s = JOptionPane.showInputDialog("Inserire il second operand");
            int n2 = Integer.parseInt(s);
            sender.send(n1, n2, op);
        }
        return done;
    }

    public void printResult(int n1, int n2, String op, int result) {
        String message = n1 + " " + opMap.get(op) + " " + n2 + " = " + result;
        JOptionPane.showMessageDialog(null, message, "Server answer", JOptionPane.INFORMATION_MESSAGE);
    }

}
