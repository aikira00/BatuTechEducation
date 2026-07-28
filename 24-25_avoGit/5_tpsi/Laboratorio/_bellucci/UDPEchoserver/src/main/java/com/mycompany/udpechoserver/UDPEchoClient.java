/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.udpechoserver;

import edu.avo.udpiolibrary.MyReader;
import edu.avo.udpiolibrary.MySender;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author MULTI01
 */
public class UDPEchoClient {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket socket = new DatagramSocket ();
        MySender sender = new MySender(socket);
        Client client = new Client (sender,InetAddress.getByName("localhost"), 60000);
        MyReader reader = new MyReader(socket, 100, client);
        reader.start();
        
    }
}
