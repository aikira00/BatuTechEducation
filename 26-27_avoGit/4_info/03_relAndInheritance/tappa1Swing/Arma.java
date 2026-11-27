package tappa1Swing;

public class Arma {
    private String nome;
    private int bonus;

    public Arma(String nome, int bonus, Vista vista) {
        this.nome = nome;
        if (bonus >= 0) {
            this.bonus = bonus;
        } else {
            vista.mostra("Bonus non valido, uso 0");
            this.bonus = 0;
        }
    }

    public int colpo(Dado d) {
        return d.tira() + bonus;
    }

    @Override
    public String toString() {
        return nome;
    }
}
