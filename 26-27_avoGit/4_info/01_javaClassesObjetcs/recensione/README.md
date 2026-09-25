# Recensione — l'aggancio per `this`

Si riparte dalla soluzione dell'esercitazione *Tre recensioni, una classe* (`BatuEdu/INFO_4/esercitazioni/01_UnaClasseMoltiOggetti_sol.md`). Quattro passi, un package per passo. Ogni package ha la sua classe `Recensione` e il suo `RecensioneTestDrive`: si eseguono uno dopo l'altro e si confronta l'output.

| Passo | Package | Cosa si vede |
|---|---|---|
| 1 | `edu.avo.nomiDiversi` | La soluzione dell'esercitazione: `setStelle(int s)`. Funziona, ma dalla firma non si capisce cosa sia `s`. |
| 2 | `edu.avo.ombra` | Parametri con nomi chiari: `setStelle(int stelle)` e `stelle = stelle`. **Compila senza errori e senza avvisi**, ma l'oggetto non cambia mai: tutte e tre le recensioni stampano `null ha dato 0 stelle a null`. |
| 3 | `edu.avo.conThis` | `this.stelle = stelle`. Stesso `main` dei passi 1 e 2, stesso output del passo 1. |
| 4 | `edu.avo.chiEThis` | Chi è `this`? `piuGenerosaDi(Recensione altra)` confronta **due** recensioni: `r2.piuGenerosaDi(r4)` e `r4.piuGenerosaDi(r2)` eseguono lo stesso codice, ma `this` è un oggetto diverso. |

Codice compilato ed eseguito con `javac --release 17 -Xlint:all` il 25/09/2026: nessun avviso nemmeno al passo 2. IntelliJ invece sottolinea `stelle = stelle` in giallo (*variable is assigned to itself*): vale la pena farlo notare, leggere gli avvisi dell'IDE.

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

## Il gancio che resta aperto

I passi 1 e 3 stampano ancora `marta_07 ha dato 0 stelle`: il setter ha rifiutato lo 0, ma lo 0 c'era già, perché è il valore di default. Il setter non garantisce che la recensione nasca valida: serve il **costruttore**.
