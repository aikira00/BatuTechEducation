package edu.avo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDPSImple {
    private static int computeOperation(int op, int opOne, int opTwo) {
        // Calcolo del risultato
        int res = 0;
        String opStr = "";
        boolean errore = false;
        char symbol = ' ';
        switch (op) {
            case 1:  // Addizione
                res = opOne + opTwo;
                opStr = "somma";
                symbol = '+';
                break;
            case 2:  // Sottrazione
                if (opOne >= opTwo) {
                    res = opOne - opTwo;
                } else {
                    res = 0;  // Gestione valori assoluti
                }
                opStr = "sottrazione";
                symbol = '-';
                break;
            case 3:  // Moltiplicazione
                res = opOne * opTwo;
                opStr = "moltiplicazione";
                symbol = '*';
                break;
            case 4:  // Divisione intera
                if (opTwo != 0) {
                    res = opOne / opTwo;
                } else {
                    System.out.println("Errore: divisione per zero");
                    res = 0;
                    errore = true;
                }
                opStr = "divisione";
                symbol = '/';
                break;
            default:
                System.out.println("Operazione non valida");
        }

        if (!errore) {
            System.out.println("Risultato: " + opStr + ": " + opOne + " " + symbol +
                    " " + opTwo + " = " + res);
        }
        return res;
    }
    public static void main(String[] args) throws SocketException, IOException {
        int port = 60000;
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Server UDP avviato sulla porta " + port);
        System.out.println("In attesa di richieste...\n");

        //mi preparo a ricevere 3 bytes
        byte[] buffer = new byte[3];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        byte[] receivedData = receivePacket.getData();
        int op = receivedData[0]  & 0xFF;
        int opOne = receivedData[1]  & 0xFF;
        int opTwo = receivedData[2]  & 0xFF;

        int risultato = computeOperation(op, opOne, opTwo);

        System.out.println("Ricevuto richiesta: " + op + " " + opOne + " " + opTwo);

        // Preparazione risposta (5 byte)
        byte[] responseData = new byte[5];

        responseData[0] = (byte) op;
        responseData[1] = (byte) opOne;
        responseData[2] = (byte) opTwo;
        // Byte alto (8 bit più significativi)
        // sposta gli 8 bit più significativi verso destra.
        responseData[3] = (byte) ((risultato >> 8) & 0xFF);
        // Byte basso (8 bit meno significativi)// MBR
        responseData[4] = (byte) (risultato & 0xFF);

        System.out.println("Risposta: MBR=" + (responseData[3] & 0xFF) +
                ", LBR=" + (responseData[4] & 0xFF) + "\n");

        // Invio risposta al client
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        DatagramPacket sendPacket = new DatagramPacket(
                responseData, responseData.length, clientAddress, clientPort
        );
        socket.send(sendPacket);
    }
}
