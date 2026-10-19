package conDate;

// Il main dell'esercitazione 12, da prevedere su carta prima di eseguirlo.
public class StudenteTestDrive {
    public static void main(String[] args) {
        Studente s1 = new Studente("Anna", "Rossi", 3, 5, 2009);
        s1.iscrivi(14, 9, 2023);
        Studente s2 = new Studente("Luca", "Bianchi", 31, 11, 2008);
        s2.iscrivi(14, 9, 2023);
        Studente s3 = new Studente("Sara", "Verdi", 20, 2, 2010);
        s3.iscrivi(1, 9, 2009);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        Data oggi = new Data(19, 10, 2026);
        System.out.println(s1.getNome() + " oggi ha " + s1.etaAl(oggi) + " anni");
        System.out.println(s1.getNome() + " all'iscrizione aveva " + s1.etaAllIscrizione() + " anni");
        System.out.println(s1.getNome() + " maggiorenne oggi? " + s1.isMaggiorenneAl(oggi));
        System.out.println(s1.getNome() + " maggiorenne il 02/05/27? " + s1.isMaggiorenneAl(new Data(2, 5, 2027)));
        System.out.println(s1.getNome() + " maggiorenne il 03/05/27? " + s1.isMaggiorenneAl(new Data(3, 5, 2027)));

        s1.getDataDiNascita().setAnno(1999);
        System.out.println(s1.getDataDiNascita());
        System.out.println(s1);
        System.out.println(s1.getNome() + " oggi ha " + s1.etaAl(oggi) + " anni");

        System.out.println(s2.getDataDiNascita().controlloData());
        System.out.println(s3.etaAllIscrizione());

        System.out.println(s1.getDataIscrizione());
        System.out.println(s2.getDataIscrizione());
        System.out.println(s1.getDataIscrizione().equals(s2.getDataIscrizione()));
    }
}
