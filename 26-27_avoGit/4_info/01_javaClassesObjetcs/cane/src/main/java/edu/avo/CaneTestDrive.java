package edu.avo;

public class CaneTestDrive {
    public static void main(String[] args) {
        Cane cane = new Cane();
        cane.nome = "Fufi";
        cane.razza = "Bassotto";
        cane.taglia = "piccola";
        cane.eta = 4;
        cane.presentati();
        cane.abbaia();

        // Nessuno ci impedisce di scrivere assurdità: compila e gira.
        cane.eta = -7;
        cane.taglia = "gigantissima";
        cane.nome = "";
        cane.presentati();
    }
}
