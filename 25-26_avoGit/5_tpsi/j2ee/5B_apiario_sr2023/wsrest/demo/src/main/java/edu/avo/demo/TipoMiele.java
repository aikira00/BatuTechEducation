package edu.avo.demo;

public class TipoMiele {
    private int id;
    private String descrizione;

    public TipoMiele(int id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }
    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
