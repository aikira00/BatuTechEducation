
Da libro BitABit pag PL06 - domande libro comprensione codice pag 365 (liste) e 351 (stringhe) + esercizi preparazione verifica
## Quiz 1 - Liste: Accesso e Iterazione

### Domanda 1

Dato il seguente codice:

python

```python
numeri = [10, 20, 30, 40, 50]
print(numeri[2])
```

Cosa viene stampato?

**A)** 20  
**B)** 30  
**C)** 2  
**D)** Errore

### Domanda 2

Quale delle seguenti affermazioni è VERA?

**A)** `len([1, 2, 3, 4])` restituisce 3  
**B)** `[1, 2, 3][3]` restituisce 3  
**C)** `[1, 2, 3][-1]` restituisce 3  
**D)** `[1, 2, 3][0]` restituisce il secondo elemento

### Domanda 3

Dato il seguente codice:
```python
lista = [5, 10, 15, 20]
somma = 0
for num in lista:
    somma = somma + num
print(somma)
```
Quale valore viene stampato?
**A)** 20  
**B)** 50  
**C)** 5  
**D)** 0

### Domanda 4

Dato il seguente codice:

python

```python
parola = "python"
print(len(parola))
```

Cosa viene stampato?

**A)** 5  
**B)** 6  
**C)** 7  
**D)** "python"

### Domanda 5
Dato il seguente codice:
```python
parola = "ciao"
contatore = 0
for lettera in parola:
    if lettera == "a":
        contatore += 1
print(contatore)
```
Quale valore viene stampato?

**A)** 0  
**B)** 1  
**C)** 2  
**D)** 4

### Domanda 7

Quale delle seguenti condizioni verifica correttamente se un carattere è una vocale?

**A)** `if carattere == "aeiou":`  
**B)** `if carattere in "aeiou":`  
**C)** `if "aeiou" == carattere:`  
**D)** `if "aeiou" in carattere:`

### Domanda 8

Dato il seguente codice:

```python
testo = "HELLO"
print(testo[0] + testo[-1])
```

Cosa viene stampato?

**A)** HE  
**B)** HO  
**C)** EO  
**D)** Errore

### Domanda 9

Dato il codice:

```python
numeri = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(numeri[2:5])
```

Cosa viene stampato?

**A)** [2, 3, 4, 5]  
**B)** [2, 3, 4]  
**C)** [1, 2, 3, 4]  
**D)** [3, 4, 5]

### Domanda 10

Dato il codice:

```python
parola = "python"
print(parola[::2])
```

Cosa viene stampato?

**A)** "pth"  
**B)** "pto"  
**C)** "yhn"  
**D)** "python"

### Domanda 11

Quale delle seguenti espressioni restituisce una stringa invertita?

**A)** `parola[0:-1]`  
**B)** `parola[-1:0]`  
**C)** `parola[::-1]`  
**D)** `parola[::1]`

### Domanda 12
Dato il codice:

```python
parola = "banana"
contatore = 0
for i in range(len(parola)):
    if parola[i] == "a":
        contatore += 1
print(contatore)
```

Quale valore viene stampato?

**A)** 0  
**B)** 1  
**C)** 3  
**D)** 6

### Domanda 13
Quale dei seguenti cicli stampa tutti gli elementi di una lista?

**A)**
```python
for i in lista:
    print(lista[i])
```

**B)**
```python
for i in range(lista):
    print(lista[i])
```

**C)**
```python
for elemento in lista:
    print(elemento)
```

**D)**
```python
for i in len(lista):
    print(lista[i])
```
### Domanda 15

Qual è l'output del seguente codice?
```python
lista = [1, 2, 3]
lista.append(4)
print(len(lista))
```

**A)** 3  
**B)** 4  
**C)** [1, 2, 3, 4]  
**D)** Errore

### Domanda 16

Dato il codice:

```python
numeri = [1, 2, 3, 4, 5, 6]
risultato = []
for num in numeri:
    if num % 2 == 0:
        risultato.append(num)
print(risultato)
```

Cosa viene stampato?

**A)** [1, 3, 5]  
**B)** [2, 4, 6]  
**C)** [1, 2, 3, 4, 5, 6]  
**D)** 3

### Domanda 17

Dato il codice:

```python
parola = "programmazione"
vocali = "aeiou"
contatore = 0
for carattere in parola:
    if carattere in vocali:
        contatore += 1
print(contatore)
```

Quale valore viene stampato?

**A)** 5  
**B)** 6  
**C)** 14  
**D)** 0

### Domanda 18

Quale delle seguenti affermazioni è FALSA?

**A)** Le stringhe in Python sono immutabili  
**B)** Le liste in Python sono mutabili  
**C)** `"ciao"[0] = "C"` è un'operazione valida  
**D)** `lista[0] = 10` è un'operazione valida

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