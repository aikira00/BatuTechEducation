# Ascensore — incapsulamento, costruttori, overloading

Si riparte dallo **scenario B** dell'esercitazione *Siete il compilatore* (`BatuEdu/INFO_4/esercitazioni/02_CorreggiCodice.md`). Due passi, un package per passo: si eseguono uno dopo l'altro e si confronta l'output.

| Passo | Package | Cosa si vede |
|---|---|---|
| 1 | `edu.avo.aperto` | Lo scenario B corretto nel secondo modo: si aggiunge `apriPorte()`. Con le variabili aperte e nessun limite l'ascensore scende al piano −2, sale al 42, e ha capienza 0. |
| 2 | `edu.avo.conOverloading` | L'ascensore conosce i suoi limiti. Costruttori in overloading: `Ascensore(ultimoPiano, capienza)` e `Ascensore(ultimoPiano)`, che chiama il primo con `this(ultimoPiano, 4)`. Metodi in overloading: `sali()` e `sali(int piani)`, `scendi()` e `scendi(int piani)`. |

Codice compilato ed eseguito con `javac --release 17 -Xlint:all` il 25/09/2026.

## Alla LIM: la versione che non compila

In `daCorreggere/AscensoreTestDrive.java` c'è lo scenario B **esattamente com'è nell'esercitazione**, con le due classi nello stesso file. Sta fuori da `src/` apposta: dentro, IntelliJ si rifiuterebbe di compilare tutto il progetto.

Si compila dal terminale, come alla lezione 0:

```
cd daCorreggere
javac AscensoreTestDrive.java
```

`javac` dà **1 errore**: `cannot find symbol … method apriPorte() … location: variable a of type Ascensore`. Da leggere insieme riga per riga: il compilatore dice **cosa** manca (un metodo), **su chi** è stato chiamato (`a`) e **di che tipo** è (`Ascensore`), quindi dove andare a cercare.

Poi le due correzioni, una dopo l'altra, eseguendo ogni volta con `java AscensoreTestDrive`:

1. togliere la riga `a.apriPorte();` → `Salgo al piano 4`;
2. rimetterla e aggiungere il metodo alla classe → `Porte aperte al piano 3` e `Salgo al piano 4`.

Quale delle due è giusta dipende da cosa si voleva: se un ascensore deve saper aprire le porte, togliere la riga fa compilare ma nasconde il problema. Da qui si passa al passo 1, che è la correzione 2.

I `.class` generati non finiscono nel repo: sono nel `.gitignore`. Dopo la lezione, però, il file va **rimesso com'era**, per l'anno dopo (`git checkout daCorreggere/`).

## Passo 2: dove guardare

- **`sali()` chiama `sali(1)`**: i controlli stanno in un posto solo, la versione senza parametri non ripete niente.
- **`sali(int piani)` controlla prima di muoversi**: `sali(4)` dal piano 4 viene rifiutato tutto, non si ferma a metà.
- **L'ascensore decide da solo**: non si muove con le porte aperte, non fa entrare la quinta persona, non va sotto il piano 0. Nel `main` non c'è nessun `if`.
- **`capienza` e `ultimoPiano` non hanno setter**: si fissano nel costruttore e non cambiano.
- **`toString()`** riassume tutto lo stato in una riga: `Ascensore al piano 4/5, 3/4 persone, porte chiuse`.

## Domande per la classe

1. Al passo 1, chi dovrebbe impedire `scendi()` dal piano 0? Il `main` o l'ascensore?
2. Al passo 2, perché non c'è `setPiano(int)`? Cosa succederebbe alle porte e ai limiti?
3. `new Ascensore(5)` e `new Ascensore(8, 12)`: quale costruttore esegue Java in ciascun caso? Quale codice viene eseguito davvero, nel primo?
4. Si potrebbe aggiungere un terzo `sali(String destinazione)`? E un `int sali()` che restituisce il piano? (Il primo sì: tipo del parametro diverso. Il secondo no: la firma è la stessa di `sali()`, il tipo restituito non conta.)
5. Scrivere `vaiAlPiano(int piano)` usando `sali(int)` e `scendi(int)`. Serve `this` da qualche parte?
