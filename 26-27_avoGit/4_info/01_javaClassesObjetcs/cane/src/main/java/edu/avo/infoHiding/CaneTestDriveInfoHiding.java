package edu.avo.infoHiding;

// Stesso identico main del passo 3 (cambia solo il package): stesso output.
// La classe Cane dentro è cambiata, ma da qui non si vede.
public class CaneTestDriveInfoHiding {
    public static void main(String[] args) {
        Cane cane = new Cane();
        // cane.nome = "Fufi";   // non compila: nome è private
        cane.setNome("Fufi");
        cane.setRazza("Bassotto");
        cane.setTaglia("piccola");
        cane.setEta(4);
        cane.presentati();
        System.out.println("Si chiama " + cane.getNome());

        // Le stesse assurdità del passo 1: adesso la classe si difende da sola.
        cane.setEta(-7);
        cane.setTaglia("gigantissima");
        cane.setNome("");
        cane.presentati();   // lo stato è rimasto quello valido
    }
}
