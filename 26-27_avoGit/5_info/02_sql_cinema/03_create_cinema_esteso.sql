-- Database cinema_esteso: cinema (slide 07) + altri film, saghe, attori, studenti e voti
-- Le righe di cinema ci sono tutte, uguali: qui se ne aggiungono altre.
--
-- E/R
--   FILM (1,1) --- regia --------- (0,N) REGISTA
--   FILM (0,1) --- appartenenza -- (0,N) SAGA
--   FILM (0,N) --- interpretazione (personaggio) --- (0,N) ATTORE
--   STUDENTE (0,1) --- preferenza --- (0,N) FILM
--   STUDENTE (0,N) --- votazione (voto) --- (0,N) FILM
--
-- Logico
--   REGISTA(id_regista, nome, cognome, nazionalita, anno_nascita)
--   SAGA(id_saga, nome, anno_inizio)
--   FILM(codice, titolo, anno, genere, durata, voto, id_regista*, id_saga*)
--     NOT NULL: id_regista
--     Valori NULL: voto, id_saga
--   ATTORE(id_attore, nome, cognome, nazionalita, anno_nascita)
--   INTERPRETAZIONE(codice_film*, id_attore*, personaggio)
--   STUDENTE(id_studente, nome, cognome, codice_film_preferito*)
--     Valori NULL: codice_film_preferito
--   VOTAZIONE(id_studente*, codice_film*, voto)
--
-- FILM.voto e' il voto medio della classe arrotondato: si puo' calcolare da VOTAZIONE (dato ridondante).

CREATE DATABASE cinema_esteso CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cinema_esteso;

CREATE TABLE REGISTA (
  id_regista   INT UNSIGNED AUTO_INCREMENT,
  nome         VARCHAR(30) NOT NULL,
  cognome      VARCHAR(30) NOT NULL,
  nazionalita  VARCHAR(20) NOT NULL,
  anno_nascita INT NOT NULL CHECK (anno_nascita > 1880),
  PRIMARY KEY (id_regista)
);

CREATE TABLE SAGA (
  id_saga     INT UNSIGNED AUTO_INCREMENT,
  nome        VARCHAR(40) NOT NULL,
  anno_inizio INT NOT NULL CHECK (anno_inizio > 1895),
  PRIMARY KEY (id_saga)
);

CREATE TABLE FILM (
  codice     CHAR(3),
  titolo     VARCHAR(60) NOT NULL,
  anno       INT NOT NULL CHECK (anno > 1895),
  genere     VARCHAR(20) NOT NULL,
  durata     INT NOT NULL CHECK (durata > 0),
  voto       INT NULL CHECK (voto BETWEEN 1 AND 10),
  id_regista INT UNSIGNED NOT NULL,
  id_saga    INT UNSIGNED NULL,
  PRIMARY KEY (codice),
  FOREIGN KEY (id_regista) REFERENCES REGISTA(id_regista),
  FOREIGN KEY (id_saga) REFERENCES SAGA(id_saga)
);

CREATE TABLE ATTORE (
  id_attore    INT UNSIGNED AUTO_INCREMENT,
  nome         VARCHAR(30) NOT NULL,
  cognome      VARCHAR(30) NOT NULL,
  nazionalita  VARCHAR(20) NOT NULL,
  anno_nascita INT NOT NULL CHECK (anno_nascita > 1880),
  PRIMARY KEY (id_attore)
);

CREATE TABLE INTERPRETAZIONE (
  codice_film CHAR(3),
  id_attore   INT UNSIGNED,
  personaggio VARCHAR(40) NOT NULL,
  PRIMARY KEY (codice_film, id_attore),
  FOREIGN KEY (codice_film) REFERENCES FILM(codice),
  FOREIGN KEY (id_attore) REFERENCES ATTORE(id_attore)
);

CREATE TABLE STUDENTE (
  id_studente           INT UNSIGNED AUTO_INCREMENT,
  nome                  VARCHAR(30) NOT NULL,
  cognome               VARCHAR(30) NOT NULL,
  codice_film_preferito CHAR(3) NULL,
  PRIMARY KEY (id_studente),
  FOREIGN KEY (codice_film_preferito) REFERENCES FILM(codice)
);

CREATE TABLE VOTAZIONE (
  id_studente INT UNSIGNED,
  codice_film CHAR(3),
  voto        INT NOT NULL CHECK (voto BETWEEN 1 AND 10),
  PRIMARY KEY (id_studente, codice_film),
  FOREIGN KEY (id_studente) REFERENCES STUDENTE(id_studente),
  FOREIGN KEY (codice_film) REFERENCES FILM(codice)
);
