/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.tftpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 *
 * @author palma
 */
public class ReceiverProtocolManager implements Runnable {

    private Application application;
    private int blockSize;
    private boolean error;
    private DatagramSocket comm;
    private DatagramPacket packet;
    private int serverNum;
    public ReceiverProtocolManager(DatagramPacket packet, int blockSize, int serverNum) {
        try {
            this.packet=packet;
            comm=new DatagramSocket();
            this.application = new Application(comm);
            this.blockSize = blockSize;
            error=false;
            this.serverNum=serverNum;
        } catch (SocketException ex) {
             System.err.println("Errore irrecuperabile. Chiusura del server N° "+serverNum);
        }
    }

    @Override
    public void run() {
        try {
            byte [] received = Arrays.copyOfRange(packet.getData(), 0, packet.getLength());
            consumeMessage(received, packet.getAddress(), packet.getPort());
            while ((application.getBlockLength() ==-1 || application.getBlockLength()==blockSize) && !error) {
                received=new byte[1024];
                packet = new DatagramPacket(received, received.length);
                comm.receive(packet);
                received = Arrays.copyOfRange(packet.getData(), 0, packet.getLength());
                consumeMessage(received, packet.getAddress(), packet.getPort());
            }
        } catch (IOException ex) {
            System.err.println("Errore irrecuperabile. Chiusura del server N° "+serverNum);
        }
    }

    private void consumeMessage(byte[] received, InetAddress address, int port) throws IOException{

        switch (received[1]) {
            case 1 -> {
                String filename = new String(Arrays.copyOfRange(received, 2, received.length));
                filename = filename.substring(0, filename.indexOf("octet") - 1);
                application.performFileReadRequest(filename, address, port);
            }
            case 2 -> {
                String filename = new String(Arrays.copyOfRange(received, 2, received.length));
                filename = filename.substring(0, filename.indexOf("octet") - 1);
                application.performFileWriteRequest(filename, address, port);
            }
            case 3 -> {
                int blockNumber = ((received[2]&0xff)<<8)|(received[3]&0xff);
                application.performReceiveData(blockNumber, Arrays.copyOfRange(received, 4, received.length), address, port);
            }
            case 4 -> {
                int blockNumber = ((received[2]&0xff)<<8)|(received[3]&0xff);
                application.performAck(blockNumber, address, port);
            }
            case 5 -> {
                error=true;
            }
        }
    }
}
