package edu.avo;

public class spiegazioneDueByte {
    public static void main(String[] args) {
        System.out.println("=== ESERCIZIO 1: Due byte come numero intero ===\n");

        // Creiamo due byte
        byte byte1 = 1;  // 0X12
        byte byte2 = 24;  // 0x34

        System.out.println("Byte 1: " + byte1 + " decimale)");
        System.out.println("Byte 2: " + byte2 + " decimale)");
        System.out.println();

        //e se voglio rappresentare numeri non da 0 a 255 ma

        // Metodo 1: Big-endian (byte più significativo prima)
        // byte 1 00000001 diventa 00000001 00000000
        // byte 2 00011000 diventa 00000000 00011000
        int byteMsb = (byte1 & 0xFF) << 8;
        int byteLsb = byte2 & 0xFF;
        System.out.println("msb: " + byteMsb);
        System.out.println("lsb: " + byteLsb);
        // operatore | combina i due byte byte
        // 00000001 0000000
        int bigEndian = ((byte1 & 0xFF) << 8) | (byte2 & 0xFF);
        System.out.println("Big-endian (0x1234): " + bigEndian);


        /*
        // Metodo 2: Little-endian (byte meno significativo prima)
        int littleEndian = ((byte2 & 0xFF) << 8) | (byte1 & 0xFF);
        System.out.println("Little-endian (0x3412): " + littleEndian);
      */
    }
}
