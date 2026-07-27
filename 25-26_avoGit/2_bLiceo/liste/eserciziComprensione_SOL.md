
Da libro BitABit pag PL06 - domande libro comprensione codice pag 365 (liste) e 351 (stringhe) + esercizi preparazione verifica

Quiz 1P Gli indici in Python partono da 0, quindi numeri[2] è il terzo element stampa30
Gli indici negativi partono dalla fine: -1 è l'ultimo elemento, -2 il penultimo quindi C

# Domanda 11

```python
parola = "python"
```

 **A) `parola[0:-1]`**

- Slicing: `start=0`, `stop=-1` (esclude l’ultimo elemento), passo implicito `+1`
    
- Risultato: `"pytho"` ✅ **manca l’ultimo carattere**
    
- Non è invertita.
    
 **B) `parola[-1:0]`**

- Slicing: `start=-1` (ultimo carattere), `stop=0` (primo carattere **escluso**), passo implicito `+1`
    
- Problema: quando il passo è positivo, Python legge da sinistra a destra. Qui `start` è dopo `stop`.
    
- Risultato: `""` (stringa vuota) ❌
    

> Nota: per invertire con slicing serve **passo negativo**.

 **C) `parola[::-1]` ✅**

- Slicing: `start=None` (inizio), `stop=None` (fine), `step=-1`
    
- Python legge la stringa al contrario.
    
- Risultato: `"nohtyp"` ✅ **esattamente ciò che vogliamo**
    
 **D) `parola[::1]`**

- Slicing: passo `+1` (default)
    
- Risultato: `"python"` ❌
    
- La stringa rimane **uguale all’originale**, non invertita.
 
 **Conclusione**

La risposta corretta è **C) `parola[::-1]`** ✅

## Esercizio 1 - Correzione: Trovare il massimo in una lista

**Consegna**: Il seguente programma dovrebbe trovare il valore massimo in una lista di numeri, ma contiene **3 errori**. Trova gli errori, spiega perché sono sbagliati e correggi il codice.

python

```python
numeri = [5, 12, 3, 18, 7, 9]

massimo = 0  # Errore 1

for i in range(len(numeri)):
    if numeri[i] < massimo:  # Errore 2
        massimo = numeri[i]

print("Il valore massimo è:", massimo)
```

**Domande**:

1. Quale valore stampa il programma attualmente? Perché?
2. Quali sono i 3 errori nel codice?
3. Riscrivi il codice corretto

**Suggerimento**: Prova a eseguire il programma mentalmente passo per passo, seguendo i valori delle variabili.

### Soluzione Esercizio 1

**Errori trovati**:

1. **Errore 1 - Inizializzazione di `massimo`**:
    - `massimo = 0` è sbagliato perché se tutti i numeri nella lista fossero negativi (es. [-5, -12, -3]), il massimo rimarrebbe 0 che non è nemmeno nella lista!
    - **Correzione**: `massimo = numeri[0]` oppure `massimo = numeri[0]` dopo aver verificato che la lista non sia vuota
2. **Errore 2 - Operatore di confronto sbagliato**:
    - `if numeri[i] < massimo:` è sbagliato perché cerca valori MINORI del massimo corrente, non MAGGIORI
    - **Correzione**: `if numeri[i] > massimo:`
3. **Errore 3 - Non c'è un terzo errore nel codice sopra, ma potremmo considerare la mancanza di controllo sulla lista vuota**

**Cosa stampa il programma attualmente?** Il valore massimo è: 0

Perché tutti i numeri (5, 12, 3, 18, 7, 9) sono maggiori di 0, quindi la condizione `numeri[i] < massimo` non è mai vera e `massimo` rimane 0.

**Codice corretto**:
numeri = [5, 12, 3, 18, 7, 9]

massimo = numeri[0]  # Correzione 1: inizializzo con il primo elemento

for i in range(len(numeri)):
    if numeri[i] > massimo:  # Correzione 2: cerco valori MAGGIORI
        massimo = numeri[i]

print("Il valore massimo è:", massimo)

## Esercizio 2 - Comprensione e Correzione: Contare le vocali

**Consegna**: Il seguente programma dovrebbe contare quante vocali ci sono in una stringa, ma contiene **errori logici e di implementazione**.



```python
parola = "programmazione"
vocali = "aeiou"
contatore = 0

for i in range(len(parola)):
    if parola[i] == vocali:  # Errore 1
        contatore = contatore + 1

print("Numero di vocali:", contatore)
```
**Domande**: 
1. Quante vocali ci sono nella parola "programmazione"? (contale manualmente) 
2. Cosa stampa il programma attualmente? Perché? 
3. Qual è l'errore nella condizione `if`? 
4. Riscrivi il codice corretto
**Bonus**: Riscrivi il codice usando un ciclo `for` che itera direttamente sui caratteri invece che sugli indici (es. `for carattere in parola:`)

**Risposta domanda 1**: La parola "programmazione" contiene 6 vocali: o, a, a, i, o, e **Cosa stampa attualmente?** ``` Numero di vocali: 0

L'errore è nella riga:

if parola[i] == vocali:

Questa condizione confronta un **singolo carattere** (es. 'p', 'r', 'o'...) con l'**intera stringa** "aeiou".

Ad esempio:

- `'p' == "aeiou"` → False
- `'o' == "aeiou"` → False (anche se 'o' è nella stringa!)
**Correzione**: Dobbiamo controllare se il carattere è **contenuto in** vocali, usando l'operatore `in`:

if parola[i] in vocali:

parola = "programmazione"
vocali = "aeiou"
contatore = 0

for carattere in parola:  # Itero direttamente sui caratteri
    if carattere in vocali:
        contatore = contatore + 1

print("Numero di vocali:", contatore)
```

**Output corretto**:
```
Numero di vocali: 6