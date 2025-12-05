package edu.avo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderProtocolManager {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public SenderProtocolManager(DatagramSocket socket, InetAddress address, int port){
        this.socket=socket;
        this.address=address;
        this.port=port;
    }

    public void send(byte[] bytes,  InetAddress address, int port){
        try{
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            socket.send(packet);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
