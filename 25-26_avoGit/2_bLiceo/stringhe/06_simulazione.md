# Esercitazione: Simuliamo un algoritmo sulle stringhe

Capire come funzionano le operazioni sulle stringhe in Python attraverso:

- simulazione manuale del codice
    
- esecuzione reale
    
- modifica e sperimentazione


Dato il seguente codice:
  
```
s = "informatica"
risultato = ""

for i in range(len(s)):
    if i % 2 == 0:
        risultato = risultato + s[i].upper()
    else:
        risultato = risultato + s[i]

print(risultato)
```

1. Completa la tabella simulando il codice sul quaderno:


| **i** | **s[i]** | **i % 2 == 0** | **risultato (prima)** | **risultato (dopo)** |
| ----- | -------- | -------------- | --------------------- | -------------------- |
| 0     |          |                |                       |                      |
| 1     |          |                |                       |                      |
| 2     |          |                |                       |                      |
| …     |          |                |                       |                      |


2. Il valore finale stampato è: 


3. Spiega cosa fa programma a parole: 


4. Trasforma il programma in modo che:

	- i caratteri in posizione **dispari** diventino maiuscoli
    
	- gli altri restino invariati
	
 5. Trasforma il programma in modo che:
	- Sostituisci tutte le vocali con *

Esempio:

```
input: informatica
output: *nf*rm*t*c*
```
