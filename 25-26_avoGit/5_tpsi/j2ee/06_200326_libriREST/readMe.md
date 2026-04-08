FARE DB SERVER!


# Progetto 1 - 

libreria bo 06_200326_bo con oggetti Autore e Romanzo


 Produce target/biblioteca-dao.jar. Se vuoi installarlo nel repository 
locale Maven (per usarlo come dipendenza in altri progetti): mvn package

per usarlo come dipendenza in altri progetti e installarlo nel repository 
locale:
mvn install

## tomcat 

aggiungere in context.xml
<Resource name="jdbc/TPSI5_libriAutori_resConnPool" auth="Container" type="javax.sql.DataSource" maxTotal="10" maxIdle="5" maxWaitMillis="10000" username="biblioteca" password="tpsi" driverClassName="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/TPSI5_LibriAutoriConnPool?useSSL=false&amp;serverTimezone=UTC"/>


aggiungere in web.xml progetto - del db server!
<resource-ref>
<description>DB Biblioteca</description>
<res-ref-name>jdbc/biblioteca</res-ref-name>
<res-type>javax.sql.DataSource</res-type>
<res-auth>Container</res-aut

h>
</resource-ref>

### tomcat versus glassfish
**Tomcat** è un **Servlet container** — esegue Servlet e JSP, ma implementa solo una parte di Jakarta EE. Tutto ciò che manca (JAX-RS, injection, EJB) va aggiunto manualmente come libreria esterna (es. Jersey).

**GlassFish** è un **application server completo** — implementa l'intera specifica Jakarta EE. JAX-RS, `@Resource`, connection pool, injection funzionano nativamente senza aggiungere nulla.

In pratica: con Tomcat fai tutto a mano, con GlassFish il container pensa a tutto.

** esempio glassfish** 

```java
@Path("/autori")
public class AutoriResource {

    @Resource(lookup = "jdbc/biblioteca")
    private DataSource ds;  // GlassFish inietta il DataSource automaticamente

    @GET
    public Response getAutori() {
        try (Connection conn = ds.getConnection()) {  // ds già pronto, niente lookup manuale
            List<Autore> autori = new DbServer(conn).getAutori();
            return Response.ok(autori).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}```

@Resource(lookup = "jdbc/biblioteca") dice a GlassFish: 
"cerca nel registro JNDI la risorsa con questo nome e iniettala in questo 
campo prima che la classe venga usata". Il campo ds è già valorizzato 
quando arriva la prima richiesta HTTP.
mentre in Tomcat

```java
// Tutto questo sparisce con @Resource su GlassFish
private Connection getConnection() throws SQLException, NamingException {
InitialContext ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/biblioteca");
return ds.getConnection();
}```

perché nn ha il motore di dependency injection

No, perché come detto Tomcat non è un application server completo — non ha il motore di **dependency injection**.

Il ciclo di vita è diverso:

**GlassFish:**
```
Richiesta HTTP arriva
  → GlassFish istanzia AutoriResource
  → GlassFish legge @Resource
  → GlassFish inietta il DataSource nel campo ds
  → chiama il metodo getAutori()
```

**Tomcat:**
```
Richiesta HTTP arriva
  → Jersey istanzia AutoriResource  ← è Jersey, non Tomcat
  → nessuno legge @Resource
  → ds rimane null
  → NullPointerException
```

Il problema è che su Tomcat è **Jersey** ad istanziare le classi JAX-RS, non Tomcat stesso — e Jersey non implementa `@Resource`. Tomcat farebbe l'injection solo sulle **Servlet** (che gestisce lui direttamente), non sulle classi JAX-RS.

```java
// Su Tomcat questo funziona ✅ (è una Servlet gestita da Tomcat)
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Resource(lookup = "jdbc/biblioteca")
    private DataSource ds;  // Tomcat inietta correttamente
}

// Su Tomcat questo NON funziona ❌ (gestita da Jersey, non da Tomcat)
@Path("/autori")
public class AutoriResource {
    @Resource(lookup = "jdbc/biblioteca")
    private DataSource ds;  // null → NullPointerException
}
```


# progetto

creare cartella webapp con META_inf e WEB-INF ed editare context.xml e web.xml

src/main/
├── java/        ← 🔵 Sources Root
├── resources/   ← 🟡 Resources Root
└── webapp/      ← 🌐 Web Resource Directory (solo nel facet Web)
├── META-INF/
└── WEB-INF/

File → Project Structure (Ctrl+Alt+Shift+S)
→ Modules → tasto "+" → Web
→ Web Resource Directories → punta a:  src/main/webapp
→ Deployment Descriptors → punta a:    src/main/webapp/WEB-INF/web.xml
→ Apply → OK


# script database

-- Crea l'utente (sostituisci 'biblioteca' e 'password' con i valori reali)
CREATE USER 'biblioteca'@'localhost' IDENTIFIED BY 'tpsi';

-- Assegna tutti i privilegi sul database specifico
GRANT ALL PRIVILEGES ON biblioteca.* TO 'biblioteca'@'localhost';

-- Applica i cambiamenti
FLUSH PRIVILEGES;

CREATE TABLE AUTORE (
ID          INT          NOT NULL AUTO_INCREMENT,
COGNOME     VARCHAR(100)   NOT NULL,
NOME        VARCHAR(100)   NULL,
DATA_NASCITA DATE           NULL,
DATA_MORTE   DATE           NULL,
CONSTRAINT PK_AUTORE PRIMARY KEY (ID)
);

CREATE TABLE ROMANZO (
ID                 INT     NOT NULL AUTO_INCREMENT,
ANNO_PUBBLICAZIONE DATE    NOT NULL,
AUTORE             INT     NOT NULL,
CONSTRAINT PK_ROMANZO PRIMARY KEY (ID),
CONSTRAINT FK_AUTORE FOREIGN KEY (AUTORE)
REFERENCES AUTORE(ID)
);

## popolazione db

-- ============================================================
--  POPOLAMENTO DATABASE - 60 Autori, 100 Romanzi
--  (ID AUTO_INCREMENT - non specificato negli INSERT)
-- ============================================================

-- ------------------------------------------------------------
--  AUTORI
-- ------------------------------------------------------------
INSERT INTO AUTORE (COGNOME, NOME, DATA_NASCITA, DATA_MORTE) VALUES
('Manzoni',      'Alessandro', '1785-03-07', '1873-05-22'),   -- ID 1
('Calvino',      'Italo',      '1923-10-15', '1985-09-19'),   -- ID 2
('Eco',          'Umberto',    '1932-01-05', '2016-02-19'),   -- ID 3
('Moravia',      'Alberto',    '1907-11-28', '1990-09-26'),   -- ID 4
('Pavese',       'Cesare',     '1908-09-09', '1950-08-27'),   -- ID 5
('Svevo',        'Italo',      '1861-12-19', '1928-09-13'),   -- ID 6
('Pirandello',   'Luigi',      '1867-06-28', '1936-12-10'),   -- ID 7
('Buzzati',      'Dino',       '1906-10-16', '1972-01-28'),   -- ID 8
('Fenoglio',     'Beppe',      '1922-03-01', '1963-02-18'),   -- ID 9
('Sciascia',     'Leonardo',   '1921-01-08', '1989-11-20'),   -- ID 10
('Ginzburg',     'Natalia',    '1916-07-14', '1991-10-07'),   -- ID 11
('Levi',         'Primo',      '1919-07-31', '1987-04-11'),   -- ID 12
('Tamaro',       'Susanna',    '1957-09-12', NULL),           -- ID 13
('Baricco',      'Alessandro', '1958-01-25', NULL),           -- ID 14
('Camilleri',    'Andrea',     '1925-09-06', '2019-07-17'),   -- ID 15
('De Carlo',     'Andrea',     '1952-04-11', NULL),           -- ID 16
('Ammaniti',     'Niccolò',   '1966-09-25', NULL),           -- ID 17
('Saviano',      'Roberto',    '1979-09-22', NULL),           -- ID 18
('Ferrante',     'Elena',      '1943-01-01', NULL),           -- ID 19
('Veronesi',     'Sandro',    '1959-06-08', NULL),           -- ID 20
('Austen',       'Jane',       '1775-12-16', '1817-07-18'),   -- ID 21
('Dickens',      'Charles',    '1812-02-07', '1870-06-09'),   -- ID 22
('Tolstoj',      'Lev',        '1828-09-09', '1910-11-20'),   -- ID 23
('Dostoevskij',  'Fëdor',     '1821-11-11', '1881-02-09'),   -- ID 24
('Flaubert',     'Gustave',    '1821-12-12', '1880-05-08'),   -- ID 25
('Zola',         'Emile',      '1840-04-02', '1902-09-29'),   -- ID 26
('Hugo',         'Victor',     '1802-02-26', '1885-05-22'),   -- ID 27
('Balzac',       'Honore de',  '1799-05-20', '1850-08-18'),   -- ID 28
('Stendhal',     NULL,         '1783-01-23', '1842-03-23'),   -- ID 29
('Proust',       'Marcel',     '1871-07-10', '1922-11-18'),   -- ID 30
('Kafka',        'Franz',      '1883-07-03', '1924-06-03'),   -- ID 31
('Joyce',        'James',      '1882-02-02', '1941-01-13'),   -- ID 32
('Woolf',        'Virginia',   '1882-01-25', '1941-03-28'),   -- ID 33
('Hemingway',    'Ernest',     '1899-07-21', '1961-07-02'),   -- ID 34
('Faulkner',     'William',    '1897-09-25', '1962-07-06'),   -- ID 35
('Fitzgerald',   'Francis Scott','1896-09-24','1940-12-21'), -- ID 36
('Steinbeck',    'John',       '1902-02-27', '1968-12-20'),   -- ID 37
('Orwell',       'George',     '1903-06-25', '1950-01-21'),   -- ID 38
('Huxley',       'Aldous',     '1894-07-26', '1963-11-22'),   -- ID 39
('Garcia Marquez','Gabriel',   '1927-03-06', '2014-04-17'),   -- ID 40
('Borges',       'Jorge Luis', '1899-08-24', '1986-06-14'),   -- ID 41
('Vargas Llosa', 'Mario',      '1936-03-28', NULL),           -- ID 42
('Cortazar',     'Julio',      '1914-08-26', '1984-02-12'),   -- ID 43
('Neruda',       'Pablo',      '1904-07-12', '1973-09-23'),   -- ID 44
('Camus',        'Albert',     '1913-11-07', '1960-01-04'),   -- ID 45
('Sartre',       'Jean-Paul',  '1905-06-21', '1980-04-15'),   -- ID 46
('de Beauvoir',  'Simone',     '1908-01-09', '1986-04-14'),   -- ID 47
('Mann',         'Thomas',     '1875-06-06', '1955-08-12'),   -- ID 48
('Grass',        'Gunter',     '1927-10-16', '2015-04-13'),   -- ID 49
('Hesse',        'Hermann',    '1877-07-02', '1962-08-09'),   -- ID 50
('Dostoevskij',  'Anna',       '1846-08-30', '1918-06-09'),   -- ID 51
('Nabokov',      'Vladimir',   '1899-04-22', '1977-07-02'),   -- ID 52
('Bulgakov',     'Michail',    '1891-05-15', '1940-03-10'),   -- ID 53
('Solzenicyn',   'Aleksandr',  '1918-12-11', '2008-08-03'),   -- ID 54
('Ishiguro',     'Kazuo',      '1954-11-08', NULL),           -- ID 55
('Murakami',     'Haruki',     '1949-01-12', NULL),           -- ID 56
('Coetzee',      'John Maxwell','1940-02-09', NULL),          -- ID 57
('Morrison',     'Toni',       '1931-02-18', '2019-08-05'),   -- ID 58
('Roth',         'Philip',     '1933-03-19', '2018-05-22'),   -- ID 59
('DeLillo',      'Don',        '1936-11-20', NULL);           -- ID 60

-- ------------------------------------------------------------
--  ROMANZI
--  ATTENZIONE: l'ordine degli INSERT degli autori sopra
--  determina gli ID - MySQL assegna 1,2,3... in sequenza
-- ------------------------------------------------------------
INSERT INTO ROMANZO (ANNO_PUBBLICAZIONE, AUTORE) VALUES
('1827-01-01', 1),   -- I promessi sposi           - Manzoni
('1952-01-01', 2),   -- Il visconte dimezzato      - Calvino
('1957-01-01', 2),   -- Il barone rampante         - Calvino
('1959-01-01', 2),   -- Il cavaliere inesistente   - Calvino
('1979-01-01', 2),   -- Se una notte d'inverno     - Calvino
('1980-01-01', 3),   -- Il nome della rosa         - Eco
('1988-01-01', 3),   -- Il pendolo di Foucault     - Eco
('1994-01-01', 3),   -- L'isola del giorno prima   - Eco
('1944-01-01', 4),   -- Agostino                   - Moravia
('1954-01-01', 4),   -- Il disprezzo               - Moravia
('1941-01-01', 5),   -- Paesi tuoi                 - Pavese
('1949-01-01', 5),   -- La luna e i falo           - Pavese
('1923-01-01', 6),   -- La coscienza di Zeno       - Svevo
('1904-01-01', 6),   -- Senilita                   - Svevo
('1926-01-01', 7),   -- Uno, nessuno e centomila   - Pirandello
('1916-01-01', 7),   -- Il fu Mattia Pascal        - Pirandello
('1940-01-01', 8),   -- Il deserto dei Tartari     - Buzzati
('1960-01-01', 8),   -- Il grande ritratto         - Buzzati
('1959-01-01', 9),   -- Una questione privata      - Fenoglio
('1963-01-01', 9),   -- Il partigiano Johnny       - Fenoglio
('1961-01-01', 10),  -- Il giorno della civetta    - Sciascia
('1971-01-01', 10),  -- Il contesto                - Sciascia
('1952-01-01', 11),  -- Tutti i nostri ieri        - Ginzburg
('1963-01-01', 11),  -- Lessico famigliare         - Ginzburg
('1947-01-01', 12),  -- Se questo e un uomo        - Levi
('1963-01-01', 12),  -- La tregua                  - Levi
('1994-01-01', 13),  -- Va dove ti porta il cuore  - Tamaro
('1993-01-01', 14),  -- Oceano mare                - Baricco
('1991-01-01', 14),  -- Castelli di rabbia         - Baricco
('1994-01-01', 15),  -- La forma dell acqua        - Camilleri
('1996-01-01', 15),  -- Il cane di terracotta      - Camilleri
('1999-01-01', 15),  -- La voce del violino        - Camilleri
('1985-01-01', 16),  -- Treno di panna             - De Carlo
('1989-01-01', 16),  -- Uccelli da gabbia          - De Carlo
('2001-01-01', 17),  -- Io non ho paura            - Ammaniti
('2006-01-01', 18),  -- Gomorra                    - Saviano
('2011-01-01', 19),  -- L amica geniale            - Ferrante
('2012-01-01', 19),  -- Storia del nuovo cognome   - Ferrante
('2013-01-01', 20),  -- Il colibri                 - Veronesi
('2006-01-01', 20),  -- Caos calmo                 - Veronesi
('1813-01-01', 21),  -- Orgoglio e pregiudizio     - Austen
('1815-01-01', 21),  -- Emma                       - Austen
('1811-01-01', 21),  -- Senso e sensibilita        - Austen
('1837-01-01', 22),  -- Oliver Twist               - Dickens
('1859-01-01', 22),  -- Storia di due citta        - Dickens
('1860-01-01', 22),  -- Grandi speranze            - Dickens
('1869-01-01', 23),  -- Guerra e pace              - Tolstoj
('1877-01-01', 23),  -- Anna Karenina              - Tolstoj
('1866-01-01', 24),  -- Delitto e castigo          - Dostoevskij
('1880-01-01', 24),  -- I fratelli Karamazov       - Dostoevskij
('1869-01-01', 24),  -- L idiota                   - Dostoevskij
('1857-01-01', 25),  -- Madame Bovary              - Flaubert
('1871-01-01', 26),  -- Il ventre di Parigi        - Zola
('1885-01-01', 26),  -- Germinale                  - Zola
('1862-01-01', 27),  -- I miserabili               - Hugo
('1831-01-01', 27),  -- Notre-Dame de Paris        - Hugo
('1834-01-01', 28),  -- Papa Goriot                - Balzac
('1830-01-01', 29),  -- Il rosso e il nero         - Stendhal
('1839-01-01', 29),  -- La certosa di Parma        - Stendhal
('1913-01-01', 30),  -- Dalla parte di Swann       - Proust
('1927-01-01', 30),  -- Il tempo ritrovato         - Proust
('1915-01-01', 31),  -- La metamorfosi             - Kafka
('1925-01-01', 31),  -- Il processo                - Kafka
('1922-01-01', 32),  -- Ulisse                     - Joyce
('1927-01-01', 33),  -- Gita al faro               - Woolf
('1941-01-01', 33),  -- Le onde                    - Woolf
('1929-01-01', 34),  -- Addio alle armi            - Hemingway
('1940-01-01', 34),  -- Per chi suona la campana   - Hemingway
('1929-01-01', 35),  -- L urlo e il furore         - Faulkner
('1925-01-01', 36),  -- Il grande Gatsby           - Fitzgerald
('1939-01-01', 37),  -- Furore                     - Steinbeck
('1937-01-01', 37),  -- Uomini e topi              - Steinbeck
('1945-01-01', 38),  -- La fattoria degli animali  - Orwell
('1949-01-01', 38),  -- 1984                       - Orwell
('1932-01-01', 39),  -- Il mondo nuovo             - Huxley
('1967-01-01', 40),  -- Cent anni di solitudine    - Garcia Marquez
('1985-01-01', 40),  -- L amore ai tempi del colera- Garcia Marquez
('1944-01-01', 41),  -- Finzioni                   - Borges
('1952-01-01', 41),  -- L Aleph                    - Borges
('1963-01-01', 42),  -- La citta e i cani          - Vargas Llosa
('1966-01-01', 42),  -- La casa verde              - Vargas Llosa
('1963-01-01', 43),  -- Rayuela                    - Cortazar
('1942-01-01', 45),  -- Lo straniero               - Camus
('1947-01-01', 45),  -- La peste                   - Camus
('1938-01-01', 46),  -- La nausea                  - Sartre
('1949-01-01', 47),  -- Il secondo sesso           - de Beauvoir
('1901-01-01', 48),  -- I Buddenbrook              - Mann
('1924-01-01', 48),  -- La montagna incantata      - Mann
('1959-01-01', 49),  -- Il tamburo di latta        - Grass
('1927-01-01', 50),  -- Il lupo della steppa       - Hesse
('1919-01-01', 50),  -- Demian                     - Hesse
('1955-01-01', 52),  -- Lolita                     - Nabokov
('1940-01-01', 53),  -- Il maestro e Margherita    - Bulgakov
('1962-01-01', 54),  -- Una giornata di Ivan D.    - Solzenicyn
('2005-01-01', 55),  -- Non lasciarmi              - Ishiguro
('1989-01-01', 56),  -- Norwegian Wood             - Murakami
('1994-01-01', 56),  -- L uccello che girava...    - Murakami
('1983-01-01', 57),  -- Vita e tempi di Michael K  - Coetzee
('1987-01-01', 58),  -- Beloved                    - Morrison
('1997-01-01', 59);  -- Pastorale americana        - Roth