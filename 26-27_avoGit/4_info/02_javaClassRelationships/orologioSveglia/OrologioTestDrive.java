package orologioSveglia;

// Dipendenza «crea»: il main fa new delle sveglie e dell'orologio, poi passa le sveglie all'orologio.
public class OrologioTestDrive {
    public static void main(String[] args) {
        Sveglia scuola = new Sveglia(7, 30, "scuola");
        Sveglia palestra = new Sveglia(6, 45, "palestra");
        Orologio telefono = new Orologio(7, 30);

        telefono.controlla(scuola);
        telefono.controlla(palestra);
    }
}
