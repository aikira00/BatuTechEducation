package dungeon2;

public class Eroe extends Personaggio {
    private Arma arma;
    private Pozione pozione;

    public Eroe(String nome, Arma arma) {
        super(nome, 20);
        this.arma = arma;
        this.pozione = new Pozione(6);
    }

    public void impugna(Arma nuova) {
        arma = nuova;
        System.out.println(getNome() + " impugna " + arma);
    }

    public void attacca(Mostro m, Dado d) {
        int danno = arma.colpo(d);
        System.out.println(getNome() + " colpisce " + m.getNome() + " con " + arma + ": " + danno + " danni");
        m.subisci(danno);
    }

    public void cura() {
        int punti = pozione.bevi();
        if (punti > 0) {
            guarisci(punti);
            System.out.println(getNome() + " beve la pozione: +" + punti);
        } else {
            System.out.println(getNome() + " ha già bevuto la pozione");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " con " + arma;
    }
}
