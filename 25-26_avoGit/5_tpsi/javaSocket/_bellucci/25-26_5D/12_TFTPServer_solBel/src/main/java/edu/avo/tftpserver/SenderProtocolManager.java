/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.tftpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author palma
 */
public class SenderProtocolManager {

    private Map<Integer, String> errors;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public SenderProtocolManager(DatagramSocket socket) {
        this.socket = socket;
        errors = new HashMap<>();
        errors.put(0, "Internal server error");
        errors.put(1, "File not found\0");
        errors.put(2, "Access violation\0");
        errors.put(3, "Disk full or allocation exceeded\0");
        errors.put(4, "Illegal TFTP operation\0");
        errors.put(5, "Unknown transfer ID\0");
        errors.put(6, "File already exists\0");
    }

    public void sendError(int errorCode, InetAddress address, int port) throws IOException {
        byte[] errorMessage = errors.get(errorCode).getBytes();
        byte[] message = new byte[4 + errorMessage.length];
        message[0] = 0;
        message[1] = 5;
        message[2] = 0;
        message[3] = (byte) errorCode;
        System.arraycopy(errorMessage, 0, message, 4, errorMessage.length);
        packet = new DatagramPacket(message, message.length, address, port);
        socket.send(packet);
        
    }

    public void sendData(int blockNumber, byte[] data, InetAddress address, int port) throws IOException {

        byte[] message = new byte[4 + data.length];
        message[0] = 0;
        message[1] = 3;
        message[2] = (byte) ((blockNumber >> 8) & 0xff);
        message[3] = (byte) (blockNumber & 0xff);
       // System.arraycopy(src, srcPos, dest, destPos, length);
        System.arraycopy(data, 0, message, 4, data.length);
        packet = new DatagramPacket(message, message.length, address, port);
        socket.send(packet);
    }

    public void sendAck(int blockNumber, InetAddress address, int port) throws IOException {
        byte[] message = new byte[4];
        message[0] = 0;
        message[1] = 4;
        message[2] = (byte) ((blockNumber >> 8) & 0xff);
        message[3] = (byte) (blockNumber & 0xff);
        packet = new DatagramPacket(message, message.length, address, port);
        socket.send(packet);
    }
}
