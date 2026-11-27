package dungeon2;

public class Personaggio {
    private String nome;
    private int vita;

    public Personaggio(String nome, int vita) {
        this.nome = nome;
        if (vita > 0) {
            this.vita = vita;
        } else {
            System.out.println("Vita non valida, uso 10");
            this.vita = 10;
        }
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

    public void guarisci(int punti) {
        if (punti < 0) {
            System.out.println("Rifiutato: " + nome + " non guarisce di " + punti);
            return;
        }
        vita = vita + punti;
    }

    @Override
    public String toString() {
        return nome + " (vita " + vita + ")";
    }
}
