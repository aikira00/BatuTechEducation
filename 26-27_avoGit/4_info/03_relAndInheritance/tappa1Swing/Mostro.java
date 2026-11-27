package tappa1Swing;

// Il campo tipo e i due switch sono voluti: si tolgono con l'ereditarietà.
public class Mostro {
    private String nome;
    private String tipo;   // "goblin" oppure "scheletro"
    private int vita;
    private Vista vista;

    public Mostro(String nome, String tipo, int vita, Vista vista) {
        this.nome = nome;
        this.tipo = tipo;
        this.vista = vista;
        if (vita > 0) {
            this.vita = vita;
        } else {
            vista.mostra("Vita non valida, uso 10");
            this.vita = 10;
        }
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

    public void descrivi() {
        switch (tipo) {
            case "goblin":
                vista.mostra(nome + " ringhia nell'ombra");
                break;
            case "scheletro":
                vista.mostra(nome + " si alza facendo rumore di ossa");
                break;
            default:
                vista.mostra(nome + " ti guarda");
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
        vista.mostra(nome + " colpisce: " + danno + " danni");
        e.subisci(danno);
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ", vita " + vita + ")";
    }
}
