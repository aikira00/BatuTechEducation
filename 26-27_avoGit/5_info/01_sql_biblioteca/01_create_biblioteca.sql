-- Biblioteca: creazione del database e delle tabelle.
-- Dopo questo script, eseguire 02_insert_biblioteca.sql.

-- DROP DATABASE IF EXISTS biblioteca;   -- togli il commento per ripartire da zero
CREATE DATABASE biblioteca
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE biblioteca;

CREATE TABLE AUTORE (
  id_autore    INT UNSIGNED AUTO_INCREMENT,
  cognome      VARCHAR(40)  NOT NULL,
  nome         VARCHAR(40)  NOT NULL,
  nazione      VARCHAR(30)  NOT NULL,
  genere       ENUM('M','F') NOT NULL,
  data_nascita DATE         NOT NULL,
  PRIMARY KEY (id_autore)
) ENGINE=InnoDB;

CREATE TABLE EDITORE (
  id_editore      INT UNSIGNED AUTO_INCREMENT,
  ragione_sociale VARCHAR(80) NOT NULL,
  indirizzo       VARCHAR(60) NOT NULL,
  citta           VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_editore)
) ENGINE=InnoDB;

CREATE TABLE CATEGORIA (
  id_categoria INT UNSIGNED AUTO_INCREMENT,
  descrizione  VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_categoria)
) ENGINE=InnoDB;

CREATE TABLE LIBRO (
  isbn         VARCHAR(13)  NOT NULL,
  titolo       VARCHAR(120) NOT NULL,
  lingua       ENUM('it','en','fr','es','de') NOT NULL,
  anno         INT          NOT NULL,
  prezzo       DECIMAL(6,2) NOT NULL,
  id_autore    INT UNSIGNED NOT NULL,
  id_editore   INT UNSIGNED NOT NULL,
  id_categoria INT UNSIGNED NOT NULL,
  PRIMARY KEY (isbn),
  CHECK (anno >= 1450),
  CHECK (prezzo >= 0),
  FOREIGN KEY (id_autore)    REFERENCES AUTORE(id_autore),
  FOREIGN KEY (id_editore)   REFERENCES EDITORE(id_editore),
  FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
) ENGINE=InnoDB;
