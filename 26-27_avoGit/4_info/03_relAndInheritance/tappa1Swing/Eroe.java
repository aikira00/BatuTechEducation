package tappa1Swing;

public class Eroe {
    private String nome;
    private int vita;
    private Arma arma;
    private Pozione pozione;
    private Vista vista;

    public Eroe(String nome, int vita, Arma arma, Vista vista) {
        this.nome = nome;
        this.vista = vista;
        if (vita > 0) {
            this.vita = vita;
        } else {
            vista.mostra("Vita non valida, uso 20");
            this.vita = 20;
        }
        this.arma = arma;                       // la riceve: c'era già
        this.pozione = new Pozione(6, vista);   // la crea: è sua
    }

    public Eroe(String nome, Arma arma, Vista vista) {
        this(nome, 20, arma, vista);
    }

    public String getNome() {
        return nome;
    }

    public int getVita() {
        return vita;
    }

    public boolean isVivo() {
        return vita > 0;
    }

    public void subisci(int danno) {
        if (danno < 0) {
            vista.mostra("Rifiutato: " + nome + " non guarisce con " + danno + " danni");
            return;
        }
        vita = vita - danno;
        if (vita < 0) {
            vita = 0;
        }
    }

    public void attacca(Mostro m, Dado d) {
        int danno = arma.colpo(d);
        vista.mostra(nome + " colpisce " + m.getNome() + " con " + arma + ": " + danno + " danni");
        m.subisci(danno);
    }

    public void cura() {
        int punti = pozione.bevi();
        if (punti > 0) {
            vita = vita + punti;
            vista.mostra(nome + " beve la pozione: +" + punti);
        } else {
            vista.mostra(nome + " ha già bevuto la pozione");
        }
    }

    @Override
    public String toString() {
        return nome + " (vita " + vita + ") con " + arma;
    }
}
