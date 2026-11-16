package tappa1;

public class Eroe {
    private String nome;
    private int vita;
    private Arma arma;
    private Pozione pozione;

    public Eroe(String nome, int vita, Arma arma) {
        this.nome = nome;
        if (vita > 0) {
            this.vita = vita;
        } else {
            System.out.println("Vita non valida, uso 20");
            this.vita = 20;
        }
        this.arma = arma;                // la riceve: c'era già
        this.pozione = new Pozione(6);   // la crea: è sua
    }

    public Eroe(String nome, Arma arma) {
        this(nome, 20, arma);
    }

    public String getNome() {
        return nome;
    }

    public boolean isVivo() {
        return vita > 0;
    }

    public void subisci(int danno) {
        if (danno < 0) {
            System.out.println("Rifiutato: " + nome + " non guarisce con " + danno + " danni");
            return;
        }
        vita = vita - danno;
        if (vita < 0) {
            vita = 0;
        }
    }

    public void attacca(Mostro m, Dado d) {
        int danno = arma.colpo(d);
        System.out.println(nome + " colpisce " + m.getNome() + " con " + arma + ": " + danno + " danni");
        m.subisci(danno);
    }

    public void cura() {
        int punti = pozione.bevi();
        if (punti > 0) {
            vita = vita + punti;
            System.out.println(nome + " beve la pozione: +" + punti);
        } else {
            System.out.println(nome + " ha già bevuto la pozione");
        }
    }

    @Override
    public String toString() {
        return nome + " (vita " + vita + ") con " + arma;
    }
}
