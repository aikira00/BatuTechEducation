## Esercizio: Traccia il comportamento del seguente programma

**Dato il seguente frammento di codice:**

```python
n = int(input("Inserisci un numero: "))
somma = 0
contatore = 1

while contatore <= n:
    if contatore % 2 == 0:
        somma = somma + contatore
    contatore = contatore + 1

print(somma)
```

**Simula l'esecuzione del programma per n = 6, completando la seguente tabella che mostra come cambiano i valori delle variabili durante l'esecuzione:**

|Iterazione|n|contatore|contatore % 2 == 0|somma|
|---|---|---|---|---|
|Iniziale|6|1||0|
|1|6||||
|2|6||||
|3|6||||
|4|6||||
|5|6||||
|6|6||||

**Output finale:** ___________

**Domande:**

1. **Quale sarà il risultato nella shell se si inserisce il numero n = 5?**
    
    - a) 10
    - b) 6
    - c) 15
    - d) 8
2. **Quale sarà il risultato nella shell se si inserisce il numero n = 8?**
    
    - a) 36
    - b) 20
    - c) 30
    - d) 16
3. **Quale sarà il risultato nella shell se si inserisce il numero n = 3?**
    
    - a) 2
    - b) 3
    - c) 6
    - d) 0
4. **Cosa calcola questo programma?**
    
    - a) La somma di tutti i numeri da 1 a n
    - b) La somma dei numeri pari da 1 a n
    - c) La somma dei numeri dispari da 1 a n
    - d) Il prodotto dei numeri pari da 1 a n
