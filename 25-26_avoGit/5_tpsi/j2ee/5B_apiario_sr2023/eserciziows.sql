DROP DATABASE IF EXISTS eserciziows;
CREATE DATABASE eserciziows;
USE esameeserciziows;

CREATE TABLE tipimiele (
  id int NOT NULL PRIMARY KEY auto_increment,
  descrizione varchar(50) NOT NULL
);


INSERT INTO tipimiele ( descrizione) VALUES
('Nazionale'),
('Regionale'),
('Territoriale'),
('DOP');
CREATE TABLE province (
  id int NOT NULL PRIMARY KEY auto_increment,
  nome varchar(255) NOT NULL
);

INSERT INTO province ( nome) VALUES
('Alessandria'),
('Astigiano'),
('Biella'),
('Cuneo'),
('Novara'),
('Torino'),
('Vercelli'),
('Verbano-Cusio-Ossola');

CREATE TABLE apiari (
  id int NOT NULL PRIMARY KEY auto_increment,
  localita varchar(255) NOT NULL,
  apicultore varchar(255) NOT NULL,
  idprovincia int NOT NULL,
  link varchar(255) DEFAULT NULL,
  FOREIGN KEY (idprovincia) REFERENCES province(id)
);


INSERT INTO apiari (localita, apicultore, idprovincia, link) VALUES
('aaaaaaaa', 'rossi', 6, 'www.rossi.it'),
('bbbbbbbb', 'verdi', 6, 'www.verdi.it'),
('cccccccc', 'giallo', 6, NULL),
('dddddddd', 'mario', 2, 'www.mario.it'),
('eeeeeeee', 'xxxx', 4, 'www.xxx.it'),
('fffffff', 'yyyy', 4, 'www.yyyy.it'),
('gggggggg', 'rosrrrrsi', 1, 'www.rosrrrrsi.it'),
('hhhhhhhh', 'zzzzz', 3, 'www.zzzzz.it');

CREATE TABLE mieli (
  id int NOT NULL PRIMARY KEY auto_increment,
  denominazione varchar(50) NOT NULL,
  tipologia int NOT NULL,
  FOREIGN KEY (tipologia) REFERENCES tipimiele(id)
);


INSERT INTO mieli ( denominazione, tipologia) VALUES
('aaaaaaa', 1),
('bbbb', 3),
('cccccccc', 4),
('asfdgffgfg', 2),
('bbbbb', 3),
('zzzzzz', 3),
('mmmm', 2),
('m1', 1),
('m2', 3);
