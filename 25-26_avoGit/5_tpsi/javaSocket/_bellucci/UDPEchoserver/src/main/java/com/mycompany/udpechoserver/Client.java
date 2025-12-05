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
import javax.swing.JOptionPane;

/**
 *
 * @author MULTI01
 */
public class Client implements IDataConsumer {

    MySender sender;

    public Client(MySender sender, InetAddress address, int portNumber) {
        this.sender = sender;
        String s =JOptionPane.showInputDialog("INSERIRE IL MESSAGGIO");
        try {
            sender.send(s, s.length(), address, portNumber);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void consumeData(byte[] data, int dataLenght, InetAddress address, int portNumber) {
        if (dataLenght != 0) {
            JOptionPane.showMessageDialog(null,new String(data));
            String s =JOptionPane.showInputDialog("INSERIRE IL MESSAGGIO");
        } else {
            try {
                byte[]b= {};
                sender.send(b,0,address,portNumber);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
