/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.clientverificaa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author palma
 */
public class Client {

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            SenderProtocolManager sender=new SenderProtocolManager(out);
            Application application=new Application(sender);
            Controller controller=new Controller(application);
            IApplicationObserver observer=new View("Client", controller);
            controller.setApplicationObserver(observer);
            ReceiverProtocolManager receiver=new ReceiverProtocolManager(application);
            String line;
            while((line=in.readLine())!=null){
                receiver.consumeMessage(line);
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
