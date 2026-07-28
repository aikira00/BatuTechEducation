/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mainc;

import java.io.PrintWriter;

/**
 *
 * @author palma
 */
public class MySender {

    private PrintWriter out;

    public MySender(PrintWriter out) {
        this.out = out;
    }

    public void send(String message) {
        out.println(message);
        out.flush();
    }

    public void close() {
        out.close();
    }

}
