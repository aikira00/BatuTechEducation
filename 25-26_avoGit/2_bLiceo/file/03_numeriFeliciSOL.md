# Esercitazione: Anni Felici
# Legge anni da un file e verifica se sono 'Numeri Felici'
Scrivere un programma in Python che:

1. **Legga** un file di testo chiamato `anni_input.txt` contenente un elenco di anni (uno per riga).
    
2. **Verifichi** se ogni anno è "felice" seguendo la regola matematica:
    
    - Si sommano i quadrati delle cifre del numero.
        
    - Si ripete il processo con il risultato ottenuto.
        
    - Se si arriva a **1**, il numero è **felice**.
        
    - Se si entra in un **loop infinito** che non contiene l'1, il numero è **infelice**.
        
3. **Scriva** i risultati in un nuovo file chiamato `risultati.txt`.

def verifica_felice(n):
    memoria = set()
    while n != 1 and n not in memoria:
        memoria.add(n)
        n = sum(int(cifra)**2 for cifra in str(n))
    return n == 1

try:
    with open('anni_input.txt', 'r') as file_in, open('risultati.txt', 'w') as file_out:
        for riga in file_in:
            anno = int(riga.strip())
            if verifica_felice(anno):
                file_out.write(f"{anno} -> FELICE\n")
            else:
                file_out.write(f"{anno} -> non felice\n")
    print("Elaborazione completata. Controlla il file risultati.txt")
except FileNotFoundError:
    print("Assicurati che il file anni_input.txt sia nella stessa cartella!")

import os

# --- Percorsi ---
radice      = os.getcwd()
file_input  = os.path.join(radice, "dati", "anni_input.txt")
file_output = os.path.join(radice, "dati", "risultati.txt")

# --- Algoritmo ---
def is_felice(n):
    while n != 1 and n != 4:
        n = sum(int(cifra) ** 2 for cifra in str(n))
    return n == 1

# --- Lettura input ---
with open(file_input, "r", encoding="utf-8") as f:
    anni = [int(riga.strip()) for riga in f if riga.strip()]

# --- Analisi e scrittura output ---
with open(file_output, "w", encoding="utf-8") as f:
    for anno in anni:
        risultato = f"{anno}: {'FELICE' if is_felice(anno) else 'NON FELICE'}"
        print(risultato)
        f.write(risultato + "\n")

print(f"\nFile salvato in: {file_output}")