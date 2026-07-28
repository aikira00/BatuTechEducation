/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.mainc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palma
 */
public class MyClient {

    private ReaderProtocolManager rpm;

    public MyClient(String address, int port) {
        try {
            Socket socket = new Socket(address, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            MySender sender = new MySender(out);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            MyReader reader = new MyReader(in);
            App app = new App(new SenderProtocolManager(sender));
            rpm = new ReaderProtocolManager(app, reader);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void start() {
        rpm.start();
    }
}
