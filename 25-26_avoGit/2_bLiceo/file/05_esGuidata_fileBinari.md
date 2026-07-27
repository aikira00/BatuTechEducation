## Parte 7 – Lettura e scrittura binaria (`rb` e `wb`)

Finora abbiamo lavorato con file di testo. Ma alcuni file — immagini, audio, video, PDF — non sono fatti di caratteri leggibili: sono sequenze di **byte**. Per aprirli correttamente dobbiamo usare la **modalità binaria**.

---

### 7.1 – Leggere un file binario

Per leggere un file binario si usa `"rb"` (**read binary**). Python restituisce i dati come oggetto `bytes`, non come stringa.

```python
try:
    with open("foto.jpg", "rb") as f:
        dati = f.read()
        print(type(dati))               # <class 'bytes'>
        print("Byte letti:", len(dati))
except FileNotFoundError:
    print("Il file 'foto.jpg' non esiste.")
```

 `dati` non è una stringa: è una sequenza di byte (`b'...'`). Puoi leggere tutto in una volta con `read()`, oppure a blocchi con `read(1024)` per leggere 1024 byte alla volta.

---

### 7.2 – Scrivere un file binario

Per scrivere dati binari si usa `"wb"` (**write binary**). Se il file esiste già, viene sovrascritto.

```python
try:
    with open("foto.jpg", "rb") as f_origine:
        dati = f_origine.read()

    with open("copia_foto.jpg", "wb") as f_copia:
        f_copia.write(dati)

    print("Copia binaria completata con successo!")
except FileNotFoundError:
    print("Errore: il file originale non esiste.")
```

---

### 7.3 – Copiare a blocchi file di grandi dimensioni

Se il file è molto grande, caricarlo tutto in memoria può essere un problema. La soluzione è leggerlo e scriverlo **a blocchi**:

```python
BLOCCO = 1024  # 1 KB per volta

try:
    with open("video.mp4", "rb") as f_origine, open("copia_video.mp4", "wb") as f_copia:
        while True:
            dati = f_origine.read(BLOCCO)
            if not dati:
                break
            f_copia.write(dati)

    print("Copia del video completata!")
except FileNotFoundError:
    print("Errore: il file video non esiste.")
```

 Il ciclo `while True` legge un blocco alla volta. Quando `read()` restituisce una sequenza vuota (`b''`), significa che il file è finito e usciamo con `break`.

---

### 7.4 – Tabella riassuntiva

|Modalità|Nome|Cosa fa|
|---|---|---|
|`"rb"`|Read binary|Legge file binari. Errore se il file non esiste.|
|`"wb"`|Write binary|Scrive file binari. Sovrascrive se il file esiste.|
|`"ab"`|Append binary|Aggiunge dati binari alla fine del file.|

---

### Esercizio di sintesi – Copia binaria a blocchi

**Obiettivo:** copiare un file binario (`immagine.png`) creando una copia chiamata `immagine_copia.png`.

```python
nome_file  = "immagine.png"
nome_copia = "immagine_copia.png"

try:
    with open(nome_file, "rb") as f_sorgente, open(nome_copia, "wb") as f_dest:
        while True:
            blocco = f_sorgente.read(4096)  # 4 KB per volta
            if not blocco:
                break
            f_dest.write(blocco)

    print("File binario copiato correttamente!")
except FileNotFoundError:
    print(f"Errore: il file '{nome_file}' non esiste.")
```

**Domanda:** perché non usiamo `read()` senza argomento?

**Risposta:** perché il file potrebbe essere molto grande e caricarlo tutto in memoria in un colpo solo può far crashare il programma. Leggere a blocchi è più sicuro ed efficiente.