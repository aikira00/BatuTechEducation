package tappa1;

public class Eroe {
    private String nome;
    private int vita;
    private int forza;

    public Eroe(String nome, int vita, int forza) {
        this.nome = nome;
        if (vita > 0) {
            this.vita = vita;
        } else {
            System.out.println("Vita non valida, uso 20");
            this.vita = 20;
        }
        if (forza >= 0) {
            this.forza = forza;
        } else {
            System.out.println("Forza non valida, uso 1");
            this.forza = 1;
        }
    }

    public Eroe(String nome) {
        this(nome, 20, 1);
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
        int danno = d.tira() + forza;
        System.out.println(nome + " colpisce " + m.getNome() + ": " + danno + " danni");
        m.subisci(danno);
    }

    @Override
    public String toString() {
        return nome + " (vita " + vita + ")";
    }
}
