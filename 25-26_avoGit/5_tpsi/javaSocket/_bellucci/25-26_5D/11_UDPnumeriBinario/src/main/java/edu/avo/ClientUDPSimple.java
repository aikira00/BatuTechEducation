package edu.avo;

import java.io.IOException;
import java.net.*;

public class ClientUDPSimple {

    /**
     * Restituisce il simbolo dell'operazione
     */
    private static char getSimboloOperazione(int operazione) {
        switch (operazione) {
            case 1: return '+';
            case 2: return '-';
            case 3: return '*';
            case 4: return '/';
            default: return '?';
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Avvio Clinet UDP Simple!");
        DatagramSocket socket = new DatagramSocket();

        //invio 3 numeri
        int op = 1;
        int opOne = 20;
        int opTwo = 232;

        // Preparazione richiesta (3 byte)
        byte[] requestData = new byte[3];
        requestData[0] = (byte) op ;
        requestData[1] = (byte) opOne;
        requestData[2] = (byte) opTwo;

        System.out.println("\nInvio richiesta al server: " +
                op + " " + opOne + " " + opTwo);
        // Invio richiesta al server
        DatagramPacket sendPacket = new DatagramPacket(
                requestData, requestData.length,  InetAddress.getByName("127.0.0.1"),60000);

        socket.send(sendPacket);

        //ricevo 5 bytes op opOne opTwo MSB LSB
        // Preparazione buffer per risposta
        byte[] receiveBuffer = new byte[5];
        DatagramPacket receivePacket = new DatagramPacket(
                receiveBuffer, receiveBuffer.length
        );

        socket.receive(receivePacket);

        byte[] responseData = receivePacket.getData();
        /*	2.	& 0xFF
	•	Applica un AND bit a bit con 0xFF (che in binario è 11111111).
	•	Questo converte il byte in un int senza segno:
	•	Se il byte è positivo (0..127), rimane uguale.
	•	Se il byte è negativo (-128..-1), viene trasformato nel corrispondente valore 128..255.
	il bitwise AND con 0xFF “rimuove” il segno, trattando il byte come valore unsigned.*/
        int opResp = responseData[0] & 0xFF;
        int op1Resp = responseData[1] & 0xFF;
        int op2Resp = responseData[2] & 0xFF;
        int mbr = responseData[3] & 0xFF;  // Most Significant Byte
        int lbr = responseData[4] & 0xFF;  // Least Significant Byte

        //combino quarto e quinto byte
       // int risultato =  mbr | lbr;
        int risultato =   (mbr << 8) | lbr;

        char simboloOp = getSimboloOperazione(opResp);

        System.out.println("Risultato ottenuto " + risultato + " con operazione " + op1Resp + simboloOp + op2Resp);

    }


}
