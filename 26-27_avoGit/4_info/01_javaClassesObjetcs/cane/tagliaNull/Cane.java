package tagliaNull;

// PASSO 5 — null: il setTaglia delle slide 14 e 26, con la variabile a sinistra di equals.
// Se t è null, t.equals(...) lancia NullPointerException.
// Le difese (t != null, oppure "piccola".equals(t)) sono nella slide 27: qui mancano apposta.
public class Cane {
    private String nome;
    private String razza;
    private String taglia;

    public String getNome() {
        return nome;
    }

    public String getRazza() {
        return razza;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public void setTaglia(String t) {
        // le String si confrontano con equals, non con ==
        if (t.equals("piccola")
                || t.equals("media")
                || t.equals("grande")) {
            taglia = t;
        }
    }
}
