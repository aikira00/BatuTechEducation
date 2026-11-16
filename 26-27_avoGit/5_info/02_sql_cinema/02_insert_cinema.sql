USE cinema;

-- Paola Cortellesi non ha film in tabella: partecipazione (0,N) di REGISTA
INSERT INTO REGISTA (id_regista, nome, cognome, nazionalita, anno_nascita) VALUES
  (1, 'Christopher', 'Nolan',      'britannica',   1970),
  (2, 'Roberto',     'Benigni',    'italiana',     1952),
  (3, 'Lee',         'Unkrich',    'statunitense', 1967),
  (4, 'Pete',        'Docter',     'statunitense', 1968),
  (5, 'Paolo',       'Genovese',   'italiana',     1966),
  (6, 'Paola',       'Cortellesi', 'italiana',     1973);

-- voto = voto medio della classe (1-10); NULL se il film non è stato votato
INSERT INTO FILM (codice, titolo, anno, genere, durata, voto, id_regista) VALUES
  ('F01', 'Inception',            2010, 'fantascienza', 148, 9,    1),
  ('F02', 'Interstellar',         2014, 'fantascienza', 169, 8,    1),
  ('F03', 'La vita è bella',      1997, 'commedia',     116, 9,    2),
  ('F04', 'Coco',                 2017, 'animazione',   105, 8,    3),
  ('F05', 'Inside Out',           2015, 'animazione',    95, 7,    4),
  ('F06', 'Up',                   2009, 'animazione',    96, NULL, 4),
  ('F07', 'Perfetti sconosciuti', 2016, 'commedia',      97, 7,    5),
  ('F08', 'Oppenheimer',          2023, 'drammatico',   180, NULL, 1);
