/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mainc;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {

    MySender sender;
    Map<Character, String> opMap;

    public SenderProtocolManager(MySender sender) {
        this.sender = sender;
        opMap = new HashMap<>();
        opMap.put('+', "ADD");
        opMap.put('-', "SUB");
        opMap.put('*', "MUL");
        opMap.put('/', "DIV");
    }

    public void send(int n1, int n2, char op) {
        sender.send(opMap.get(op) + " " + n1 + " " + n2);
    }
}
