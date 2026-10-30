package orologioConSveglie;

// Il main crea le sveglie e le dà all'orologio, che se le tiene.
public class OrologioTestDrive {
    public static void main(String[] args) {
        Sveglia scuola = new Sveglia(7, 30, "scuola");
        Sveglia palestra = new Sveglia(6, 45, "palestra");
        Sveglia medicina = new Sveglia(7, 30, "medicina");

        Orologio telefono = new Orologio(7, 30);
        telefono.aggiungi(scuola);
        telefono.aggiungi(palestra);
        telefono.aggiungi(medicina);
        System.out.println(telefono);
        telefono.controlla();

        // il main ha ancora il suo riferimento a scuola
        scuola.posticipa(10);
        System.out.println(telefono);
        telefono.controlla();
    }
}
