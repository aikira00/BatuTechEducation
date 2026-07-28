/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mains;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {

    private MySender sender;

    public SenderProtocolManager(MySender sender) {
        this.sender = sender;
    }

    public void send(int n1, int n2, int op, int result) {
        String command = "";
        switch (op) {
            case 1 -> {
                command = "ADD";
            }
            case 2 -> {
                command = "SUB";
            }
            case 3 -> {
                command = "MUL";
            }
            case 4 -> {
                command = "DIV";
            }
        }
        sender.send(command + " " + n1 + " " + n2 + " " + result);
    }
}
