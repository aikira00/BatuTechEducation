

# **📝 Esercitazione Guidata: Uso delle Funzioni in Python**

  

## **Obiettivi**

  

Al termine di questa attività sarai in grado di:

- Creare **funzioni** per dividere il codice in blocchi riutilizzabili.
    
- Passare **parametri** alle funzioni.
    
- Ricevere **valori di ritorno** dalle funzioni.
    
- Importare funzioni da un altro file (modularità).
    

---

## **📌 Parte 1 – Cos’è una funzione?**

  

Una funzione è un **pezzo di codice che fa qualcosa**, che puoi richiamare ogni volta che vuoi.

  

Esempio:

```
def saluta(nome):
    """Saluta la persona passata come parametro"""
    print(f"Ciao {nome}!")
```

**Uso della funzione:**

```
saluta("Anna")
saluta("Luca")
```

**Sfida:** Crea una funzione che saluti due persone insieme.

---

## **📌 Parte 2 – Funzioni che ritornano valori**

  

Le funzioni possono anche **restituire un risultato**, usando return.

```
def somma(a, b):
    return a + b

risultato = somma(5, 3)
print(risultato)  # Stampa 8
```

**Sfida:** Scrivi una funzione moltiplica(a, b) che ritorna il prodotto dei due numeri.

---

## **📌 Parte 3 – Funzioni con liste**

```
def media_lista(lista):
    return sum(lista) / len(lista)

voti = [7, 8, 10, 5, 6]
print(f"La media dei voti è: {media_lista(voti):.2f}")
```

**Sfida:** Crea una funzione massimo_lista(lista) che ritorna il numero più grande della lista.

---

## **📌 Parte 4 – Modularità: separare le funzioni dal programma principale**

  

### **1. Creiamo un file** 

### **funzioni.py**

```
# File: funzioni.py

def somma(a, b):
    return a + b

def moltiplica(a, b):
    return a * b

def media_lista(lista):
    return sum(lista) / len(lista)
```

> Importante: **nessun print fuori dalle funzioni**.

---

### **2. File principale** 

### **main.py**

```
# File: main.py
import funzioni as fn

x = 10
y = 5

print("Somma:", fn.somma(x, y))
print("Prodotto:", fn.moltiplica(x, y))

numeri = [3, 7, 2, 9, 4]
print("Media:", fn.media_lista(numeri))
```

---

## **📌 Parte 5 – Esercizio guidato**

1. Crea un file funzioni.py con queste funzioni:
    

  

- minimo_lista(lista) → ritorna il valore più piccolo della lista
    
- somma_cifre(n) → ritorna la somma delle cifre di un numero
    
- saluta_persona(nome, età) → stampa: “Ciao {nome}, hai {età} anni!”
    

  

2. Crea main.py che:
    

  

- Importa le funzioni da funzioni.py
    
- Crea una lista di numeri e stampa il minimo
    
- Chiede all’utente di inserire un numero e stampa la somma delle cifre
    
- Chiede nome ed età e chiama saluta_persona()
    

---

### **💡 Consigli**

- Ricorda: **tutto il codice che fa cose deve essere nel main**, non nel file delle funzioni.
    
- Le funzioni sono il modo migliore per **riutilizzare codice e semplificare il programma**.
    

---

Se vuoi, posso preparare anche una **versione “PEI semplificata”** dove gli studenti devono completare solo alcune funzioni con esempi guidati e senza input da tastiera, così risulta più accessibile.

  

Vuoi che faccia quella versione?