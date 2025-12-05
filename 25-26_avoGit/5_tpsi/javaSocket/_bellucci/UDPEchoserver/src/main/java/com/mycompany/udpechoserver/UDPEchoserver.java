/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.udpechoserver;

import edu.avo.udpiolibrary.MyReader;
import edu.avo.udpiolibrary.MySender;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author MULTI01
 */
public class UDPEchoserver {

    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket (60000);
        MySender sender = new MySender(socket);
        Server server = new Server (sender);
        MyReader reader = new MyReader(socket, 100, server);
        reader.start();
        
    }
}
