package edu.avo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class UdpSender {
    public static void main(String[] args) {
        try {
            int number = 12345; // intero da inviare
            ByteBuffer buffer = ByteBuffer.allocate(4); // 4 byte = int
            buffer.putInt(number);

            // invia verso localhost:5000
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity(), address, 5000);

            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

            System.out.println("Inviato numero: " + number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}