-- Biblioteca: creazione del database e delle tabelle.
-- Dopo questo script, eseguire 02_insert_biblioteca.sql.

-- DROP DATABASE IF EXISTS biblioteca;  
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
  data_nascita DATE         NOT NULL ,
  PRIMARY KEY (id_autore)
) 

CREATE TABLE EDITORE (
  id_editore      INT UNSIGNED AUTO_INCREMENT,
  ragione_sociale VARCHAR(80) NOT NULL,
  indirizzo       VARCHAR(60) NOT NULL,
  citta           VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_editore)
) 

CREATE TABLE CATEGORIA (
  id_categoria INT UNSIGNED AUTO_INCREMENT,
  descrizione  VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_categoria)
) 

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
  CONSTRAINT chk_anno CHECK (anno BETWEEN 1450 AND 2026),
  CONSTRAINT chk_prezzo CHECK (prezzo >= 0),
  CONSTRAINT fk_libro_autore FOREIGN KEY (id_autore)    REFERENCES AUTORE(id_autore),
  CONSTRAINT fk_libro_editore FOREIGN KEY (id_editore)   REFERENCES EDITORE(id_editore),
  CONSTRAINT fk_libro_categoria FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
) ;
