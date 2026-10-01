package conToStringBis;

// Il main della "Verifica" di "Siete il compilatore, bis": l'output deve essere quello atteso.
public class SvegliaTestDrive {
    public static void main(String[] args) {
        Sveglia scuola = new Sveglia(7, 30, "scuola");
        Sveglia pisolino = new Sveglia(15, 0);
        System.out.println(scuola);
        System.out.println(pisolino);

        scuola.posticipa();
        System.out.println(scuola);
        scuola.posticipa(20);
        System.out.println(scuola);
        scuola.posticipa(0);

        Sveglia medicina = new Sveglia(23, 50, "medicina");
        medicina.posticipa(15);       // passa la mezzanotte
        System.out.println(medicina);
    }
}
