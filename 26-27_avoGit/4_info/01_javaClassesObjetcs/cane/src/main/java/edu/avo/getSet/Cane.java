package edu.avo.getSet;

// PASSO 3 — incapsulamento: lo stato è private, e si passa da getter e setter.
// I setter non si limitano a copiare il valore: decidono se accettarlo.
public class Cane {
    private String nome;
    private String razza;
    private String taglia;
    private int eta;

    public String getNome() {
        return nome;
    }

    public String getRazza() {
        return razza;
    }

    public String getTaglia() {
        return taglia;
    }

    public int getEta() {
        return eta;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        } else {
            System.out.println("Rifiutato: il nome non può essere vuoto");
        }
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public void setTaglia(String taglia) {
        if ("piccola".equals(taglia) || "media".equals(taglia) || "grande".equals(taglia)) {
            this.taglia = taglia;
        } else {
            System.out.println("Rifiutato: la taglia è piccola, media o grande, non " + taglia);
        }
    }

    public void setEta(int eta) {
        if (eta >= 0 && eta <= 30) {
            this.eta = eta;
        } else {
            System.out.println("Rifiutato: un cane non può avere " + eta + " anni");
        }
    }

    public void abbaia() {
        System.out.println(nome + ": Bau bau!");
    }

    public void presentati() {
        System.out.println(nome + ", " + razza + ", taglia " + taglia + ", " + eta + " anni");
    }
}
