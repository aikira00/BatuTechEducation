
# Esercizio 4 (15 pt) - Simulazione Escursione in Montagna

## Descrizione

Immagina di essere in un'escursione in montagna dove affronti una serie di situazioni casuali. Progetta un algoritmo e scrivi il programma in Python che simuli questa esperienza utilizzando il ciclo **while** e la generazione di eventi casuali. L'obiettivo è gestire dinamicamente il percorso dell'escursionista e le situazioni incontrate durante l'escursione.

### Variabili iniziali

- **altezza**: rappresenta l'altezza raggiunta dall'escursionista. Parte da 0 (livello del mare)
- **energia**: indica l'energia disponibile. Parte da 100

### Eventi casuali e conseguenze

Ad ogni iterazione del ciclo, il programma deve generare casualmente uno dei seguenti eventi:

1. **Evento 1 - Raggiungimento di una vetta**: aumenta l'altezza di 20 unità
2. **Evento 2 - Trovata una grotta**: l'escursionista si riposa e recupera 20 unità di energia
3. **Evento 3 - Incontro con un guado**: attraversare costa 10 unità di energia
4. **Evento 4 - Terreno impervio**: fa perdere 30 unità di energia

### Condizioni di uscita dal ciclo while

Il programma termina quando si verifica **una** di queste condizioni:

- L'energia scende a 0 o diventa negativa
- L'escursionista raggiunge almeno 100 unità di altezza (obiettivo raggiunto)

### Output finale

Alla fine dell'escursione, stampare:

- Se altezza ≥ 100: `"Hai raggiunto l'obiettivo di altezza!"`
- Se altezza < 100: `"Non hai raggiunto l'obiettivo di altezza. Mancavano ancora X metri."` (dove X = 100 - altezza)

### Suggerimenti

- Usa `if-elif-else` per gestire i 4 eventi diversi
- Stampa ad ogni iterazione cosa succede (quale evento, energia e altezza attuali)
- Controlla bene le condizioni del while
- Alla fine del ciclo, verifica perché è terminato (energia esaurita o obiettivo raggiunto)

## Fase 1: Progettazione dell'algoritmo

Prima di scrivere il codice, devi progettare l'algoritmo in **pseudo-linguaggio**. L'algoritmo deve descrivere chiaramente la logica del programma utilizzando il ciclo **while** e la gestione di eventi casuali.

**Linee guida per lo pseudolinguaggio:**

- Usa parole chiave semplici in italiano come: `INIZIO`, `FINE`, `MENTRE`, `SE`, `ALTRIMENTI`
- Indica chiaramente le operazioni di assegnamento con `←` oppure `=`
- Descrivi le condizioni in modo leggibile: `energia > 0 E altezza < 100`
- Non usare sintassi specifica di Python, ma un linguaggio comprensibile a chiunque

**Esempio di struttura in pseudolinguaggio:**

INIZIO
    altezza = 0
    energia = 100
    
    MENTRE (energia > 0 E altezza < 100) FARE
        genera evento casuale da 1 a 4
        
        SE evento == 1 ALLORA
            altezza = altezza + 20
            stampa "Hai raggiunto una vetta!"
        ALTRIMENTI SE evento == 2 ALLORA
            ...
        FINE SE
    FINE MENTRE
    
    ...
FINE

## Fase 2: Codifica in python
Dopo aver progettato l'algoritmo, implementalo in Python utilizzando la sintassi corretta del linguaggio e il modulo `random` per generare gli eventi casuali.

### Come usare random in Python

### 1. Importare il modulo

All'inizio del programma, scrivi:

```import random```

### 2. Generare un numero casuale

Per generare un numero intero casuale tra 1 e 4 (estremi inclusi), usa:

```evento = random.randint(1, 4)```

Questo genera un numero che può essere 1, 2, 3 oppure 4 con uguale probabilità.

