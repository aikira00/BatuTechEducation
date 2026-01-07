package edu.avo;

import java.nio.charset.StandardCharsets;

public class spiegazioneStringhe {

    public static void main(String[] args) {
        // 1. Da String a byte[]
        String messaggio = "Ciao";
        byte[] bytes = messaggio.getBytes();

        System.out.println("Stringa originale: " + messaggio);
        System.out.print("Array di byte: ");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println();

        // 2. Da byte[] a String
        String ricostruita = new String(bytes);
        System.out.println("Stringa ricostruita: " + ricostruita);

        System.out.println("\n--- Con encoding esplicito (UTF-8) ---");

        // 3. Con charset esplicito (UTF-8)
        String testo = "Città";
        byte[] bytesUTF8 = testo.getBytes(StandardCharsets.UTF_8);

        System.out.print("Byte UTF-8: ");
        for (byte b : bytesUTF8) {
            System.out.print(b + " ");
        }
        System.out.println();

        String testoDaBytes = new String(bytesUTF8, StandardCharsets.UTF_8);
        System.out.println("Testo ricostruito: " + testoDaBytes);
    }
}
