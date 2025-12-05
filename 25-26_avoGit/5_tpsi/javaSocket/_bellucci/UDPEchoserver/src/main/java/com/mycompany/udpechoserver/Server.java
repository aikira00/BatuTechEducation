/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.udpechoserver;

import edu.avo.udpiolibrary.IDataConsumer;
import edu.avo.udpiolibrary.MySender;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MULTI01
 */
public class Server implements IDataConsumer {

    MySender sender;

    public Server(MySender sender) {
        this.sender = sender;
    }

    @Override
    public void consumeData(byte[] data, int dataLenght, InetAddress address, int portNumber) {
        try {
            String s = new String(data).trim();
            if (s.equals("quit")) {
                byte[]b = {};
                sender.send(b, 0, address, portNumber);
            } else {
                sender.send(data, dataLenght, address, portNumber);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
