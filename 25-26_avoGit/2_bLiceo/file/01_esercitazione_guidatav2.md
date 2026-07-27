
# Esercitazione: Gestione dei File in Python

## Obiettivi

Al termine di questa attività, sarai in grado di:

- Aprire e chiudere file in sicurezza usando il **context manager** `with`.
    
- Leggere dati (tutto il blocco o riga per riga).
    
- Scrivere e aggiungere dati senza perdere quelli esistenti.
    
- Gestire le eccezioni per evitare il crash del programma (file mancanti).
    

---

## Parte 1 – Preparazione

Crea un file di testo chiamato `testo.txt` nella stessa cartella dove salverai i tuoi script Python. Inserisci queste tre righe:

Plaintext

```
Python è un linguaggio di programmazione.
Gli studenti stanno imparando a usare i file.
Questa è una esercitazione sulla lettura e scrittura.
```

---

## Parte 2 – Leggere il contenuto

Per leggere un file usiamo la funzione `open()`. Il metodo più sicuro è l'istruzione `with`, che garantisce la chiusura del file anche se si verifica un errore durante l'esecuzione.

Python

```
# Leggere tutto il contenuto in una variabile
with open("testo.txt", "r", encoding="utf-8") as f:
    contenuto = f.read()
    print(contenuto)
```

> **Nota sull'Encoding:** Usare `encoding="utf-8"` è fondamentale per visualizzare correttamente lettere accentate (à, è, ì, ò, ù) indipendentemente dal sistema operativo (Windows, Mac o Linux).

---

## Parte 3 – Iterazione riga per riga

Se un file è molto grande (es. gigabyte di log), non conviene caricarlo tutto in memoria con `read()`. È meglio leggerlo un pezzo alla volta.

Python

```
# Iterazione diretta sull'oggetto file (metodo più efficiente)
with open("testo.txt", "r", encoding="utf-8") as f:
    for riga in f:
        print(riga.strip()) 
```

**💡 Curiosità tecnica:** Perché usiamo `.strip()`? Ogni riga del file termina con il carattere invisibile `\n` (newline). Anche la funzione `print()` aggiunge un "a capo" di default. Senza `strip()`, vedresti una riga vuota tra ogni frase.

---

## Parte 4 – Scrittura: "w" vs "a"

Esistono diverse modalità per aprire un file in scrittura:

- **`"w"` (Write):** Crea il file o **sovrascrive** quello esistente. Attenzione: cancella tutto il contenuto precedente!
    
- **`"a"` (Append):** Aggiunge i dati alla **fine** del file senza cancellare nulla.
    

### Esempio di sovrascrittura:

Python

```
with open("output.txt", "w", encoding="utf-8") as f:
    f.write("Questo sovrascrive tutto il contenuto precedente.\n")
```

### Esempio di aggiunta:

Python

```
with open("output.txt", "a", encoding="utf-8") as f:
    f.write("Questa riga viene aggiunta in fondo al file.\n")
```

---

## Parte 5 – Tabella Riassuntiva delle Modalità

|**Modalità**|**Nome**|**Cosa fa?**|
|---|---|---|
|**`"r"`**|**Read**|Legge (default). Restituisce errore se il file non esiste.|
|**`"w"`**|**Write**|Scrive. Crea il file o **svuota** quello esistente.|
|**`"a"`**|**Append**|Aggiunge. Scrive alla fine del file esistente.|

---

## Parte 6 – Gestire i file mancanti (try-except)

Cosa succede se provi ad aprire un file che non esiste? Il programma si blocca con un errore `FileNotFoundError`. Per rendere il programma più professionale, usiamo un blocco `try-except`.

Python

```
nome_file = "archivio_segreto.txt"

try:
    with open(nome_file, "r", encoding="utf-8") as f:
        print(f.read())
except FileNotFoundError:
    print(f" Errore: Il file '{nome_file}' non è stato trovato.")
    print("Controlla che il nome sia corretto e che si trovi nella cartella giusta.")
```

---

## Sfida Finale: Il Copiatore Intelligente

Completa il codice seguente. L'obiettivo è leggere il file `testo.txt` e creare un nuovo file chiamato `testo_maiuscolo.txt` dove ogni riga è trasformata in MAIUSCOLO.

Python

```
# ESERCIZIO: Leggiamo da 'testo.txt' e scriviamo in 'testo_maiuscolo.txt'
try:
    with open("testo.txt", "r", encoding="utf-8") as sorgente:
        with open("testo_maiuscolo.txt", "w", encoding="utf-8") as destinazione:
            for riga in sorgente:
                # --- SCRIVI QUI LA TUA LOGICA ---
                riga_modificata = riga.upper()
                destinazione.write(riga_modificata)
    
    print("Operazione completata! Controlla il nuovo file creato.")

except FileNotFoundError:
    print("Errore: Assicurati che 'testo.txt' esista prima di avviare lo script.")
```

