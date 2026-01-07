/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.tftpserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author palma
 */
public class ServerTFTP {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        DatagramSocket socket = new DatagramSocket(60000);
        DatagramPacket packet;
        byte[] buffer;
        int i=1;
        while (true) {
            buffer = new byte[516];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            ReceiverProtocolManager r = new ReceiverProtocolManager(packet, 512,i++);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
