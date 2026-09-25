package edu.avo.conCostruttori;

public class SvegliaTestDrive {
    public static void main(String[] args) {
        Sveglia scuola = new Sveglia(7, 30, "scuola");
        Sveglia pisolino = new Sveglia(15, 0);
        Sveglia sbagliata = new Sveglia(25, 75);
        // Sveglia vuota = new Sveglia();   // non compila più: il costruttore di default è sparito

        System.out.println(scuola);
        System.out.println(pisolino);
        System.out.println(sbagliata);

        scuola.setRipeti(true);
        scuola.suona();
        scuola.posticipa();
        System.out.println(scuola);
        scuola.posticipa(30);
        System.out.println(scuola);

        Sveglia notte = new Sveglia(23, 50, "medicina");
        notte.posticipa(15);          // passa la mezzanotte
        System.out.println(notte);
        notte.posticipa(-3);
    }
}
