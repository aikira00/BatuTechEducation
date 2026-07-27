
# Esercitazione: Gestione dei File in Python

## Obiettivi

Al termine di questa attività, sarai in grado di:

- **Aprire e chiudere** file in sicurezza usando il context manager `with`.
    
- **Leggere** dati (tutto il blocco o riga per riga).
    
- **Scrivere** e **aggiungere** dati senza perdere quelli esistenti.
    
- Gestire i caratteri di **newline** (`\n`).
    

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

Per leggere un file usiamo la funzione `open()`. Il metodo più sicuro è l'istruzione `with`, che chiude il file automaticamente anche se si verifica un errore.

Python

```
# Leggere tutto in un colpo solo
with open("testo.txt", "r", encoding="utf-8") as f:
    contenuto = f.read()
    print(contenuto)
```

 encoding="utf-8"` buona norma usarlo per evitare problemi con lettere accentate su sistemi operativi diversi (Windows vs Mac/Linux).

## Parte 3 – Iterazione riga per riga

Se il file è molto grande, non conviene caricarlo tutto in memoria con `read()`. È meglio leggerlo un pezzetto alla volta.

Python

```
with open("testo.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()  
	for riga in lines:
        print(riga.strip()) # strip() rimuove lo spazio vuoto e il comando "a capo" extra
```

**Domanda:** Perché nel ciclo `for` le righe appaiono separate da una riga vuota se non uso `.strip()`?

_Risposta: Perché ogni riga del file termina già con un carattere invisibile `\n` (invio), e `print()` ne aggiunge un secondo di default._

---

## Parte 4 – Scrittura: "w" vs "a"

Esistono diverse modalità per aprire un file in scrittura:

- `"w"` (**Write**): Crea il file o **sovrascrive** quello esistente (cancella tutto!).
    
- `"a"` (**Append**): Aggiunge i dati alla fine del file senza cancellare nulla.
    

### Esempio di sovrascrittura:

Python

```
with open("output.txt", "w", encoding="utf-8") as f:
    f.write("Questo sovrascrive tutto.\n")
```

### Esempio di aggiunta:

Python

```
with open("output.txt", "a", encoding="utf-8") as f:
    f.write("Questa riga viene aggiunta in fondo.\n")
```

---

## Parte 5 – Esercizio di Sintesi: Il Copiatore Intelligente

Invece di copiare tutto il blocco, proviamo a copiare il file riga per riga, trasformando tutto in **MAIUSCOLO**.

**Sfida:** Completa il codice seguente.

Python

```
# Leggiamo da 'testo.txt' e scriviamo in 'testo_maiuscolo.txt'
with open("testo.txt", "r", encoding="utf-8") as file_sorgente:
    with open("testo_maiuscolo.txt", "w", encoding="utf-8") as file_destinazione:
        for riga in file_sorgente:
            # Trasforma la riga e scrivila nel nuovo file
            file_destinazione.write(riga.upper())

print("Copia completata con successo!")
```

---

### Tabella Riassuntiva Modalità

|**Modalità**|**Nome**|**Cosa fa?**|
|---|---|---|
|`"r"`|Read|Legge (default). Errore se il file non esiste.|
|`"w"`|Write|Scrive. Crea il file o svuota quello esistente.|
|`"a"`|Append|Aggiunge. Scrive alla fine del file esistente.|

## Parte 6 – Gestire i file mancanti (`try-except`)

Cosa succede se provi ad aprire un file che non esiste? Il programma si blocca e restituisce un errore di tipo `FileNotFoundError`.

Per evitare il crash, usiamo un blocco **try-except**.

Python

```
nome_file = "archivio_segreto.txt"

try:
    with open(nome_file, "r", encoding="utf-8") as f:
        contenuto = f.read()
        print(contenuto)
except FileNotFoundError:
    print(f"Errore: Il file '{nome_file}' non è stato trovato.")
    print("Assicurati che il nome sia corretto e che il file sia nella stessa cartella dello script.")
```

### Perché è importante?

- **Esperienza utente:** Invece di vedere righe di codice d'errore rosse, l'utente legge un consiglio utile.
    
- **Sicurezza:** Impedisce al programma di chiudersi bruscamente se l'input dell'utente è sbagliato.