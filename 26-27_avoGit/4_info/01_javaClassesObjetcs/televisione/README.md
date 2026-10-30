# Televisione — `main` interattivo

Il codice di *Dal diagramma al codice: la televisione, parte 2* (`BatuEdu/INFO_4/esercitazioni/06_01_mainInterattivo.md`).

| Package | Cosa c'è |
|---|---|
| `mainInterattivoTv` | La `Televisione` della soluzione della 06, senza modifiche, e `MainInterattivo`: si inseriscono i televisori in un ciclo, poi un menu (elenco, cerca per marca, accendi o spegni, cambia canale, volume, Partita!). Un input non numerico stampa un errore e il programma riprende: niente `try`/`catch`, le cifre si controllano con un ciclo su `charAt`. Si confrontano stringhe e interi, mai televisori. |

Il package si chiama `mainInterattivoTv`, e non `mainInterattivo`, per non scontrarsi con quello della Sveglia se le due cartelle stanno nello stesso modulo IntelliJ. In IntelliJ la cartella `televisione` va segnata come cartella sorgente (*Mark Directory as → Sources Root*).

Codice compilato ed eseguito con `javac --release 17` il 01/10/2026. `Televisione` rivista l'08/10/2026: `alzaVolume()` chiama `alzaVolume(1)`, e il tetto a 100 si controlla prima di sommare. Ricompilata, stesso output.

## Versione PEI: i file di partenza

In `pei/` ci sono `Televisione.java` e `TelevisioneTestDrive.java` della scheda PEI (`BatuEdu/INFO_4/esercitazioni/PEI/06_DaUMLaCodice_Televisione_PEI.md`). Sono uguali al codice della scheda: si danno allo studente così come sono.

- Lo scheletro compila subito e stampa le prime due righe.
- I metodi da scrivere sono segnati `// PASSO 3`, `// PASSO 4`, `// PASSO 5`.
- Nel `main`, i commenti `// passo 3`, `// passo 4`, `// passo 5` dicono dove incollare le righe di ogni passo.

Sono **senza package**, apposta, come quelli di `files.zip`: lo studente li mette nel suo progetto senza creare cartelle. Se in IntelliJ `televisione` è segnata come cartella sorgente, `pei` va esclusa (*Mark Directory as → Excluded*), come `daCorreggere` della Sveglia. Dal terminale:

```
cd pei
javac *.java
java TelevisioneTestDrive
```

Compilati ed eseguiti con `javac --release 17` l'08/10/2026.
