package edu.avo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderProtocolManager {
    private DatagramSocket socket;

    public SenderProtocolManager(DatagramSocket socket){
        this.socket=socket;
    }

    public void send(String message, String address, int port){
        try{
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, socket.getLocalAddress(), address);
            socket.send(packet);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
