
# Esercizio a coppie: rappresentazione in CA2 e Overflow in C
**Argomento:** Complemento a 2, overflow, operazioni su interi con segno e senza segno

## Obiettivi dell'esercizio
- Comprendere la differenza tra interi con segno (`int8_t`) e senza segno (`uint8_t`)
- Identificare situazioni di overflow
- Analizzare la rappresentazione binaria in Complemento a 2
- Giustificare risultati "anomali" usando le regole del CA2

## Suggerimenti
- Usa `#include <stdint.h>` per i tipi `int8_t` e `uint8_t`  
- Per stampare `uint8_t` come numero usa `%u` o cast a `(int)`  
- Usa il debugger di Code Blocks per vedere i valori in binario  
- Ricorda: il bit più significativo in CA2 indica il segno (0=positivo, 1=negativo)  

## Parte 1: Implementazione 
Scrivi un programma C chiamato `analisi_overflow.c` che:

1) dichiara le seguenti variabili su 8 bit:**

    ```c
    uint8_t a = 150;
    uint8_t b = 50;
    int8_t c = 100;
    int8_t d = 50;
    int8_t e = -80;
    int8_t f = -60;
    
    uint8_t risultato1; // unsigned
    int8_t risultato2, risultato3, risultato4; // signed
    uint8_t risultato5; // unsigned
    ```
2. stampa le istruzioni per visualizzare il contenuto delle variabili

    ```C
     printf("Variabile a contiene: %u\n", a);
     printf("Variabile b contiene: %u\n", b);
     printf("Variabile c contiene: %d\n", c);
     printf("Variabile d contiene: %d\n", d);
     printf("Variabile e contiene: %d\n", e);
     printf("Variabile f contiene: %d\n", f);
     ```

3. assegna alle variabili **a**, **c**, **e**  dei valori che non sono nell'intervallo rappresentabile e ristampa il contenuto delle variabili.

    ```C
       printf("Variabile a contiene, giusto??: %u\n", a);
        printf("Variabile c contiene, giusto??: %d\n", c);
        printf("Variabile e contiene, giusto??: %d\n", e);
    ```
    
4. esegue le operazioni aritmetiche descritte di seguito. Riporta i valori delle variabili **a**, **c** ed **e** a dei valori rappresentabili nell'intervallo su 8 bit per **unsigned** e **signed**
Ora scrivi il codice per eseguire in **risultato1** due operazioni:
    a) una somma tra numeri interi senza segno (quindi positivi) che non dia overflow 
    b) una somma tra numeri interi senza segno (quindi positivi) che dia overflow. 
Vedi il codice d'esempio
    ```C
        printf("=== ANALISI OVERFLOW E CA2 ===\n\n");
        // --- OPERAZIONE 1a: unsigned + unsigned ---
        risultato1 = a + b;
        printf("Operazione 1: %u + %u (unsigned)\n", a, b);
        printf("Risultato ottenuto: %u\n", risultato1);
        printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b); // Uso uint16_t per il risultato teorico
        printf("---------------------------------------\n");
    
         // --- OPERAZIONE 1b: unsigned + unsigned (Overflow) ---
        b = 200; //assegna a b un valore per andare in overflow
        risultato1 = a + b;
        printf("Operazione 1: %u + %u (unsigned)\n", a, b);
        printf("Risultato ottenuto: %u\n", risultato1);
        printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b); // Uso uint16_t per il risultato teorico
        printf("---------------------------------------\n");
    ```
    Scrivi le seguenti operazioni per **risultato2**, **risultato3**, **risultato4**, **risultato5**
    - risultato2 
            a) la prima volta dovrà contenere il risultato di un'operazione tra numeri con segno **entrambi positivi** che non dia overflow
            b) la seconda volta con overflow
    - risultato3 
            a) la prima voltà dovrà contenere il risultato di un'operazione tra numeri con segno **entrambi negativi** che non dia overflow, 
            b) la seconda volta con overflow
   - risultato4 dovrà contenere la sottrazione di due numeri discorsi che non da problemi di risultato (una sola operazione)
    - risultato5 dovrà contenere la sottrazione con un errore di overflow (esempio 150-200) (una sola operazione)
    
5. Ora esegui il programma ed esamina i risultati. Noti qualcosa di strano? 
---
## Parte 2: Analisi e Motivazione 

Dopo aver eseguito il programma, **rispondi alle seguenti domande in un documento separato**  aggiungendo gli screenshots e ispezionando le variabili in CodeBlocks con la modalità di Debug.

1. **Domanda 1** Analizza gli intervalli rappresentabili, cosa succede quando assegnamo un numero che non è rappresentabile nell'intervallo dato?


2. **Domanda 2** Analizza le due operazioni per la variabile risultato1:
    - Qual è il risultato ottenuto?
    - Qual è il risultato teorico? perché istruzione printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b); stampa risultato corretto? cosa cambia?
    - Perché il risultato non è corretto?
    - Spiega cosa succede ai bit quando si verifica overflow su unsigned

3. **Domanda 3** Analizza le due operazioni per la variabile risultato2:
    - Qual è il risultato ottenuto?
    - Il risultato è un numero positivo o negativo?
    - Perché un'addizione di due numeri positivi dà un numero negativo?
    - Qual è il valore massimo rappresentabile su `int8_t`?

4. **Domanda 4** Analizza le due operazioni per la variabile risultato3:
    - Qual è il risultato ottenuto?
    - Scrivi la rappresentazione binaria di -80 e -60 in CA2
    - Perché l'addizione di due numeri negativi dà un numero positivo?

5. **Domanda 5** Analizza l'**Operazione 4** (`100 - (-80)`):
    - Qual è il risultato ottenuto?
    - Riscrivi l'operazione come addizione (sottrarre -80 equivale a...?)
    - Si verifica overflow? Perché?

6. **Domanda 6** Analizza l'**Operazione 5** (`150 - 200`):
    - Qual è il risultato ottenuto?
    - Cosa significa "underflow" per unsigned?
    - Spiega perché il risultato è un numero molto grande invece di negativo
---
## Parte 3:  Domande Extra
1. **Conversione manuale:** Converti manualmente -80 in rappresentazione binaria CA2 su 8 bit, mostrando tutti i passaggi (valore assoluto → binario → complemento a 1 → +1)
2. **Limiti teorici:** Completa la tabella:
    
    |Tipo|Numero di bit|Valore Minimo|Valore Massimo|
    |---|---|---|---|
    |`uint8_t`|8|?|?|
    |`int8_t`|8|?|?|
    |`uint16_t`|16|?|?|
    |`int16_t`|16|?|?|

3. **Previsione:** Senza eseguire il codice, prevedi il risultato di:
    - `int8_t x = 127; x = x + 1;` → x = ?
    - `uint8_t y = 0; y = y - 1;` → y = ?
---
## Consegna

📁 **File da consegnare:**

1. `Cognome1Cognome2analisi_overflow.c` - Il codice sorgente
2. `Cognome1Cognome2risposte.gdoc` -  Analisi e motivazioni
3. Screenshot vari  - Screenshot della Memory View in COde Blocks


---

**Dichiarazione di Trasparenza sull'Uso dell'Intelligenza Artificiale**

Il presente materiale didattico è stato elaborato con l'ausilio di un modello di intelligenza artificiale (**Claude, Anthropic**) in funzione di **strumento di supporto e ottimizzazione della progettazione**.

Il **ruolo fondamentale e insostituibile del docente** nel processo di creazione e validazione è stato garantito attraverso:
- La **Definizione** degli obiettivi didattici e dei risultati di apprendimento.
- La **Supervisione critica** della struttura logica e dei contenuti.
- La **Verifica** scrupolosa della correttezza scientifica e tecnica.
- L'**Adattamento mirato** e l'integrazione del materiale al contesto specifico della classe.
Si promuove un uso responsabile, etico e pienamente trasparente dell'Intelligenza Artificiale nella didattica e nella progettazione dei contenuti.