/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mains;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author palma
 */
public class MyReader {

    private BufferedReader in;

    public MyReader(BufferedReader in) {
        this.in = in;
    }

    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
