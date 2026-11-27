package dungeon2;

public class Goblin extends Mostro {

    public Goblin(String nome, int vita) {
        super(nome, vita);
    }

    @Override
    public void descrivi() {
        System.out.println(getNome() + " ringhia nell'ombra");
    }

    @Override
    public int colpo(Dado d) {
        return d.tira();       // colpisce come capita
    }
}
