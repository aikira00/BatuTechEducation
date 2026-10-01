# Televisione — `main` interattivo

Il codice di *Dal diagramma al codice: la televisione, parte 2* (`BatuEdu/INFO_4/esercitazioni/06_01_mainInterattivo.md`).

| Package | Cosa c'è |
|---|---|
| `mainInterattivoTv` | La `Televisione` della soluzione della 06, senza modifiche, e `MainInterattivo`: si inseriscono i televisori in un ciclo, poi un menu (elenco, cerca per marca, accendi o spegni, cambia canale, volume, Partita!). Un input non numerico stampa un errore e il programma riprende: niente `try`/`catch`, le cifre si controllano con un ciclo su `charAt`. Si confrontano stringhe e interi, mai televisori. |

Il package si chiama `mainInterattivoTv`, e non `mainInterattivo`, per non scontrarsi con quello della Sveglia se le due cartelle stanno nello stesso modulo IntelliJ. In IntelliJ la cartella `televisione` va segnata come cartella sorgente (*Mark Directory as → Sources Root*).

Codice compilato ed eseguito con `javac --release 17` il 01/10/2026.
