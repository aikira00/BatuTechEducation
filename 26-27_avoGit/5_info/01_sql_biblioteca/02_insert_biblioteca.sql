-- Biblioteca: dati di esempio.
-- Richiede il database creato da 01_create_biblioteca.sql.
--
-- anno = anno di prima pubblicazione dell'opera.
-- ISBN (con cifra di controllo valida), prezzi e indirizzi sono di fantasia.

USE biblioteca;

INSERT INTO AUTORE (id_autore, cognome, nome, nazione, genere, data_nascita) VALUES
  (1, 'Calvino', 'Italo', 'Italia', 'M', '1923-10-15'),
  (2, 'Morante', 'Elsa', 'Italia', 'F', '1912-08-18'),
  (3, 'Eco', 'Umberto', 'Italia', 'M', '1932-01-05'),
  (4, 'Ginzburg', 'Natalia', 'Italia', 'F', '1916-07-14'),
  (5, 'Levi', 'Primo', 'Italia', 'M', '1919-07-31'),
  (6, 'Camilleri', 'Andrea', 'Italia', 'M', '1925-09-06'),
  (7, 'Rodari', 'Gianni', 'Italia', 'M', '1920-10-23'),
  (8, 'Rovelli', 'Carlo', 'Italia', 'M', '1956-05-03'),
  (9, 'Manzoni', 'Alessandro', 'Italia', 'M', '1785-03-07'),
  (10, 'Machiavelli', 'Niccolò', 'Italia', 'M', '1469-05-03'),
  (11, 'Deledda', 'Grazia', 'Italia', 'F', '1871-09-27'),
  (12, 'Hack', 'Margherita', 'Italia', 'F', '1922-06-12'),
  (13, 'Orwell', 'George', 'Regno Unito', 'M', '1903-06-25'),
  (14, 'Woolf', 'Virginia', 'Regno Unito', 'F', '1882-01-25'),
  (15, 'Austen', 'Jane', 'Regno Unito', 'F', '1775-12-16'),
  (16, 'Christie', 'Agatha', 'Regno Unito', 'F', '1890-09-15'),
  (17, 'Tolkien', 'John Ronald Reuel', 'Regno Unito', 'M', '1892-01-03'),
  (18, 'Camus', 'Albert', 'Francia', 'M', '1913-11-07'),
  (19, 'Yourcenar', 'Marguerite', 'Francia', 'F', '1903-06-08'),
  (20, 'García Márquez', 'Gabriel', 'Colombia', 'M', '1927-03-06'),
  (21, 'Allende', 'Isabel', 'Cile', 'F', '1942-08-02'),
  (22, 'Bolaño', 'Roberto', 'Cile', 'M', '1953-04-28'),
  (23, 'Mann', 'Thomas', 'Germania', 'M', '1875-06-06'),
  (24, 'Hesse', 'Hermann', 'Germania', 'M', '1877-07-02'),
  (25, 'Murakami', 'Haruki', 'Giappone', 'M', '1949-01-12'),
  (26, 'Saramago', 'José', 'Portogallo', 'M', '1922-11-16');

INSERT INTO EDITORE (id_editore, ragione_sociale, indirizzo, citta) VALUES
  (1, 'Giulio Einaudi Editore', 'Via Umberto Biancamano 2', 'Torino'),
  (2, 'Arnoldo Mondadori Editore', 'Via Mondadori 1', 'Segrate'),
  (3, 'Giangiacomo Feltrinelli Editore', 'Via Andegari 6', 'Milano'),
  (4, 'Adelphi Edizioni', 'Via San Giovanni sul Muro 14', 'Milano'),
  (5, 'Sellerio Editore', 'Via Enzo ed Elvira Sellerio 50', 'Palermo'),
  (6, 'Bompiani', 'Via Bergognone 34', 'Milano'),
  (7, 'Penguin Books', '20 Vauxhall Bridge Road', 'London'),
  (8, 'Éditions Gallimard', '5 rue Gaston-Gallimard', 'Paris'),
  (9, 'Editorial Anagrama', 'Carrer de Pau Claris 172', 'Barcelona'),
  (10, 'S. Fischer Verlag', 'Hedderichstraße 114', 'Frankfurt am Main'),
  (11, 'Editori Laterza', 'Piazza Umberto I 54', 'Bari');

INSERT INTO CATEGORIA (id_categoria, descrizione) VALUES
  (1, 'Romanzo'),
  (2, 'Giallo'),
  (3, 'Fantasy'),
  (4, 'Saggio'),
  (5, 'Divulgazione scientifica'),
  (6, 'Letteratura per ragazzi'),
  (7, 'Memorialistica'),
  (8, 'Poesia');

INSERT INTO LIBRO (isbn, titolo, lingua, anno, prezzo, id_autore, id_editore, id_categoria) VALUES
  ('9788806001384', 'Il barone rampante', 'it', 1957, 12.00, 1, 1, 1),
  ('9788806001759', 'Le città invisibili', 'it', 1972, 13.00, 1, 1, 1),
  ('9788806002121', 'Se una notte d''inverno un viaggiatore', 'it', 1979, 14.00, 1, 1, 1),
  ('9788806002497', 'Marcovaldo', 'it', 1963, 10.50, 1, 1, 6),
  ('9788806002862', 'La Storia', 'it', 1974, 16.00, 2, 1, 1),
  ('9788806003234', 'L''isola di Arturo', 'it', 1957, 13.00, 2, 1, 1),
  ('9788845201387', 'Il nome della rosa', 'it', 1980, 15.00, 3, 6, 2),
  ('9788845201752', 'Il pendolo di Foucault', 'it', 1988, 16.00, 3, 6, 1),
  ('9788806003609', 'Lessico famigliare', 'it', 1963, 12.00, 4, 1, 1),
  ('9788806003975', 'Se questo è un uomo', 'it', 1947, 11.00, 5, 1, 7),
  ('9788806004347', 'Il sistema periodico', 'it', 1975, 12.50, 5, 1, 7),
  ('9788838901386', 'La forma dell''acqua', 'it', 1994, 14.00, 6, 5, 2),
  ('9788838901751', 'Il cane di terracotta', 'it', 1996, 14.00, 6, 5, 2),
  ('9788838902123', 'La gita a Tindari', 'it', 2000, 14.00, 6, 5, 2),
  ('9788806004712', 'Favole al telefono', 'it', 1962, 9.50, 7, 1, 6),
  ('9788806005085', 'Grammatica della fantasia', 'it', 1973, 12.00, 7, 1, 4),
  ('9788845901386', 'Sette brevi lezioni di fisica', 'it', 2014, 10.00, 8, 4, 5),
  ('9788845901751', 'L''ordine del tempo', 'it', 2017, 14.00, 8, 4, 5),
  ('9788804001386', 'I promessi sposi', 'it', 1827, 9.00, 9, 2, 1),
  ('9788807001383', 'Il Principe', 'it', 1532, 8.00, 10, 3, 4),
  ('9780141001388', 'Nineteen Eighty-Four', 'en', 1949, 9.99, 13, 7, 1),
  ('9780141001753', 'Animal Farm', 'en', 1945, 8.99, 13, 7, 1),
  ('9780141002125', 'Mrs Dalloway', 'en', 1925, 8.99, 14, 7, 1),
  ('9780141002491', 'Pride and Prejudice', 'en', 1813, 7.99, 15, 7, 1),
  ('9788804001751', 'Assassinio sull''Orient Express', 'it', 1934, 12.00, 16, 2, 2),
  ('9788845902123', 'Lo Hobbit', 'it', 1937, 13.00, 17, 4, 3),
  ('9788845202124', 'Il Signore degli Anelli', 'it', 1954, 25.00, 17, 6, 3),
  ('9782070001385', 'L''Étranger', 'fr', 1942, 7.50, 18, 8, 1),
  ('9782070001750', 'La Peste', 'fr', 1947, 9.00, 18, 8, 1),
  ('9788806005450', 'Memorie di Adriano', 'it', 1951, 13.00, 19, 1, 1),
  ('9788807001758', 'Cent''anni di solitudine', 'it', 1967, 14.00, 20, 3, 1),
  ('9788807002120', 'La casa degli spiriti', 'it', 1982, 13.00, 21, 3, 1),
  ('9788433901385', 'Los detectives salvajes', 'es', 1998, 14.90, 22, 9, 1),
  ('9783100001382', 'Buddenbrooks', 'de', 1901, 14.00, 23, 10, 1),
  ('9783100001757', 'Der Zauberberg', 'de', 1924, 16.00, 23, 10, 1),
  ('9783100002129', 'Siddhartha', 'de', 1922, 9.00, 24, 10, 1),
  ('9788806005825', 'Kafka sulla spiaggia', 'it', 2002, 14.00, 25, 1, 1),
  ('9788806006198', 'Cecità', 'it', 1995, 13.00, 26, 1, 1);
