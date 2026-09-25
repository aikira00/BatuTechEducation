# Cane — private, public, incapsulamento, information hiding

Quattro passi, un package per passo. Ogni package ha la sua classe `Cane` e il suo `main`: si eseguono uno dopo l'altro e si confronta l'output.

| Passo | Package | `main` da eseguire | Cosa si vede |
|---|---|---|---|
| 1 | `edu.avo` | `CaneTestDrive` | Variabili aperte: `cane.eta = -7` compila e gira. Nessuno difende lo stato. |
| 2 | `edu.avo.attrPriv` | `CaneTestDriverPriv` | Variabili `private`: da fuori non si toccano più (errore *nome has private access in Cane*). Però non si possono nemmeno impostare: il cane resta `null`. |
| 3 | `edu.avo.getSet` | `CaneTestDriverGetSet` | **Incapsulamento**: getter e setter sono la porta controllata. I setter rifiutano −7, `"gigantissima"` e il nome vuoto. |
| 4 | `edu.avo.infoHiding` | `CaneTestDriveInfoHiding` | **Information hiding**: dentro `Cane` l'età non c'è più, c'è l'anno di nascita. Il `main` è identico al passo 3 e l'output è identico. |

Codice compilato ed eseguito con `javac --release 17` il 21/09/2026.

## Passo 2: l'errore da mostrare

Nel `main` del passo 2 la riga `cane.nome = "Fufi";` è **commentata apposta**. In classe: si toglie il commento, si fa vedere l'errore, si rimette il commento. Se resta scommentata, IntelliJ non compila il progetto e non esegue nemmeno gli altri `main`.

## Incapsulamento e information hiding: la differenza

- **Incapsulamento** (passo 3): dati e metodi che li usano stanno nella stessa classe, e lo stato si cambia solo dai metodi. La classe può **controllare** i valori.
- **Information hiding** (passo 4): chi usa la classe conosce solo i metodi `public`, non come è fatta dentro. La classe può **cambiare** la sua rappresentazione interna senza rompere il codice che la usa.

Il passo 4 lo dimostra: confrontate `getSet/Cane.java` e `infoHiding/Cane.java`. Cambiano la variabile e il corpo di `getEta()`, `setEta()` e `presentati()`. I due `main` invece sono identici, a parte il package.

## Domande per la classe

1. Al passo 1, cosa stampa `presentati()` dopo le assurdità? Il compilatore ha protestato?
2. Al passo 2 le variabili sono protette. Perché questo da solo non basta?
3. Al passo 3, un setter che fa solo `this.nome = nome` sarebbe utile? Cosa cambia rispetto a una variabile `public`?
4. `setRazza` non controlla niente. Serve lo stesso? (Sì: se un domani si vorrà un controllo, basterà aggiungerlo lì, senza cambiare chi usa la classe.)
5. Al passo 4, se `eta` fosse stata `public` e qualcuno avesse scritto `cane.eta = 4` nel suo `main`, si sarebbe potuta cambiare la rappresentazione interna?
