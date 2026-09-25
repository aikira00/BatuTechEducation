package avo.conThis;

public class RecensioneTestDrive {
    public static void main(String[] args) {
        Recensione r1 = new Recensione();
        r1.setLocale("Pizzeria Vesuvio");
        r1.setAutore("marta_07");
        r1.setStelle(0);

        Recensione r2 = new Recensione();
        r2.setLocale("Kebab Express");
        r2.setAutore("samir.k");
        r2.setStelle(4);

        Recensione r3 = new Recensione();
        r3.setLocale("Bar della Stazione");
        r3.setAutore("anonimo");
        r3.setStelle(11);

        r1.pubblica();
        r2.pubblica();
        r3.pubblica();
    }
}
