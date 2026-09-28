# Recensione — l'aggancio per `this`

Si riparte dalla soluzione dell'esercitazione *Tre recensioni, una classe* (`BatuEdu/INFO_4/esercitazioni/01_UnaClasseMoltiOggetti_sol.md`). Cinque passi, un package per passo. Ogni package ha la sua classe `Recensione` e il suo `RecensioneTestDrive`: si eseguono uno dopo l'altro e si confronta l'output.

| Passo | Package | Cosa si vede |
|---|---|---|
| 1 | `avo.nomiDiversi` | La soluzione dell'esercitazione: `setStelle(int s)`. Funziona, ma dalla firma non si capisce cosa sia `s`. |
| 2 | `avo.ombra` | Parametri con nomi chiari: `setStelle(int stelle)` e `stelle = stelle`. **Compila senza errori e senza avvisi**, ma l'oggetto non cambia mai: tutte e tre le recensioni stampano `null ha dato 0 stelle a null`. |
| 3 | `avo.conThis` | `this.stelle = stelle`. Stesso `main` dei passi 1 e 2, stesso output del passo 1. |
| 4 | `avo.chiEThis` | Chi è `this`? `piuGenerosaDi(Recensione altra)` confronta **due** recensioni: `r2.piuGenerosaDi(r4)` e `r4.piuGenerosaDi(r2)` eseguono lo stesso codice, ma `this` è un oggetto diverso. |
| 5 | `avo.conCostruttore` | La soluzione delle slide *this, toString, costruttori, overloading* (sezioni 1-3): costruttore `Recensione(locale, autore, stelle)` che per le stelle riusa `setStelle`, e `toString()` con `@Override`. `System.out.println(r2)` stampa `samir.k ha dato 4 stelle a Trattoria da Gino`. |

Codice compilato ed eseguito con `javac --release 17 -Xlint:all` il 25/09/2026 (passi 1-4) e il 26/09/2026 (passo 5). Nei passi 1-4 nessun avviso, nemmeno al passo 2; al passo 5 uno solo, spiegato più sotto. IntelliJ invece sottolinea `stelle = stelle` in giallo (*variable is assigned to itself*): vale la pena farlo notare, leggere gli avvisi dell'IDE.

## Passo 2: perché è l'errore giusto da far vedere

- **È silenzioso.** Nessun errore di compilazione, nessuna eccezione: il programma gira e stampa cose sbagliate.
- **Il controllo funziona e l'assegnazione no.** I voti 0 e 11 vengono rifiutati correttamente, perché l'`if` legge il parametro. Ma anche il 4 va perso: `stelle = stelle` copia il parametro in se stesso.
- **`pubblica()` non ha colpa.** Lì non c'è nessun parametro, quindi `autore` è la variabile d'istanza, che nessuno ha mai assegnato: `null`, e `0` per le stelle.

La regola che ne esce: **dentro un metodo vince la variabile più vicina.** Se un parametro ha lo stesso nome di una variabile d'istanza, la copre. `this.` serve a dire «voglio quella dell'oggetto».

## Passo 4: il ponte con la lezione 0

In C avremmo scritto `piuGenerosaDi(&r2, &r4)`, passando entrambe le recensioni. In Java la prima si scrive **prima del punto**: `r2.piuGenerosaDi(r4)`. Dentro il metodo quella recensione non ha un nome di parametro: si chiama `this`.

Attenzione a una sorpresa: `altra.stelle` è `private`, eppure si legge. `private` vuol dire *privato della classe*, non *del singolo oggetto*: il codice di `Recensione` può leggere le variabili private di qualunque `Recensione`.

## Domande per la classe

1. Al passo 2 il compilatore non protesta. Perché? (Per lui `stelle = stelle` è un'assegnazione valida: `int` in `int`.)
2. Al passo 2, perché 0 e 11 vengono comunque rifiutati, se il setter non funziona?
3. Al passo 3, in `pubblica()` non c'è `this`. Perché lì non serve? Sarebbe sbagliato scrivere `this.autore`?
4. Al passo 4, con `r2.piuGenerosaDi(r4)`, chi è `this` e chi è `altra`? E con `r4.piuGenerosaDi(r2)`?
5. Al passo 4, `altra.stelle` è `private`. Perché si può leggere?

## Passo 5: il costruttore, come nelle slide

Il `main` usa gli stessi dati delle slide: `samir.k` alla Trattoria da Gino con 4 stelle, `giulia.b` alla Gelateria Aurora con 5.

- **Una riga sola**: `new Recensione("Trattoria da Gino", "samir.k", 4)` e l'oggetto nasce già con i suoi dati.
- **`setStelle(stelle)` nel costruttore**: il controllo sta in un posto solo. Se domani le stelle vanno da 1 a 10, si cambia solo il setter.
- **Il costruttore di default è sparito**: la riga `new Recensione()` nel `main` è commentata. Si toglie il commento e `javac` dà l'errore della slide 21 (*required: String,String,int, found: no arguments*), poi si rimette il commento.
- **`println(r2)` usa `toString()`**: prima del passo 5 stampava qualcosa come `avo.chiEThis.Recensione@1b6d3586`.

**Il limite, da tenere nelle note e non nelle slide**: `new Recensione("Pizzeria Vesuvio", "marta_07", 0)` stampa «Rifiutato», ma la recensione **nasce lo stesso**, con 0 stelle. Il costruttore non può rifiutarsi di creare l'oggetto: per quello servono le **eccezioni**. Nel `main` c'è apposta, in fondo, se la domanda viene fuori.

**Un avviso da non aprire in classe**: con `javac -Xlint:all` (JDK 21 o successivo) la chiamata a `setStelle` nel costruttore dà *possible 'this' escape before subclass is fully initialized*. Senza `-Xlint` non compare. Riguarda le sottoclassi, che fanno parte dell'ereditarietà: per adesso chiamare il setter dal costruttore va bene così.
