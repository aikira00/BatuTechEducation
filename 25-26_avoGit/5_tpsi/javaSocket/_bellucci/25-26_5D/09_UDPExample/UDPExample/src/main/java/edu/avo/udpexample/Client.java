/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.udpexample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 *
 * @author MULTI01
 */
public class Client {
    
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket socket=new DatagramSocket();
        byte [] buffer="Ciao sono un client".getBytes();
        DatagramPacket packet=new DatagramPacket(buffer,buffer.length,
        InetAddress.getByName("127.0.0.1"),60000);
        socket.send(packet);
        buffer=new byte[1024];
        packet=new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String message=new String(Arrays.copyOfRange(packet.getData(),0,packet.getLength()));
        System.out.println(message);
        
                
    }
}
