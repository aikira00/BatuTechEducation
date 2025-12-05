/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziotokenserver;

import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {

    private final PrintWriter out;

    public SenderProtocolManager(PrintWriter out) {
        this.out = out;
    }

    public void sendUnknowCommand() {
        out.println("UNKNOW COMMAND");
    }

    public void sendLoginNow() {
        out.println("LOGIN NOW");
    }

    public void sendLoginOk(int token) {
        out.println("LOGIN OK#" + token);
    }

    public void sendMsgResponse(String text) {
        out.println("TEXT SAVED#" + text);
    }

    public void sendListResponse(List<String> list) {
        String message = "LIST#";
        for (String text : list) {
            message += text + "°";
        }
        message = message.substring(0, message.length() - 1);
        out.println(message);
    }

    public void sendParametersError() {
        out.println("PARAMETERS ERROR");
    }

    void sendLoginKo() {
        out.println("LOGIN KO");
    }

}
