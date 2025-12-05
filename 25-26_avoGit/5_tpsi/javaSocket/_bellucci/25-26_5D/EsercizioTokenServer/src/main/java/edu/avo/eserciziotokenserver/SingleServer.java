/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziotokenserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author MULTI01
 */
public class SingleServer implements Runnable {

    private final Socket socket;
    private final Map<String, String> dbUsers;

    public SingleServer(Socket socket, Map<String, String> dbUsers) {
        this.socket = socket;
        this.dbUsers = dbUsers;
    }

    @Override
    public void run() {
        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DbManager dbManager = new DbManager(dbUsers);
            SenderProtocolManager sender = new SenderProtocolManager(out);
            Application application = new Application(sender, dbManager);
            ReceiverProtocolManager receiver = new ReceiverProtocolManager(application);
            String message;
            sender.sendLoginNow();
            while ((message = in.readLine()) != null && !"QUIT".equals(message.trim())) {
                receiver.consumeMessage(message.trim());
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
