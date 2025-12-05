package edu.avo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class UdpReceiver {
    public static void main(String[] args) {
        try {
            // --- SERVER UDP ---
            // Si mette in ascolto su una porta specifica (es. 5000).
            // Attenzione: con UDP NON esiste il concetto di connessione stabile (niente accept() come in TCP).
            // Il "server" crea semplicemente una socket su una porta e attende pacchetti (datagrammi).
            // I datagrammi sono indipendenti: non c'è flusso continuo né ordine garantito.
            DatagramSocket socket = new DatagramSocket(5000);
            byte[] buffer = new byte[4]; // 4 byte = int

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("In attesa di ricezione...");

            socket.receive(packet);
            ByteBuffer byteBuffer = ByteBuffer.wrap(packet.getData());
            int receivedNumber = byteBuffer.getInt();

            System.out.println("Ricevuto numero: " + receivedNumber);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}