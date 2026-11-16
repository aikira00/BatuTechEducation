-- Database cinema: i film votati dalla classe e i loro registi
-- E/R: FILM (1,1) --- regia --- (0,N) REGISTA
-- REGISTA(id_regista, nome, cognome, nazionalita, anno_nascita)
-- FILM(codice, titolo, anno, genere, durata, voto, id_regista*)
--   Valori NULL: voto
--   NOT NULL: id_regista

CREATE DATABASE cinema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cinema;

CREATE TABLE REGISTA (
  id_regista   INT UNSIGNED AUTO_INCREMENT,
  nome         VARCHAR(30) NOT NULL,
  cognome      VARCHAR(30) NOT NULL,
  nazionalita  VARCHAR(20) NOT NULL,
  anno_nascita INT NOT NULL CHECK (anno_nascita > 1880),
  PRIMARY KEY (id_regista)
);

CREATE TABLE FILM (
  codice     CHAR(3),
  titolo     VARCHAR(60) NOT NULL,
  anno       INT NOT NULL CHECK (anno > 1895),
  genere     VARCHAR(20) NOT NULL,
  durata     INT NOT NULL CHECK (durata > 0),
  voto       INT NULL CHECK (voto BETWEEN 1 AND 10),
  id_regista INT UNSIGNED NOT NULL,
  PRIMARY KEY (codice),
  FOREIGN KEY (id_regista) REFERENCES REGISTA(id_regista)
);
