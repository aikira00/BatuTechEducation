
# **Esercizio Ripasso**

Scrivi un programma Python ispirato a Hollow Knight: Silksong.

Il programma deve simulare una battaglia in cui il personaggio combatte contro nemici usando attacchi casuali.

---

## **Obiettivo**

Hornet ha una certa quantità di vita iniziale.  
Finché la vita è maggiore di 0, il gioco continua.

Ad ogni turno:

1. viene scelto casualmente un attacco da una lista;
2. ogni attacco ha un valore di danno diverso;
3. viene generato casualmente il danno minimo necessario per sconfiggere il nemico;
4. se l’attacco scelto fa abbastanza danni:
    - il nemico viene sconfitto;
5. altrimenti:
    - Hornet perde 1 punto vita.

---

# **Vincoli**

Il programma deve usare:

- una **lista di stringhe** per gli attacchi;
- un ciclo `while`;
- un ciclo `for`;
- numeri casuali con `random`;
- condizioni `if`.

---

# **Attacchi disponibili**

Usa questa lista:

```python
attacchi = ["Ago Rapido", "Filo Tagliente", "Lancio Ago", "Danza di Seta"]
```

---

# **Danni degli attacchi**

|**Attacco**|**Danno**|
|---|---|
|Ago Rapido|3|
|Filo Tagliente|5|
|Lancio Ago|7|
|Danza di Seta|9|

---

# **Richieste**

Il programma deve:

1. inizializzare la vita di Hornet a 5;
2. mostrare gli attacchi disponibili usando un `for`;
3. scegliere un attacco casuale;
4. generare un numero casuale tra 1 e 10 che rappresenta il danno richiesto;
5. confrontare il danno dell’attacco con quello richiesto;
6. stampare il risultato del turno;
7. terminare quando la vita arriva a 0.

---

# **Esempio di output**

```python
Attacchi disponibili:
Ago Rapido
Filo Tagliente
Lancio Ago
Danza di Seta

Attacco usato: Lancio Ago
Danno richiesto: 5
Nemico sconfitto!

Vita rimasta: 5
----------------

Attacco usato: Ago Rapido
Danno richiesto: 8
Attacco troppo debole!
Hornet perde 1 vita.

Vita rimasta: 4
----------------
```

---

# **Suggerimento**

Per scegliere un attacco casuale puoi usare:

```python
import random

random.choice(lista)
```

Per generare un numero casuale:

```python
random.randint(1, 10)
```