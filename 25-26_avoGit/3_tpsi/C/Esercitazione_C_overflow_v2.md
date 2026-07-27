# Esercizio a coppie: rappresentazione in CA2 e Overflow in C

**Argomento:** Complemento a 2, overflow, operazioni su interi con segno e senza segno

---

## Obiettivi dell'esercizio

- Comprendere la differenza tra interi con segno (`int8_t`) e senza segno (`uint8_t`)
- Identificare situazioni di overflow
- Analizzare la rappresentazione binaria in Complemento a 2
- Giustificare risultati "anomali" usando le regole del CA2

---

## Suggerimenti

- Usa `#include <stdint.h>` per i tipi `int8_t` e `uint8_t`
- Per stampare `uint8_t` come numero usa `%u` o cast a `(int)`
- Usa il debugger di Code Blocks per vedere i valori in binario
- Ricorda: il bit più significativo in CA2 indica il segno (0=positivo, 1=negativo)

---

## Parte 1: Implementazione

Scrivi un programma C chiamato `analisi_overflow.c` che:

### 1. Dichiara le seguenti variabili su 8 bit:

```c
uint8_t a = 150;
uint8_t b = 50;
int8_t c = 100;
int8_t d = 50;
int8_t e = -80;
int8_t f = -60;

uint8_t risultato1;  // unsigned
int8_t risultato2, risultato3, risultato4;  // signed
uint8_t risultato5;  // unsigned
```

### 2. Stampa le istruzioni per visualizzare il contenuto delle variabili

```c
printf("Variabile a contiene: %u\n", a);
printf("Variabile b contiene: %u\n", b);
printf("Variabile c contiene: %d\n", c);
printf("Variabile d contiene: %d\n", d);
printf("Variabile e contiene: %d\n", e);
printf("Variabile f contiene: %d\n", f);
```

### 3. Assegna alle variabili **a**, **c**, **e** dei valori che non sono nell'intervallo rappresentabile

Assegna valori fuori range e ristampa il contenuto delle variabili:

```c
printf("Variabile a contiene, giusto??: %u\n", a);
printf("Variabile c contiene, giusto??: %d\n", c);
printf("Variabile e contiene, giusto??: %d\n", e);
```

### 4. Esegue le operazioni aritmetiche descritte di seguito

**Prima di procedere**, riporta i valori delle variabili **a**, **c** ed **e** a dei valori rappresentabili nell'intervallo su 8 bit per **unsigned** e **signed**.

#### Operazioni per `risultato1`

Scrivi il codice per eseguire in **risultato1** due operazioni:

* a) una somma tra numeri interi senza segno (quindi positivi) che non dia overflow
* b) una somma tra numeri interi senza segno (quindi positivi) che dia overflow

Vedi il codice d'esempio:

```c
printf("=== ANALISI OVERFLOW E CA2 ===\n\n");

// --- OPERAZIONE 1a: unsigned + unsigned (senza overflow) ---
risultato1 = a + b;
printf("Operazione 1a: %u + %u (unsigned)\n", a, b);
printf("Risultato ottenuto: %u\n", risultato1);
printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b);
printf("---------------------------------------\n");

// --- OPERAZIONE 1b: unsigned + unsigned (con overflow) ---
b = 200;  // assegna a b un valore per andare in overflow
risultato1 = a + b;
printf("Operazione 1b: %u + %u (unsigned)\n", a, b);
printf("Risultato ottenuto: %u\n", risultato1);
printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b);
printf("---------------------------------------\n");
```


#### Operazioni per `risultato2`, `risultato3`, `risultato4`, `risultato5`
**Attenzione:** Utilizzare lo specificatore di formato corretto (`%d` per signed, `%u` per unsigned) nella stampa dei valori. Per il valore teorico, effettuare il cast appropriato a 16 bit: `int16_t` per operazioni signed o `uint16_t` per operazioni unsigned.

Scrivi le seguenti operazioni:

* **risultato2** con variabili c e d
  * a) la prima volta dovrà contenere il risultato di un'operazione tra numeri con segno **entrambi positivi** che non dia overflow
  * b) la seconda volta con overflow


* **risultato3** con variabili e ed f
  * a) la prima volta dovrà contenere il risultato di un'operazione tra numeri con segno **entrambi negativi** che non dia overflow
  * b) la seconda volta con overflow

* **risultato4** dovrà contenere la sottrazione di due numeri discordi che non dia problemi di risultato (una sola operazione)

* **risultato5** dovrà contenere la sottrazione con un errore di overflow (esempio: 150-200) (una sola operazione)

### 5. Esegui il programma ed esamina i risultati

Ora esegui il programma ed esamina i risultati. Noti qualcosa di strano?

---

## Parte 2: Analisi e Motivazione

Dopo aver eseguito il programma, **rispondi alle seguenti domande in un documento separato** aggiungendo gli screenshot e ispezionando le variabili in Code Blocks con la modalità di Debug.

### Domanda 1
Analizza gli intervalli rappresentabili. Cosa succede quando assegnamo un numero che non è rappresentabile nell'intervallo dato?

### Domanda 2
Analizza le due operazioni per la variabile `risultato1`:

* Qual è il risultato ottenuto?
* Qual è il risultato teorico? Perché istruzione printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b); stampa risultato corretto? cosa cambia?
* Perché e quando il risultato non è corretto?
* Giustifica le differenze tra i risultati attesi e quelli ottenuti, ricalcolando manualmente le operazioni e analizzando la rappresentazione binaria pura
* Spiega cosa succede ai bit quando si verifica overflow su unsigned

### Domanda 3
Analizza le due operazioni per la variabile `risultato2`:

* Qual è il risultato ottenuto?
* Qual è il risultato teorico? Perché istruzione printf("Risultato teorico: %u\n", (uint16_t)c + (uint16_t)d); stampa risultato corretto? cosa cambia?
* Perché e quando il risultato non è corretto?
* Giustifica le differenze tra i risultati attesi e quelli ottenuti, ricalcolando manualmente le operazioni e analizzando la rappresentazione binaria pura
* Perché un'addizione di due numeri positivi dà un numero negativo?
* Qual è il valore massimo rappresentabile su `int8_t`?

### Domanda 4
Analizza le due operazioni per la variabile `risultato3`:

* Qual è il risultato ottenuto?
* Qual è il risultato teorico?
* Perché e quando il risultato non è corretto?
* Giustifica le differenze tra i risultati attesi e quelli ottenuti, ricalcolando manualmente le operazioni e analizzando la rappresentazione binaria pura
* Perché l'addizione di due numeri negativi dà un numero positivo?

### Domanda 5
Analizza l'**Operazione 4** (per esempio `100 - 80)`):

* Qual è il risultato ottenuto?
* Qual è il risultato teorico?
* Perché e quando il risultato non è corretto?
* Giustifica le differenze tra i risultati attesi e quelli ottenuti, ricalcolando manualmente le operazioni e analizzando la rappresentazione binaria pura
* Si verifica overflow? 

### Domanda 6
Analizza l'**Operazione 5** (per esempio `150 - 200`):
* Qual è il risultato ottenuto?
* Qual è il risultato teorico?
* Perché e quando il risultato non è corretto?
* Giustifica le differenze tra i risultati attesi e quelli ottenuti, ricalcolando manualmente le operazioni e analizzando la rappresentazione binaria pura
* Cosa succede alla rappresentazone del numero in questo caso?
---

## Parte 3: Domande Extra

### 1. Conversione manuale
Converti manualmente -80 in rappresentazione binaria CA2 su 8 bit, mostrando tutti i passaggi (valore assoluto → binario → complemento a 1 → +1)

### 2. Limiti teorici
Completa la tabella:

| Tipo | Numero di bit | Valore Minimo | Valore Massimo |
|------|---------------|---------------|----------------|
| `uint8_t` | 8 | ? | ? |
| `int8_t` | 8 | ? | ? |
| `uint16_t` | 16 | ? | ? |
| `int16_t` | 16 | ? | ? |

### 3. Previsione
Senza eseguire il codice, prevedi il risultato di:

* `int8_t x = 127; x = x + 1;` → x = ?
* `uint8_t y = 0; y = y - 1;` → y = ?

---

## Consegna

📁 **File da consegnare:**

1. `Cognome1Cognome2_analisi_overflow.c` - Il codice sorgente
2. `Cognome1Cognome2_risposte.gdoc` - Analisi e motivazioni
3. Screenshot vari - Screenshot della Memory View in Code Blocks

---

**Dichiarazione di Trasparenza sull'Uso dell'Intelligenza Artificiale**

Il presente materiale didattico è stato elaborato con l'ausilio di un modello di intelligenza artificiale (**Claude, Anthropic**) in funzione di **strumento di supporto e ottimizzazione della progettazione**.

Il **ruolo fondamentale e insostituibile del docente** nel processo di creazione e validazione è stato garantito attraverso:

* La **Definizione** degli obiettivi didattici e dei risultati di apprendimento
* La **Supervisione critica** della struttura logica e dei contenuti
* La **Verifica** scrupolosa della correttezza scientifica e tecnica
* L'**Adattamento mirato** e l'integrazione del materiale al contesto specifico della classe

Si promuove un uso responsabile, etico e pienamente trasparente dell'Intelligenza Artificiale nella didattica e nella progettazione dei contenuti.

---

## Principali modifiche apportate:

1. **Struttura gerarchica migliorata**: uso corretto dei titoli con `#`, `##`, `###`
2. **Formattazione più chiara**: separazione visiva tra sezioni con linee orizzontali `---`
3. **Liste migliorate**: uso coerente di `*` per le liste non ordinate
4. **Codice formattato**: tutti i blocchi di codice ora usano correttamente i backtick tripli con linguaggio specificato
5. **Tabelle corrette**: formattazione Markdown standard per le tabelle
6. **Coerenza terminologica**: "Code Blocks" → "Code Blocks" (uniformità)
7. **Nomenclatura file**: aggiunto underscore per migliorare leggibilità (`Cognome1Cognome2_analisi_overflow.c`)
8. **Punteggiatura**: corretta e uniformata in tutto il documento