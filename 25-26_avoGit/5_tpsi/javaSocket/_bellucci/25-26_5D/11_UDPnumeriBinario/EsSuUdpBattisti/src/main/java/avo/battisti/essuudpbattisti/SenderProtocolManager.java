/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avo.battisti.essuudpbattisti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Enrico
 */
public class SenderProtocolManager {
    DatagramSocket s;
    byte [] buffer;
    public SenderProtocolManager(DatagramSocket s, byte[] buffer) {
        this.s = s;
        this.buffer = buffer;
    }
    public void sendConfirm(String num,DatagramPacket packet) throws IOException{
        buffer = num.getBytes();
        packet=new DatagramPacket(buffer, buffer.length, 
                packet.getAddress(), packet.getPort());
        s.send(packet);
    }
    public void sendFileRicostruito(DatagramPacket packet) throws IOException{
        buffer = "FILE RICOSTRUITO".getBytes();
        packet=new DatagramPacket(buffer, buffer.length, 
                packet.getAddress(), packet.getPort());
        s.send(packet);
    }
    public void sendUnknownCommand(DatagramPacket packet) throws IOException{
        buffer = "UNKNOWN COMMAND".getBytes();
        packet=new DatagramPacket(buffer, buffer.length, 
                packet.getAddress(), packet.getPort());
        s.send(packet);
    }
    public void sendParametersError(DatagramPacket packet) throws IOException{
        buffer = "PARAMETERS ERROR".getBytes();
        packet=new DatagramPacket(buffer, buffer.length, 
                packet.getAddress(), packet.getPort());
        s.send(packet);
    }
}
