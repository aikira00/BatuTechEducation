# Sveglia — costruttori, `this(...)`, overloading, `toString`

Si riparte dallo **scenario A** dell'esercitazione *Siete il compilatore* (`BatuEdu/INFO_4/esercitazioni/02_CorreggiCodice.md`) e si arriva alla soluzione di *Siete il compilatore, bis* (`02_CorreggiCodice_bis.md`), poi al `main` interattivo della parte 3 (`02_02_mainInterattivo.md`). Quattro passi, un package per passo: si eseguono uno dopo l'altro e si confronta l'output.

| Passo | Package | Cosa si vede |
|---|---|---|
| 1 | `senzaCostruttore` | Lo scenario A corretto (manca `Sveglia s = new Sveglia();`). Prima delle assegnazioni la sveglia stampa `Sveglia alle 0:0 (null)`: esiste, ma nessuno le ha detto a che ora suonare. E con le variabili aperte `s.ora = 25` passa. |
| 2 | `conCostruttori` | Due costruttori: `Sveglia(ora, minuti)` e `Sveglia(ora, minuti, etichetta)`. Il secondo chiama il primo con `this(ora, minuti)`, come nelle slide 35-36. Getter e setter come nella 02 Parte 2, ma il costruttore non chiama i setter. Si stampa ancora con `mostra()`. |
| 3 | `conToStringBis` | La soluzione del bis: setter con `this`, costruttore breve che chiama quello completo (che non usa i setter), `toString()` al posto di `mostra()`, `posticipa()` e `posticipa(int minuti)`. Il `main` è quello della *Verifica*, e l'output è quello atteso. |
| 4 | `mainInterattivo` | La parte 3: `MainInterattivo`, un `main` con `Scanner` che fa inserire le sveglie in un ciclo e poi offre un menu (elenco, cerca, modifica, posticipa, che ore sono). Modifica e posticipa stampano la sveglia prima e dopo; un input non numerico stampa un errore e il programma riprende: niente `try`/`catch`, le cifre si controllano con un ciclo su `charAt`, e menu e numero della sveglia si confrontano come stringhe con `equals`. La Sveglia è quella del passo 3, senza modifiche. |

Codice compilato ed eseguito con `javac --release 17` il 01/10/2026.

## Alla LIM: la versione che non compila

In `daCorreggere/SvegliaTestDrive.java` c'è lo scenario A **esattamente com'è nell'esercitazione**, con le due classi nello stesso file. In IntelliJ la cartella è esclusa dai sorgenti apposta: dentro, si rifiuterebbe di compilare tutto il progetto.

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

- **`this(ora, minuti)`** deve essere la **prima istruzione** del costruttore. Il controllo sull'orario sta in un posto solo. Vale sui JDK 17 e 21 del laboratorio e sul 23 di IntelliJ; dal JDK 25 le istruzioni prima di `this(...)` sono ammesse.
- **Il costruttore di default è sparito.** Nel `main` c'è la riga `new Sveglia()` commentata: si toglie il commento, si fa vedere l'errore, si rimette il commento.
- **Il costruttore non può rifiutare**: con `25:75` la sveglia nasce lo stesso, alle 7:00. Impedire la creazione richiede le eccezioni.
- **Setter e costruttore reagiscono in modo diverso**: in fondo al `main`, `setOra(25)` e `setMinuti(75)` vengono rifiutati e la sveglia resta alle 7:30, com'era. Il costruttore invece, con `25:75`, aveva messo le 7:00: alla nascita non c'è un «com'era».

## Passo 3: dove guardare

- **Il costruttore breve chiama quello completo**: `this(ora, minuti, "senza etichetta")`. È la direzione opposta al passo 2 e alle slide, e quella più usata: ogni attributo si assegna una volta sola, nel costruttore completo. Al passo 2 invece l'etichetta prende `"senza etichetta"` e subito dopo viene sovrascritta.
- **Il costruttore non chiama i setter.** Controlla l'orario da solo e, se è impossibile, imposta le 7:00 e lo dice, come al passo 2. Con i setter, che rifiutano e lasciano l'attributo com'era, una sveglia `25:75` nascerebbe alle `0:00`: lo zero di partenza degli `int`, un orario che nessuno ha scelto. Il motivo più profondo (un costruttore non deve chiamare metodi che una sottoclasse può ridefinire) arriva con l'ereditarietà.
- **`toString()` con `@Override`** al posto di `mostra()`, con i minuti sempre a due cifre: `7:05`, non `7:5`.
- **`posticipa()` chiama `posticipa(5)`**: stessa idea di `this(...)`, la versione breve riusa quella completa.
- **`this.minuti` in `posticipa(int minuti)`** è obbligatorio: il parametro ha lo stesso nome della variabile d'istanza. È il caso della Recensione, ma dentro un metodo qualunque, non in un setter.
- **Mezzanotte**: `23:50` posticipata di 15 minuti diventa `0:05`. Il calcolo passa per i minuti totali.

## Domande per la classe

1. Al passo 1, perché `ora` vale `0` ed `etichetta` vale `null`, se nessuno li ha assegnati?
2. Perché `new Sveglia()` non compila più, se al passo 1 compilava?
3. Al passo 3, cosa stampa `System.out.println(scuola)` se si cancella `toString()`?
4. `posticipa()` e `posticipa(20)`: come fa Java a sapere quale dei due eseguire?
5. In `posticipa(int minuti)`, cosa succede se si scrive `minuti = totale % 60;` invece di `this.minuti = totale % 60;`?
