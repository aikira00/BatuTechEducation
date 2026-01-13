# DAtabase

USE TPSI_5Anno;

-- Elimina la tabella se esiste
DROP TABLE IF EXISTS libri;

-- Crea la tabella con SMALLINT per l'anno
CREATE TABLE libri(
id INT AUTO_INCREMENT PRIMARY KEY,
titolo VARCHAR(200) NOT NULL,
autore VARCHAR(100) NOT NULL,
anno_pubblicazione SMALLINT NOT NULL,  -- SMALLINT invece di YEAR
genere VARCHAR(50),
prezzo DECIMAL(6, 2),
isbn VARCHAR(20) UNIQUE,
editore VARCHAR(100),
numero_pagine INT,
disponibile BOOLEAN DEFAULT TRUE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Inserisce 10 libri di esempio
INSERT INTO libri(
titolo,
autore,
anno_pubblicazione,
genere,
prezzo,
isbn,
editore,
numero_pagine,
disponibile
)
VALUES
('Il nome della rosa', 'Umberto Eco', 1980, 'Romanzo storico', 14.50, '978-8845292613', 'Bompiani', 503, TRUE),
('1984', 'George Orwell', 1949, 'Distopia', 12.00, '978-8804668879', 'Mondadori', 328, TRUE),
('Il Signore degli Anelli', 'J.R.R. Tolkien', 1954, 'Fantasy', 25.00, '978-8845292453', 'Bompiani', 1255, FALSE),
('Cent\'anni di solitudine', 'Gabriel García Márquez', 1967, 'Realismo magico', 13.50, '978-8804668527', 'Mondadori', 432, TRUE),
('Il piccolo principe', 'Antoine de Saint-Exupéry', 1943, 'Favola', 8.50, '978-8845292934', 'Bompiani', 96, TRUE),
('Orgoglio e pregiudizio', 'Jane Austen', 1813, 'Romanzo', 11.00, '978-8804668534', 'Mondadori', 416, TRUE),
('Il codice da Vinci', 'Dan Brown', 2003, 'Thriller', 15.00, '978-8804668541', 'Mondadori', 512, FALSE),
('Harry Potter e la pietra filosofale', 'J.K. Rowling', 1997, 'Fantasy', 16.00, '978-8845292620', 'Salani', 302, TRUE),
('Promessi Sposi', 'Alessandro Manzoni', 1840, 'Romanzo storico', 9.50, '978-8804668558', 'Mondadori', 720, TRUE),
('Il vecchio e il mare', 'Ernest Hemingway', 1952, 'Romanzo', 10.00, '978-8804668565', 'Mondadori', 127, TRUE);

-- Verifica i dati inseriti
SELECT * FROM libri ORDER BY anno_pubblicazione;
-- Serie completa di Harry Potter (J.K. Rowling)
INSERT INTO libri(titolo, autore, anno_pubblicazione, genere, prezzo, isbn, editore, numero_pagine, disponibile)
VALUES
('Harry Potter e la camera dei segreti', 'J.K. Rowling', 1998, 'Fantasy', 16.50, '978-8845292637', 'Salani', 366, TRUE),
('Harry Potter e il prigioniero di Azkaban', 'J.K. Rowling', 1999, 'Fantasy', 17.00, '978-8845292644', 'Salani', 448, TRUE),
('Harry Potter e il calice di fuoco', 'J.K. Rowling', 2000, 'Fantasy', 19.00, '978-8845292651', 'Salani', 656, FALSE),
('Harry Potter e l\'Ordine della Fenice', 'J.K. Rowling', 2003, 'Fantasy', 21.00, '978-8845292668', 'Salani', 896, TRUE),
('Harry Potter e il principe mezzosangue', 'J.K. Rowling', 2005, 'Fantasy', 18.50, '978-8845292675', 'Salani', 608, TRUE),
('Harry Potter e i Doni della Morte', 'J.K. Rowling', 2007, 'Fantasy', 22.00, '978-8845292682', 'Salani', 784, TRUE);

-- Altri libri di George Orwell
INSERT INTO libri(titolo, autore, anno_pubblicazione, genere, prezzo, isbn, editore, numero_pagine, disponibile)
VALUES
('La fattoria degli animali', 'George Orwell', 1945, 'Satira politica', 11.00, '978-8804668886', 'Mondadori', 144, TRUE),
('Omaggio alla Catalogna', 'George Orwell', 1938, 'Memoir', 13.50, '978-8804668893', 'Mondadori', 256, TRUE),
('Senza un soldo a Parigi e Londra', 'George Orwell', 1933, 'Memoir', 12.50, '978-8804668909', 'Mondadori', 224, FALSE);

-- Trilogia de Il Signore degli Anelli (J.R.R. Tolkien)
INSERT INTO libri(titolo, autore, anno_pubblicazione, genere, prezzo, isbn, editore, numero_pagine, disponibile)
VALUES
('La Compagnia dell\'Anello', 'J.R.R. Tolkien', 1954, 'Fantasy', 15.00, '978-8845292460', 'Bompiani', 423, TRUE),
('Le Due Torri', 'J.R.R. Tolkien', 1954, 'Fantasy', 15.00, '978-8845292477', 'Bompiani', 352, TRUE),
('Il Ritorno del Re', 'J.R.R. Tolkien', 1955, 'Fantasy', 16.00, '978-8845292484', 'Bompiani', 490, TRUE),
('Lo Hobbit', 'J.R.R. Tolkien', 1937, 'Fantasy', 14.00, '978-8845292491', 'Bompiani', 304, TRUE),
('Il Silmarillion', 'J.R.R. Tolkien', 1977, 'Fantasy', 18.00, '978-8845292507', 'Bompiani', 480, FALSE);

-- Altri libri di Ernest Hemingway
INSERT INTO libri(titolo, autore, anno_pubblicazione, genere, prezzo, isbn, editore, numero_pagine, disponibile)
VALUES
('Per chi suona la campana', 'Ernest Hemingway', 1940, 'Romanzo', 14.00, '978-8804668619', 'Mondadori', 544, TRUE),
('Addio alle armi', 'Ernest Hemingway', 1929, 'Romanzo', 13.00, '978-8804668626', 'Mondadori', 352, TRUE),
('Fiesta', 'Ernest Hemingway', 1926, 'Romanzo', 12.50, '978-8804668633', 'Mondadori', 304, FALSE),
('Il giardino dell\'Eden', 'Ernest Hemingway', 1986, 'Romanzo', 13.50, '978-8804668640', 'Mondadori', 320, TRUE);

-- Altri libri di Jane Austen
INSERT INTO libri(titolo, autore, anno_pubblicazione, genere, prezzo, isbn, editore, numero_pagine, disponibile)
VALUES
('Emma', 'Jane Austen', 1815, 'Romanzo', 11.50, '978-8804668657', 'Mondadori', 512, TRUE),
('Ragione e sentimento', 'Jane Austen', 1811, 'Romanzo', 11.00, '978-8804668664', 'Mondadori', 400, TRUE),
('Mansfield Park', 'Jane Austen', 1814, 'Romanzo', 12.00, '978-8804668671', 'Mondadori', 544, FALSE),
('Persuasione', 'Jane Austen', 1817, 'Romanzo', 10.50, '978-8804668688', 'Mondadori', 288, TRUE);

## Query utili

-- Tutti i libri di Harry Potter in ordine cronologico
SELECT titolo, anno_pubblicazione, prezzo, disponibile
FROM libri
WHERE autore = 'J.K. Rowling'
ORDER BY anno_pubblicazione;

-- Serie completa di Tolkien
SELECT titolo, anno_pubblicazione, numero_pagine
FROM libri
WHERE autore = 'J.R.R. Tolkien'
ORDER BY anno_pubblicazione;

-- Thriller di Dan Brown disponibili
SELECT titolo, anno_pubblicazione, prezzo
FROM libri
WHERE autore = 'Dan Brown' AND disponibile = TRUE
ORDER BY anno_pubblicazione;

-- Autori con più di 3 libri
SELECT autore, COUNT(*) as totale_libri,
SUM(CASE WHEN disponibile = TRUE THEN 1 ELSE 0 END) as disponibili
FROM libri
GROUP BY autore
HAVING COUNT(*) > 3
ORDER BY totale_libri DESC;

-- Costo totale per acquistare tutta la saga di Harry Potter
SELECT SUM(prezzo) as costo_totale_harry_potter
FROM libri
WHERE autore = 'J.K. Rowling';

-- Libri più lunghi (oltre 500 pagine)
SELECT titolo, autore, numero_pagine
FROM libri
WHERE numero_pagine > 500
ORDER BY numero_pagine DESC;

-- Media pagine per autore
SELECT autore,
COUNT(*) as num_libri,
ROUND(AVG(numero_pagine)) as pagine_medie,
ROUND(AVG(prezzo), 2) as prezzo_medio
FROM libri
GROUP BY autore
ORDER BY pagine_medie DESC;

# Deployment sito in htdocs Xampp automatico
HPStorm copia automaticamente i file in htdocs quando li salvi.

### **Setup in PHPStorm:**

**1. Apri Settings:**
```
File → Settings → Build, Execution, Deployment → Deployment
```

**2. Aggiungi nuovo server:**
- Click **+** (Add)
- **Name:** XAMPP Local
- **Type:** Local or mounted folder
- Click **OK**

**3. Configura Connection:**
- **Folder:** `/Applications/XAMPP/xamppfiles/htdocs/biblioteca`
- **Web server URL:** `http://localhost/biblioteca`

**4. Configura Mappings:**
- **Local path:** `/Users/cristina/GIT_AVO/BatuTechEducation/25-26_avoGit/5_tpsi/php/2ExampleXamppDB`
- **Deployment path:** `/`
- **Web path:** `/`

**5. Abilita auto-upload:**
```
Tools → Deployment → Automatic Upload → ✓ Always