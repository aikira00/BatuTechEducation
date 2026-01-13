package edu.avo.demo;

public class Miele {
    //private int id = -1;
    private int id;
    private int tipologia; //non mi paice tanto
    private String denominazione;

    public Miele(){

    }
    public Miele(int tipologia, String denominazione) {
        this.tipologia = tipologia;
        this.denominazione = denominazione;
    }

    public int getId() {
        return id;
    }

    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int tipologia) {
        this.tipologia = tipologia;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }
}
