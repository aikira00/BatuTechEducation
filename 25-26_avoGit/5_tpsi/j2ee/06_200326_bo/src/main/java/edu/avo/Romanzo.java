package edu.avo;

import java.time.Year;
import java.util.Objects;

public class Romanzo {

    private int id;
    private String titolo;
    private Year annoPubblicazione;
    private Autore autore; // FK nel DB → riferimento all'oggetto in Java

    // Costruttore pre-insert (senza ID)
    public Romanzo(String titolo, Year annoPubblicazione, Autore autore) {
        this.titolo = Objects.requireNonNull(titolo, "Il titolo non può essere null");
        this.annoPubblicazione = Objects.requireNonNull(annoPubblicazione, "L'anno non può essere null");
        this.autore = Objects.requireNonNull(autore, "L'autore non può essere null");
    }

    // Costruttore post-fetch dal DB (con ID)
    public Romanzo(int id, String titolo, Year annoPubblicazione, Autore autore) {
        this(titolo, annoPubblicazione, autore);
        this.id = id;
    }

    // ID: niente setter, assegnato solo dal DB
    public int getId() { return id; }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) {
        this.titolo = Objects.requireNonNull(titolo, "Il titolo non può essere null");
    }

    public Year getAnnoPubblicazione() { return annoPubblicazione; }
    public void setAnnoPubblicazione(Year annoPubblicazione) {
        this.annoPubblicazione = Objects.requireNonNull(annoPubblicazione, "L'anno non può essere null");
    }

    public Autore getAutore() { return autore; }
    public void setAutore(Autore autore) {
        this.autore = Objects.requireNonNull(autore, "L'autore non può essere null");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Romanzo other)) return false;
        if (this.id > 0 && other.id > 0) return this.id == other.id;
        return Objects.equals(titolo, other.titolo) &&
                Objects.equals(autore, other.autore);
    }

    @Override
    public int hashCode() {
        return id > 0 ? Objects.hash(id) : Objects.hash(titolo, autore);
    }

    @Override
    public String toString() {
        return "Romanzo{id=%d, titolo='%s', anno=%s, autore=%s}"
                .formatted(id, titolo, annoPubblicazione, autore);
    }
}