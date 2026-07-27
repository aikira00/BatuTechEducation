## Esercizio: traccia il comportamento del seguente programma

**Dato il seguente frammento di codice:**

```python
n = int(input("Inserisci un numero: "))
risultato = 0
i = 0

while i < n:
    if i % 3 == 0:
        risultato = risultato + 1
    i = i + 1

print(risultato)
```

**Simula l'esecuzione del programma per n = 7, completando la seguente tabella che mostra come cambiano i valori delle variabili durante l'esecuzione:**

|Iterazione|n|i|i % 3 == 0|risultato|
|---|---|---|---|---|
|Iniziale|7|0||0|
|1|7||||
|2|7||||
|3|7||||
|4|7||||
|5|7||||
|6|7||||
|7|7||||

**Output finale:** ___________

**Domande:**

1. **Quale sarà il risultato nella shell se si inserisce il numero n = 9?**
    
    - a) 3
    - b) 4
    - c) 9
    - d) 0
2. **Quale sarà il risultato nella shell se si inserisce il numero n = 6?**
    
    - a) 2
    - b) 3
    - c) 6
    - d) 1
3. **Quale sarà il risultato nella shell se si inserisce il numero n = 10?**
    
    - a) 3
    - b) 4
    - c) 10
    - d) 5
4. **Cosa calcola questo programma?**
    
    - a) Conta quanti numeri sono divisibili per 3 da 0 a n-1
    - b) Calcola la somma dei multipli di 3 da 0 a n-1
    - c) Calcola il prodotto dei numeri da 0 a n-1
    - d) Conta quanti numeri sono pari da 0 a n-1

