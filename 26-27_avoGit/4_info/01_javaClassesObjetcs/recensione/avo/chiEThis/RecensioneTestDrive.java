package avo.chiEThis;

public class RecensioneTestDrive {
    public static void main(String[] args) {
        Recensione r2 = new Recensione();
        r2.setLocale("Kebab Express");
        r2.setAutore("samir.k");
        r2.setStelle(4);

        Recensione r4 = new Recensione();
        r4.setLocale("Kebab Express");
        r4.setAutore("giulia.r");
        r4.setStelle(2);

        r2.pubblica();
        r4.pubblica();

        // stesso metodo, stesso codice: cambia solo chi è this
        System.out.println("r2 più generosa di r4? " + r2.piuGenerosaDi(r4));   // this = r2
        System.out.println("r4 più generosa di r2? " + r4.piuGenerosaDi(r2));   // this = r4
    }
}
