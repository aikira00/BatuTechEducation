/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MULTI01
 */
public class SingleServer extends Thread {

    PrintWriter out;
    BufferedReader in;
    Socket s;
    private final int min;
    private final int max;
    private List<Integer> list;

    public SingleServer(Socket s, int min, int max) throws IOException {
        this.s = s;
        out = new PrintWriter(s.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.min = min;
        this.max = max;
        list = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            out.println(min+" "+max);
            String s = in.readLine();
            String[] valori = s.split(",");
            for (String valore : valori) {
                list.add(Integer.valueOf(valore));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        out.close();
        try {
            in.close();
            s.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Integer> getList() {
        return list;
    }

}
