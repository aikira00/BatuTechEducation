# Sveglia — costruttori, `this(...)`, overloading, `toString`

Si riparte dallo **scenario A** dell'esercitazione *Siete il compilatore* (`BatuEdu/INFO_4/esercitazioni/02_CorreggiCodice.md`). Due passi, un package per passo: si eseguono uno dopo l'altro e si confronta l'output.

| Passo | Package | Cosa si vede |
|---|---|---|
| 1 | `edu.avo.senzaCostruttore` | Lo scenario A corretto (manca `Sveglia s = new Sveglia();`). Prima delle assegnazioni la sveglia stampa `Sveglia alle 0:0 (null)`: esiste, ma nessuno le ha detto a che ora suonare. E con le variabili aperte `s.ora = 25` passa. |
| 2 | `edu.avo.conCostruttori` | Due costruttori: `Sveglia(ora, minuti)` e `Sveglia(ora, minuti, etichetta)`. Il secondo chiama il primo con `this(ora, minuti)`. Poi `toString()` e l'overloading su un metodo: `posticipa()` e `posticipa(int minuti)`. |

Codice compilato ed eseguito con `javac --release 17 -Xlint:all` il 25/09/2026.

## Alla LIM: la versione che non compila

In `daCorreggere/SvegliaTestDrive.java` c'è lo scenario A **esattamente com'è nell'esercitazione**, con le due classi nello stesso file. Sta fuori da `src/` apposta: dentro, IntelliJ si rifiuterebbe di compilare tutto il progetto.

Si compila dal terminale, come alla lezione 0:

```
cd daCorreggere
javac SvegliaTestDrive.java
```

`javac` dà **5 errori** `cannot find symbol … variable s`, uno per ogni riga che usa `s`. Da far notare:

- gli errori sono cinque, ma la correzione è **una riga sola**: `Sveglia s = new Sveglia();` all'inizio del `main`;
- nella cartella compare `Sveglia.class` ma non `SvegliaTestDrive.class`: la classe `Sveglia` è corretta, il problema sta solo nel `main`.

Si corregge davanti alla classe, si ricompila e si esegue con `java SvegliaTestDrive`. Poi si aggiunge `s.mostra();` **prima** delle assegnazioni: `Sveglia alle 0:0 (null)`. Da lì si passa al passo 1.

I `.class` generati non finiscono nel repo: sono nel `.gitignore`. Dopo la lezione, però, il file va **rimesso com'era**, per l'anno dopo (`git checkout daCorreggere/`).

## Passo 2: dove guardare

- **`this(ora, minuti)`** deve essere la **prima istruzione** del costruttore. Il controllo sull'orario sta in un posto solo.
- **Il costruttore di default è sparito.** Nel `main` c'è la riga `new Sveglia()` commentata: si toglie il commento, si fa vedere l'errore, si rimette il commento.
- **`posticipa()` chiama `posticipa(5)`**: stessa idea di `this(...)`, la versione breve riusa quella completa.
- **`this.minuti` in `posticipa(int minuti)`** è obbligatorio: il parametro ha lo stesso nome della variabile d'istanza. È il caso della Recensione, ma dentro un metodo qualunque, non in un setter.
- **Mezzanotte**: `23:50` posticipata di 15 minuti diventa `00:05`. Il calcolo passa per i minuti totali.
- **Il costruttore non può rifiutare**: con `25:75` la sveglia nasce lo stesso, alle 7:00. Impedire la creazione richiede le eccezioni.

L'alternativa, altrettanto giusta: il costruttore breve chiama quello completo, `this(ora, minuti, "sveglia")`. Così il costruttore completo fa tutto il lavoro e il breve aggiunge solo il valore predefinito.

## Domande per la classe

1. Al passo 1, perché `ripeti` vale `false` e `etichetta` vale `null`, se nessuno li ha assegnati?
2. Al passo 2, cosa stampa `System.out.println(scuola)` se si cancella `toString()`?
3. Perché `new Sveglia()` non compila più, se al passo 1 compilava?
4. `posticipa()` e `posticipa(30)`: come fa Java a sapere quale dei due eseguire?
5. In `posticipa(int minuti)`, cosa succede se si scrive `minuti = totale % 60;` invece di `this.minuti = totale % 60;`?
