package dungeon2;

public class Scheletro extends Mostro {

    public Scheletro(String nome, int vita) {
        super(nome, vita);
    }

    @Override
    public void descrivi() {
        System.out.println(getNome() + " si alza facendo rumore di ossa");
    }

    @Override
    public int colpo(Dado d) {
        return 2;              // lento ma costante
    }
}
