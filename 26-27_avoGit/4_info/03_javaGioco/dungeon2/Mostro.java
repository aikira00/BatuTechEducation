package dungeon2;

public class Mostro extends Personaggio {

    public Mostro(String nome, int vita) {
        super(nome, vita);
    }

    public void descrivi() {
        System.out.println(getNome() + " ti guarda");
    }

    public int colpo(Dado d) {
        return 1;
    }

    public void attacca(Eroe e, Dado d) {
        int danno = colpo(d);
        System.out.println(getNome() + " colpisce: " + danno + " danni");
        e.subisci(danno);
    }
}
