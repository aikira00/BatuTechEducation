/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.udpiolibrary;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author MULTI01
 */
public class MyReader implements Runnable {

    private DatagramSocket socket;
    private byte[] buffer;
    private DatagramPacket packet;
    private IDataConsumer consumer;
    private final int bufferLength;

    public MyReader(DatagramSocket socket, int bufferLenght, IDataConsumer consumer) {
        this.socket = socket;
        this.buffer = new byte[bufferLenght];
        this.bufferLength = bufferLenght;
        this.consumer = consumer;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        do {
            try {
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                consumer.consumeData(packet.getData(), packet.getLength(),
                        packet.getAddress(), packet.getPort());
                buffer = new byte[bufferLength];
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } while (packet.getLength() != 0);

    }

}
