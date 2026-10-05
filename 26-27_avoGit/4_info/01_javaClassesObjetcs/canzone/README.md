# Canzone — il jukebox dell'intervallo

Il codice di *Dallo scenario al codice: il jukebox dell'intervallo* (`BatuEdu/INFO_4/esercitazioni/08_DalloScenarioAlCodice_Canzone.md`).

| Package | Cosa c'è |
|---|---|
| `catalogoArray` | `Canzone`, progettata dalla mail dei rappresentanti: nessun setter, gli ascolti crescono solo con `play()`, il costruttore mette in «altro» i generi sconosciuti e a 3:00 le durate non valide, il costruttore breve è per gli strumentali. `RadioIntervallo`: il catalogo è un array di `Canzone` caricato nel `main`, con il contatore `quante`, e un menu (elenco, cerca per titolo, cerca per cantante, suona, a caso per genere). Il caso si estrae fra le sole canzoni del genere, raccolte in un secondo array riempito a metà. Si confrontano stringhe e interi, mai canzoni. |

Il package si chiama `catalogoArray` perché è il passo prima della `Playlist`: le canzoni stanno ancora in un array nel `main`, e l'aggregazione arriva dopo. In IntelliJ la cartella `canzone` va segnata come cartella sorgente (*Mark Directory as → Sources Root*).

Codice compilato ed eseguito con `javac --release 17` il 02/10/2026.
