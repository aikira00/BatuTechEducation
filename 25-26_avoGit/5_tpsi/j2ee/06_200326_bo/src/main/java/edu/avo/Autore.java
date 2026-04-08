package edu.avo;

import java.time.LocalDate;
import java.util.Objects;

public class Autore {

    private int id; //autoincrement sql
    private String cognome;
    private String nome;
    private LocalDate dataNascita;
    private LocalDate dataMorte;

    // Costruttore senza ID: usato prima del salvataggio nel DB
    public Autore(String cognome, String nome, LocalDate dataNascita, LocalDate dataMorte) {
        this.cognome = Objects.requireNonNull(cognome, "Il cognome non può essere null");
        this.nome = nome;
        this.dataNascita = dataNascita;
        this.dataMorte = dataMorte;
    }

    // Costruttore completo: usato quando si legge dal DB
    public Autore(int id, String cognome, String nome, LocalDate dataNascita, LocalDate dataMorte) {
        this(cognome, nome, dataNascita, dataMorte);
        this.id = id;
    }

    // Getter ID senza setter: il DB è l'unica fonte dell'ID
    public int getId() { return id; }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return nome; }
    public void setCognome(String nome) { this.nome = nome; }


    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }

    public LocalDate getDataMorte() { return dataMorte; }
    public void setDataMorte(LocalDate dataMorte) { this.dataMorte = dataMorte; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Autore other)) return false;
        // Se entrambi hanno ID valido, confronta per ID
        if (this.id > 0 && other.id > 0) return this.id == other.id;
        // Altrimenti confronto per cognome + nome
        return Objects.equals(cognome, other.cognome) &&
                Objects.equals(nome, other.nome);
    }

    @Override
    public int hashCode() {
        return id > 0 ? Objects.hash(id) : Objects.hash(cognome, nome);
    }

    @Override
    public String toString() {
        return "Autore{id=%d, cognome='%s', nome='%s', nascita=%s, morte=%s}"
                .formatted(id, cognome, nome, dataNascita, dataMorte);
    }

}
