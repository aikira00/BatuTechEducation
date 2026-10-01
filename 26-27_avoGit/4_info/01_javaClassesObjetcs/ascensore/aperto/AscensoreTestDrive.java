package edu.avo.aperto;

public class AscensoreTestDrive {
    public static void main(String[] args) {
        Ascensore a = new Ascensore();
        a.piano = 3;
        a.apriPorte();
        a.sali();

        // Il palazzo ha i piani da 0 a 5. L'ascensore non lo sa.
        a.piano = 0;
        a.scendi();
        a.scendi();
        a.piano = 42;
        a.apriPorte();
        System.out.println("capienza = " + a.capienza + " persone");
    }
}
