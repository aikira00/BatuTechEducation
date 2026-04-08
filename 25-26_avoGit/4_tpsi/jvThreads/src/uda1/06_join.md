**Esercizio 1 — Join e operazioni aritmetiche**

**Obiettivo**: utilizzare i thread per eseguire operazioni matematiche in parallelo e il metodo `join()` per attendere i risultati prima di stamparli.

**Descrizione**

Il programma chiede all'utente due numeri interi da tastiera, quindi crea e avvia quattro thread, ciascuno responsabile di una sola operazione: somma, sottrazione, moltiplicazione e divisione. Ogni thread memorizza il risultato come variabile d'istanza. Il `main` attende la terminazione di tutti i thread con `join()`, poi stampa i quattro risultati.

**Requisiti tecnici**

1. Creare una classe `OperationThread` che implementa `Runnable`. Il costruttore riceve i due operandi e un carattere (o stringa) che identifica l'operazione (`+`, `-`, `*`, `/`).
2. Nel metodo `run()`, eseguire l'operazione e salvare il risultato in una variabile d'istanza accessibile tramite getter.
3. Nel `main`, leggere i due numeri con `BufferedReader` (vedi codice fornito sotto), creare i quattro thread e avviarli.
4. Utilizzare `join()` su ciascun thread prima di stampare i risultati.
5. Gestire il caso di divisione per zero con un messaggio appropriato.

**Codice fornito per la lettura da tastiera**

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
try {
    System.out.print("Inserisci il primo numero intero: ");
    int numero1 = Integer.parseInt(reader.readLine());

    System.out.print("Inserisci il secondo numero intero: ");
    int numero2 = Integer.parseInt(reader.readLine());

} catch (IOException e) {
    e.printStackTrace();
} catch (NumberFormatException e) {
    System.out.println("Errore: devi inserire un numero intero.");
}
```

**Esempio di output atteso**

```
Inserisci il primo numero intero: 12
Inserisci il secondo numero intero: 4

[Somma]           12 + 4 = 16
[Sottrazione]     12 - 4 = 8
[Moltiplicazione] 12 * 4 = 48
[Divisione]       12 / 4 = 3
```

**Consegna**: codice sorgente (.java) e screenshot dell'esecuzione.

**Domanda di riflessione** — In questo esercizio i thread sono davvero utili? Quale sarebbe uno scenario in cui la parallelizzazione delle operazioni darebbe un vantaggio concreto?
