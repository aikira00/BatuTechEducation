package stampante;

public class Computer {
    private String nome;
    private Stampante[] stampanti;
    private int quante;

    public Computer(String nome) {
        this.nome = nome;
        this.stampanti = new Stampante[3];   // tre posti vuoti: le stampanti le crea il main
        this.quante = 0;
    }

    public void aggiungiStampante(Stampante s) {
        if (quante < stampanti.length) {
            stampanti[quante] = s;           // associazione: è solo collegato
            quante++;
        } else {
            System.out.println(nome + ": già 3 stampanti collegate");
        }
    }

    public void stampa(String documento, int pagine, int posizione) {
        if (posizione >= 0 && posizione < quante) {
            System.out.println(nome + " manda in stampa " + documento + " (" + pagine + " pagine)");
            stampanti[posizione].stampa(documento, pagine);
        } else {
            System.out.println(nome + ": nessuna stampante in posizione " + posizione);
        }
    }
}