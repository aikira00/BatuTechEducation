Perfetto! Possiamo creare una **esercitazione guidata in due parti**, molto didattica per una seconda liceo:

- **Esercizio 1** – Leggere una matrice immagine (0/1) da file e comprimere ogni riga con RLE.
    
- **Esercizio 2** – Leggere la compressione RLE da file o da variabile e decomprimere per ricostruire l’immagine.
    

Scriviamo tutto in modo **lineare**, con spiegazioni passo passo su apertura file, lettura e scrittura.

---

# **Esercitazione guidata: Compressione e decompressione di immagini con RLE**

## **Introduzione**

L’algoritmo **Run-Length Encoding (RLE)** serve a comprimere dati con **ripetizioni consecutive**, come pixel in immagini semplici.  
In questa esercitazione useremo **immagini in bianco e nero**:

- `0` → bianco
    
- `1` → nero
    

---

## **Esercizio 1 – Comprimere un’immagine da file**

### **Obiettivo**

1. Leggere la matrice dell’immagine da un file
    
2. Comprimi ogni riga usando RLE
    
3. Stampare la compressione
    

### **Passaggi guidati**

1. **Preparare il file immagine** `cuore.txt` (10×10) con 0 e 1 separati da spazi:
    

```
0 0 1 1 0 0 1 1 0 0
0 1 1 1 1 1 1 1 1 0
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 0
0 0 1 1 1 1 1 1 0 0
0 0 0 1 1 1 1 0 0 0
0 0 0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
```

2. **Leggere il file in Python**:
    

```python
# Apriamo il file in modalità lettura
file = open("cuore.txt", "r")

immagine = []

# Leggiamo riga per riga
for riga_file in file:
    riga_file = riga_file.strip()  # togli eventuali spazi o a capo
    numeri_str = riga_file.split()  # separa i numeri in una lista di stringhe
    # Convertiamo in numeri interi
    riga = []
    for n in numeri_str:
        riga.append(int(n))
    immagine.append(riga)

file.close()  # chiudiamo il file
```

💡 **Spiegazione**:

- `open("cuore.txt", "r")` apre il file in modalità **lettura**
    
- `strip()` rimuove spazi e a capo
    
- `split()` divide la riga in singoli numeri
    
- `int()` converte le stringhe in numeri interi
    
- `file.close()` chiude il file
    

3. **Comprimere ogni riga con RLE**:
    

```python
# Lista per la compressione
cuore_compressa = []

# Comprimiamo riga per riga
for riga in immagine:
    riga_compressa = []
    conteggio = 1
    for i in range(1, len(riga)):
        if riga[i] == riga[i-1]:
            conteggio = conteggio + 1
        else:
            riga_compressa.append([riga[i-1], conteggio])
            conteggio = 1
    riga_compressa.append([riga[-1], conteggio])
    cuore_compressa.append(riga_compressa)

# Stampiamo la compressione
for riga in cuore_compressa:
    print(riga)
```

---

## **Esercizio 2 – Decomprimere un’immagine da RLE**

### **Obiettivo**

1. Leggere i dati compressi (da file o variabile)
    
2. Decomprimere riga per riga
    
3. Stampare l’immagine in console
    

### **Esempio con variabile (più semplice per studenti)**

```python
# Versione compressa già in memoria
cuore_compressa = [
[[0,2],[1,2],[0,2],[1,2],[0,2]],
[[0,1],[1,8],[0,1]],
[[1,10]],
[[1,10]],
[[0,1],[1,8],[0,1]],
[[0,2],[1,7],[0,2]],
[[0,3],[1,4],[0,3]],
[[0,4],[1,2],[0,4]],
[[0,10]],
[[0,10]]
]

# Decompressione
immagine_decompresso = []

for riga_compressa in cuore_compressa:
    riga_decompresso = []
    i = 0
    while i < len(riga_compressa):
        coppia = riga_compressa[i]
        valore = coppia[0]
        conteggio = coppia[1]
        j = 0
        while j < conteggio:
            riga_decompresso.append(valore)
            j = j + 1
        i = i + 1
    immagine_decompresso.append(riga_decompresso)

# Stampa in console
for riga in immagine_decompresso:
    for pixel in riga:
        if pixel == 1:
            print("#", end="")
        else:
            print(" ", end="")
    print("")
```

💡 **Nota:** qui gli studenti vedono subito **il cuore stampato con `#` e spazi`**, e capiscono come funziona RLE.

---

### **Varianti didattiche**

- Invece di file `.txt`, si può usare una **stringa multilinea** con `split("\n")` per leggere le righe
    
- Gli studenti possono **modificare la matrice** e vedere come cambia la compressione
    
- Si può chiedere di **creare più immagini** e decomprimerle tutte
    

---

Se vuoi, posso prepararti **una versione pronta da distribuire agli studenti**, con **due file guida**:

1. `cuore.txt` (immagine da comprimere)
    
2. `cuore_rle.txt` (versione compressa da decomprimere)
    

e un **foglio istruzioni passo passo** già pronto per la classe.

Vuoi che lo faccia?