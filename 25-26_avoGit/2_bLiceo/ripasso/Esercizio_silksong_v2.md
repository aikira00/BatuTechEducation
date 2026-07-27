
# **Esercizio Ripasso **

# **Esercizio — Silksong Battle Advanced**

Crea un programma Python ispirato a Hollow Knight: Silksong in cui Hornet combatte contro una serie di nemici usando attacchi casuali.

L’obiettivo è sopravvivere il più a lungo possibile.

---

# **Dati iniziali**

Usa questa lista di attacchi:

```python
attacchi = ["Ago Rapido", "Filo Tagliente", "Lancio Ago", "Danza di Seta"]
```

Ogni attacco ha un danno diverso:

|**Attacco**|**Danno**|
|---|---|
|Ago Rapido|3|
|Filo Tagliente|5|
|Lancio Ago|7|
|Danza di Seta|9|

Usa anche questa lista di nemici:

```python
nemici = ["Insetto", "Cacciatore", "Guardiano"]
```

---

# **Obiettivo del programma**

Il gioco continua con un ciclo `while` finché Hornet ha vita maggiore di 0.

Ad ogni turno:

1. viene scelto casualmente un nemico;
2. viene scelto casualmente un attacco;
3. viene generato un numero casuale tra 1 e 10 che rappresenta il danno necessario per sconfiggere il nemico;
4. il programma confronta il danno dell’attacco con il danno richiesto.

---

# **Regole speciali**

## **Nemici**

Se il nemico è `"Guardiano"`:

- il danno richiesto aumenta di 2.

---

## **Vittoria e sconfitta**

Usa un `if` annidato per controllare i risultati:

- se il danno dell’attacco è maggiore o uguale al danno richiesto:
    - il nemico è sconfitto;
    - se il danno supera di almeno 3 punti quello richiesto:
        - stampa `"COLPO PERFETTO!"`
- altrimenti:
    - Hornet perde 1 vita.

---

# **Uso delle stringhe**

Il programma deve anche:

- controllare se il nome dell’attacco contiene la parola `"Ago"`;
- se contiene `"Ago"` stampare:

```python
Attacco con ago!
```

- stampare il nome dell’attacco in MAIUSCOLO usando `.upper()`.

---

# **Uso delle liste**

Crea una lista vuota:

```python
attacchi_usati = []
```

Durante il gioco:

- salva ogni attacco usato nella lista usando `append()`.

Alla fine del programma stampa:

1. tutti gli attacchi usati;
2. quanti attacchi contengono `"Ago"`;
3. quanti nemici sono stati sconfitti;
4. quanti turni sono stati giocati.

---

# **Vincoli**

Il programma deve usare:

- liste;
- stringhe;
- ciclo `while`;
- ciclo `for`;
- `if` annidati;
- `random.choice()`;
- `random.randint()`.

---

# **Esempio di output**

```python
Attacchi disponibili:
Ago Rapido
Filo Tagliente
Lancio Ago
Danza di Seta

Nemico incontrato: Guardiano
Attacco usato: LANCIO AGO

Attacco con ago!

Danno richiesto: 8
Danno attacco: 7

Attacco troppo debole!
Hornet perde 1 vita.

Vita rimasta: 4
----------------------
```

---

# **Suggerimenti**

Per scegliere elementi casuali:

```python
random.choice(lista)
```

Per numeri casuali:

```python
random.randint(1, 10)
```

Per controllare se una parola è presente in una stringa:

```python
if "Ago" in attacco:
```

Per aggiungere elementi a una lista:

```python
attacchi_usati.append(attacco)
```