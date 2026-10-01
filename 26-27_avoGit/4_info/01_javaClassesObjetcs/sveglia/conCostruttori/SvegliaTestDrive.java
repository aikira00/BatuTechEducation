package conCostruttori;

public class SvegliaTestDrive {
    public static void main(String[] args) {
        Sveglia scuola = new Sveglia(7, 30, "scuola");
        Sveglia pisolino = new Sveglia(15, 0);
        Sveglia sbagliata = new Sveglia(25, 75);
        // Sveglia vuota = new Sveglia();   // non compila più: il costruttore di default è sparito

        scuola.suona();
        scuola.mostra();
        pisolino.mostra();
        sbagliata.mostra();

        Sveglia notte = new Sveglia(23, 50, "medicina");
        notte.suona();
        notte.mostra();

        // Il setter rifiuta e lascia l'orario com'era; il costruttore, con 25:75, aveva messo le 7:00.
        scuola.setOra(25);
        scuola.setMinuti(75);
        scuola.mostra();
    }
}
