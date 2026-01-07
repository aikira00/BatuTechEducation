/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avo.battisti.essuudpbattisti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 *
 * @author Enrico
 */
public class SingleServer implements Runnable {

    DatagramSocket socket;
    boolean stop;
    public SingleServer(DatagramSocket socket) {
        this.socket = socket;
        stop=false;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        SenderProtocolManager sPM = new SenderProtocolManager(socket, buffer);
        Application a = new Application(sPM);
        RecieverProtocolManager rPM = new RecieverProtocolManager(a,this);
        
        while (!stop) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                rPM.consumeMessage(packet);
            } catch (IOException ex) {
                System.getLogger(SingleServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}
