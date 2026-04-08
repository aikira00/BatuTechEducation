**Esercizio — Gara di dadi con i Thread**

Sviluppare un'applicazione Java che simuli una gara di dadi tra due giocatori.

**Regole del gioco**

Ogni giocatore lancia ripetutamente un dado a 6 facce (valore casuale tra 1 e 6) e accumula i punti ottenuti. Vince il giocatore che raggiunge o supera il punteggio di **100** con il minor numero di lanci.

**Requisiti tecnici**

1. Creare una classe `DicePlayer` che estende `Thread`. Ogni istanza rappresenta un giocatore e, nel metodo `run()`, esegue i lanci in un ciclo fino al raggiungimento del punteggio obiettivo.
2. Ad ogni lancio, stampare a video il nome del giocatore, il valore ottenuto e il punteggio accumulato.
3. Inserire una pausa di 100 ms tra un lancio e l'altro tramite `Thread.sleep()`.
4. Nel `main`, creare e avviare **due istanze** di `DicePlayer` con nomi diversi.
5. Attendere la terminazione di entrambi i thread con `join()`, quindi stampare i risultati finali e dichiarare il vincitore.

**Consegna**

Allegare il codice sorgente (.java) e uno screenshot dell'output di un'esecuzione.

**Domanda di riflessione** — Cosa cambierebbe se si inserisse `t.join()` subito dopo ogni `t.start()` invece che in un ciclo separato?