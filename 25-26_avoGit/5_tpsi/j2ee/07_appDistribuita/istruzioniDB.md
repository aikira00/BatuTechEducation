DROP DATABASE IF EXISTS tpsi5_usersdb;
CREATE DATABASE usersdb;
Use usersdb;

CREATE TABLE users (
username VARCHAR(10) NOT NULL PRIMARY KEY,
password VARCHAR(64) NOT NULL,
role VARCHAR (5) NOT NULL CHECK (role IN ('user', 'admin'))
);

INSERT INTO users VALUES ('pippo', SHA2('pippo',256), 'user'),
('pluto', SHA2('pluto',256), 'admin'),
('paperino', SHA2('paperino',256), 'user'),
('topolino', SHA2('topolino',256), 'admin');

create user 'gfuser'@'localhost' identified by 'gfuser';
grant all privileges on usersdb.* to 'gfuser'@'localhost';

Creare il DbServer con il metodo per l'autenticazione
-- inserire in autdomain/lib il file mysql-connector-j-8.0.33.jar

Creare il connection poll e la risorsa jdbc come riportato in precedente materiale da utilizzare per l'autenticazione
Nell'endpoint
del servizio di autenticazione scrivere un metodo di verifica delle
credenziali che lavora sul post e avra come @FormParam username e
password. Un secondo metodo che lavora sul get che avra come
@HeaderParam il token inviato.
Nella classe dichiarare una lista(statica)  di integer che conterrà i token generati e un oggetto della classe Random per generare i token

Nel
metodo di autenticazione se l'utente viene autenticato si genera un
token compreso fra 1000000 e 2000000, si verifica che non sia già
presente e lo si inserisce nella lista e si reinvia.
Nel caso di verifica si limitaa controllare che esista nella lista.
Il
parametro @HeaderParam("Authentication") e composto dalla stringa
"Bearer token" quindi per ottenere il token bisogna prendere la sola
sottostringa contente il token