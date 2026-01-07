package edu.avo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class spiegazioneByte {
    public static void main(String[] args) {
        byte b = 10;
        int valore_b = b;
        System.out.println(valore_b);

        b = -10;
        valore_b = b;
        System.out.println(valore_b);

        //vediamo problema del segno e della propagazione
        //serve cast esplicito perché andiamo fuori dal range
        b = (byte) 124;
        valore_b = b;
        System.out.println(valore_b);

        //& 0xFF serve per convertire
        // il byte (con segno) in un valore senza segno.
        valore_b = b & 0xFF;
        System.out.println(valore_b);

    }
}