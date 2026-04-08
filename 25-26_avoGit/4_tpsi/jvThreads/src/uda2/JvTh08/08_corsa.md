# I Corridori — Interruzione sicura di un thread

## Descrizione

Implementare un'applicazione Java che simuli due corridori che corrono e si riposano ciclicamente, ognuno fino a raggiungere una distanza prestabilita. Ogni corridore è un thread che deve essere in grado di **terminare in modo sicuro** quando viene interrotto dal thread principale.

### Input da tastiera

Il metodo `main` chiede all'utente di inserire:

- La distanza totale che i due corridori devono percorrere
- Il numero di **secondi di attesa** prima di interrompere i corridori

Usare `BufferedReader` per la lettura da tastiera.

### Thread corridore

- Alterna fasi di **corsa** e **riposo** in modo ciclico, aggiornando la distanza percorsa
- Controlla periodicamente se è stato interrotto e termina in modo sicuro se è stato interrotto.
- Al termine del metodo `run()`, stampa se è stato fermato dal main e se ha completato la corsa

### Thread main

- Avvia entrambi i thread corridori
- Attende il numero di secondi specificato dall'utente e dorme
- Interrompe i due corridori
- Attende la terminazione dei thread con `join()`
- Stampa, per ciascun corridore, se ha completato la corsa e la distanza percorsa

---

## Risorse consigliate

- `BufferedReader` — per leggere l'input dell'utente
- `Thread.join()` — per attendere la terminazione dei thread
- Variabile di osservazione — per l'interruzione sicura (vedere le slide del docente)

---

## Esempio di output

> **Nota:** poiché i due thread sono eseguiti in parallelo, l'ordine delle righe può variare ad ogni esecuzione. I valori numerici dipendono dalla distanza casuale percorsa ad ogni ciclo

```
Inserisci la distanza totale che i corridori devono percorrere: 50
Inserisci il numero di secondi prima di interrompere i corridori: 10

PRIMO: sto correndo...
SECONDO: sto correndo...
PRIMO: ho corso 17
SECONDO: ho corso 17
PRIMO: sto riposando...
SECONDO: sto riposando...
SECONDO: sto correndo...
PRIMO: sto correndo...
SECONDO: ho corso 33
SECONDO: sto riposando...
PRIMO: ho corso 32
PRIMO: sto riposando...
SECONDO: sto correndo...
SECONDO: ho corso 50
SECONDO: sto riposando...
PRIMO: sto correndo...
PRIMO: ho corso 32
PRIMO: il main mi ha fermato: false
PRIMO: ho completato la corsa: true
PRIMO: ho finito di eseguire il metodo run
SECONDO: ho corso 50
SECONDO: il main mi ha fermato: false
SECONDO: ho completato la corsa: true
SECONDO: ho finito di eseguire il metodo run

main: sono stufo di aspettare, interrompo i corridori.
Corridore PRIMO NON ha completato la corsa
Corridore PRIMO ha percorso 32
Corridore SECONDO ha completato la corsa
Corridore SECONDO ha percorso 50
main: Fine.
```