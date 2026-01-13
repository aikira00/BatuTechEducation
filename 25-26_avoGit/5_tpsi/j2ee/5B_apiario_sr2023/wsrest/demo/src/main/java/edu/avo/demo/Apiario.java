package edu.avo.demo;

public class Apiario {
    private int id;
    private String località;
    private String apicultore;
    private int idProvincia;
    private String link;
    private String provincia;

    public Apiario(){

    }

    public Apiario(int id, String località, String apicultore, int idProvincia, String link, String provincia) {
        this.id = id;
        this.località = località;
        this.apicultore = apicultore;
        this.idProvincia = idProvincia;
        this.link = link;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }

    public String getApicultore() {
        return apicultore;
    }

    public void setApicultore(String apicultore) {
        this.apicultore = apicultore;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
