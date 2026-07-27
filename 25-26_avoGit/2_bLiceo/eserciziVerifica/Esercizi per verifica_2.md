# Verifica – Le istruzioni di selezione in Python

Nello svolgimento del test fai attenzione: ogni domanda ha una sola risposta esatta.
Rispondi a ciascuna domanda, dove indicato segna la lettera corretta o “V/F”.

1. Dato il seguente frammento di codice:

	```
	x = 10
	y = 5
	if x > y:
	    messaggio = "maggiore"
	else:
	    messaggio = "minore"
	print(messaggio)
	```
	
Che cosa compare esattamente nella shell?
	

---


2. Dato il seguente frammento di codice

	```
	a = int(input("Inserisci un numero: "))
	b = int(input("Inserisci un altro numero: "))
	if a % 2 == 0 and b % 2 == 0:
	    risultato = "entrambi pari"
	elif a % 2 != 0 and b % 2 != 0:
	    risultato = "entrambi dispari"
	else:
	    risultato = "uno pari e uno dispari"
	print(risultato)
	```
	
Cosa viene stampato se si inseriscono i numeri **3** e **8**?

a) entrambi pari b) entrambi dispari c) uno pari e uno dispari d) errore

---
3. Dato il frammento:

```
a = 5
b = 2
if a > 3 or b > 10:
    print("vero")
else:
    print("falso")
```

Cosa viene mostrato nella shell?

a) vero b) falso c) errore d) nulla

---
4. Dato il seguente frammento di codice 
```
a = int(input("Valore a "))
b = int(input("Valore b "))
c = int(input("Valore c "))

if a > 1:
    print(a)
elif b > 2:
    print(b)
elif c > 3:
    print(c)
elif a + b + c > 10:
    print(7)
else:
    print(10)
```

quale sarà il risultato nella shell, se si inseriscono i numeri a= –3 e b=6 e c=5? 
   
quale sarà il risultato nella shell se si inseriscono i numeri a=10 e b=–7 e c=30?

5. Dato il seguente frammento di codice 
```
a = int(input("Valore a "))
b = int(input("Valore b "))
c = int(input("Valore c "))

if a > 1:
    print(a)
if b > 2:
    print(b)
if c > 3:
    print(c)
if a + b + c > 10:
    print(7)
else:
    print(10)
```
quale sarà il risultato nella shell, se si inseriscono i numeri a= –3 e b=6 e c=5? 
   
quale sarà il risultato nella shell se si inseriscono i numeri a=10 e b=–7 e c=30?

## Domande vero/falso
1) In Python, l’istruzione `if` può esistere senza `else`. V/F
2) Il simbolo **`=`** serve per confrontare due valori. V/F
3) n Python, l’operatore `and` restituisce **True** solo se entrambe le condizioni sono false. V/F