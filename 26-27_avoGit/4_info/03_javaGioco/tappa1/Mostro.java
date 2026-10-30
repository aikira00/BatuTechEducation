package tappa1;

// Il campo tipo e i due switch sono voluti: si tolgono con l'ereditarietà.
public class Mostro {
    private String nome;
    private String tipo;   // "goblin" oppure "scheletro"
    private int vita;

    public Mostro(String nome, String tipo, int vita) {
        this.nome = nome;
        this.tipo = tipo;
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

    public void descrivi() {
        switch (tipo) {
            case "goblin":
                System.out.println(nome + " ringhia nell'ombra");
                break;
            case "scheletro":
                System.out.println(nome + " si alza facendo rumore di ossa");
                break;
            default:
                System.out.println(nome + " ti guarda");
        }
    }

    public void attacca(Eroe e, Dado d) {
        int danno;
        switch (tipo) {
            case "goblin":
                danno = d.tira();     // colpisce come capita
                break;
            case "scheletro":
                danno = 2;            // lento ma costante
                break;
            default:
                danno = 1;
        }
        System.out.println(nome + " colpisce: " + danno + " danni");
        e.subisci(danno);
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ", vita " + vita + ")";
    }
}
