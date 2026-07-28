/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.udpiolibrary;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author MULTI01
 */
public class MySender {
    private DatagramSocket socket;

    public MySender(DatagramSocket socket) {
        this.socket = socket;
    }
    
   public void send(byte[] data, int dataLenght,InetAddress address, int portNumber) throws IOException{
       DatagramPacket packet= new DatagramPacket(data, dataLenght,
               address, portNumber);
       socket.send(packet);
       
   }
   
      public void send(String data, int dataLenght,
              InetAddress address, int portNumber) throws IOException{
      send(data.getBytes(),dataLenght,
               address, portNumber);
   }
}
