package edu.avo.getSet;

public class CaneTestDriverGetSet {
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
