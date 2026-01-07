package edu.avo;

public class esercizioQuattroByte {
    public static void main(String[] args) {
        byte[] bytes = {0x12, 0x34, 0x56, 0x78};

        // Metodo manuale
        int numero = ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                (bytes[3] & 0xFF);

        System.out.println("Decimale: " + numero);
        System.out.println("Esadecimale: 0x" + Integer.toHexString(numero).toUpperCase());

        // Verifica con ByteBuffer
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.wrap(bytes);
        int numeroBuffer = buffer.getInt();

        System.out.println("Con ByteBuffer: " + numeroBuffer);
        System.out.println("Sono uguali? " + (numero == numeroBuffer));
    }
}

