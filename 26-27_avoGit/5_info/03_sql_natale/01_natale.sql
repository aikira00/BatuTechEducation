-- Database natale: per provare CROSS, INNER, LEFT, RIGHT e FULL JOIN
-- COLORE(colore)        REGALO(regalo)        PERSONA(nome, regalo)
-- COLORE e REGALO non hanno colonne in comune: solo CROSS JOIN
-- PERSONA e REGALO hanno in comune la colonna regalo
-- Niente chiavi esterne: Carla chiede il monopattino, che in REGALO non c'è

CREATE DATABASE natale CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE natale;

CREATE TABLE COLORE (
  colore VARCHAR(20) PRIMARY KEY
);

CREATE TABLE REGALO (
  regalo VARCHAR(20) PRIMARY KEY
);

CREATE TABLE PERSONA (
  nome   VARCHAR(20) PRIMARY KEY,
  regalo VARCHAR(20)
);

INSERT INTO COLORE (colore) VALUES ('rosso'), ('verde'), ('bianco'), ('oro');
INSERT INTO REGALO (regalo) VALUES ('calzini'), ('sciarpa'), ('felpa');

INSERT INTO PERSONA (nome, regalo) VALUES
  ('Anna',  'calzini'),
  ('Bruno', 'calzini'),
  ('Carla', 'monopattino');
