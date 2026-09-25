package edu.avo.infoHiding;

import java.time.Year;

// PASSO 4 — information hiding: da fuori si vedono solo i metodi public.
// Qui l'età NON è più memorizzata: si salva l'anno di nascita e l'età si calcola.
// I metodi public sono identici al passo 3, quindi chi usa Cane non se ne accorge.
public class Cane {
    private String nome;
    private String razza;
    private String taglia;
    private int annoNascita;   // prima era: private int eta;

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
        return Year.now().getValue() - annoNascita;
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
            this.annoNascita = Year.now().getValue() - eta;
        } else {
            System.out.println("Rifiutato: un cane non può avere " + eta + " anni");
        }
    }

    public void abbaia() {
        System.out.println(nome + ": Bau bau!");
    }

    public void presentati() {
        System.out.println(nome + ", " + razza + ", taglia " + taglia + ", " + getEta() + " anni");
    }
}
