# **Esercizio – “Gita in treno dell’istituto”**

**Obiettivo:** leggere e comprendere un programma Python, simulare l’esecuzione con aggiornamenti multipli delle variabili, e completare una parte del codice

Python

```
import math

# INPUT
nome = input("Nome e cognome: ")
km = float(input("Km del viaggio: "))
eta = int(input("Età: "))
studente = input("Sei studente? (s/n): ")
codice = input("Codice sconto (invio se non hai codice): ")
assicurazione = input("Vuoi l'assicurazione viaggio (+0.80€)? (s/n): ")

# CALCOLI DI BASE
zone = math.ceil(km / 10)            # 1 zona ogni 10 km, arrotondata per eccesso
prezzo_base = zone * 1.50

# SCONTO PER ETA/STATO: (si aggiorna una prima volta)
sconto = 0.0
if eta < 14:
    sconto = 0.50
else:
    if eta >= 65:
        sconto = 0.30
    else:
        if studente == "s" or studente == "S":
		    sconto = 0.20

prezzo = prezzo_base * (1 - sconto)  # 1° aggiornamento di 'prezzo'

# CODICE SCONTO: (si aggiorna una 2ª volta)
if len(codice) >= 5:
    prezzo = max(0.0, prezzo - 1.00) # coupon -1€, non meno di 0

# ASSICURAZIONE: (si aggiorna una 3ª volta)
if assicurazione == "s" or assicurazione == "S":
    prezzo = prezzo + 0.80

# TODO: COMPLETA I FLAG/CONDIZIONI
# Imposta le seguenti variabili booleane:
# - viaggio_lungo: True se il viaggio è di almeno 50 km
# - nome_lungo: True se il nome ha più di 12 caratteri
# - sconto_massimo: True se lo sconto applicato è quello del 50%
# - prezzo_zero: True se il prezzo finale (dopo tutti gli aggiornamenti) è 0.0
# Sostituisci i None con espressioni booleane corrette.
viaggio_lungo = None
nome_lungo = None
sconto_massimo = None
prezzo_zero = None

#TODO COMPLETA

print("------ RIEPILOGO ------")
print("Zone:", zone, " Prezzo base:", prezzo_base, "€")
print("Sconto applicato:", sconto)
print("Viaggio lungo? ", viaggio_lungo)
print("Nome lungo?    ", nome_lungo)
print("Sconto massimo? ", sconto_massimo)
print("Prezzo è zero?  ", prezzo_zero)
print("Totale da pagare:", round(prezzo, 2), "€")
```

---

## **Parte A – Comprensione (risposte brevi)**

1. Perché si importa math? Indica la riga che la usa.
    
2. Tipo e significato di: km, eta, studente, codice, assicurazione, zone, prezzo_base, sconto, prezzo.
    
3. Cosa fa math.ceil(km / 10)? Fai un esempio numerico.
    
4. Spiega la catena di if annidati per determinare lo **sconto**: in quale caso vale 0?
    
5. Perché si controlla len(codice) >= 5? Cosa accade se è 3?
    
6. A cosa serve max(0.0, prezzo - 1.00)?
    

---

## **Parte B – Simulazione (aggiornamenti multipli delle variabili)**

Simula **senza eseguire** il programma con questi input:

```
nome = "Giulia Verdi"
km = 47.2
eta = 15
studente = "s"
codice = "GITA2025"
assicurazione = "n"
```


## **Parte C – Mini-esercizio FLAG/CONDIZIONI (completare il** **`# TODO`** nel codice)

Guardando il codice, completa la parte segnata con `# TODO` **usando istruzioni if**.

Flag richiesti:

- viaggio_lungo: vale True se il viaggio è di **almeno 50 km**, altrimenti False.
    
- nome_lungo: vale True se lunghezza del nome è **maggiore di 12**, altrimenti False.
    
- sconto_massimo: vale True se è stato applicato **lo sconto del 50%**, altrimenti False.
    
- prezzo_zero: vale True se **dopo tutti gli aggiornamenti** il prezzo è esattamente 0.0, altrimenti False.
    

