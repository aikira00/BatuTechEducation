package tappa1Swing;

public class Eroe {
    private String nome;
    private int vita;
    private int forza;
    private Vista vista;

    public Eroe(String nome, int vita, int forza, Vista vista) {
        this.nome = nome;
        this.vista = vista;
        if (vita > 0) {
            this.vita = vita;
        } else {
            vista.mostra("Vita non valida, uso 20");
            this.vita = 20;
        }
        if (forza >= 0) {
            this.forza = forza;
        } else {
            vista.mostra("Forza non valida, uso 1");
            this.forza = 1;
        }
    }

    public Eroe(String nome, Vista vista) {
        this(nome, 20, 1, vista);
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
        int danno = d.tira() + forza;
        vista.mostra(nome + " colpisce " + m.getNome() + ": " + danno + " danni");
        m.subisci(danno);
    }

    @Override
    public String toString() {
        return nome + " (vita " + vita + ")";
    }
}
