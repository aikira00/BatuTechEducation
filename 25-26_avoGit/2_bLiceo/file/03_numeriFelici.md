# Esercitazione: L'Algoritmo dell'Anno Felice 2026

Secondo un recente approfondimento di **Geopop**, il **2026** non è un anno come gli altri: è matematicamente un **"anno felice"**. Ma cosa significa esattamente? In questa esercitazione scriveremo un programma per scoprirlo e per analizzare un'intera lista di anni.

🔗 **Video e Articolo di riferimento:** [Perché il 2026 sarà un anno felice? (Geopop)](https://www.geopop.it/numero-felice-2026-anno-felice-matematica/)

Dopo aver visto il video e aver capito la regola matematica per dire se un anno è felice, fai il setup del tuo progetto.

## Parte 1: Setup del Progetto (Manuale)

## Parte 1 — Setup del progetto

Prima di scrivere il codice, organizza il tuo spazio di lavoro in PyCharm.

**Struttura da creare:**

```
Progetto_Geopop/
├── codice/
│   └── analizzatore_felice.py    ← il file che scriveremo
└── dati/
    ├── anni_input.txt            ← il file di input (da creare tu)
    └── risultati.txt             ← verrà creato dallo script
```

**Contenuto di `anni_input.txt`** — un anno per riga:

```
2020
2021
2022
2023
2024
2025
2026
2031
2032
1991
1994
2019
2000
1945
```

> 💡 Ricorda: la CWD in PyCharm è la radice del progetto (`Progetto_Geopop/`), non la cartella `codice/` dove si trova lo script. Useremo `os.path.join()` per costruire i percorsi in modo preciso, senza mai spostarci tra le cartelle.

## Parte 2: Sviluppo del Codice

Crea il file `analizzatore_felice.py` dentro la cartella `codice`. Lo script dovrà usare il modulo `os` per raggiungere i dati. Il programma deve

1. **Leggere** il file di testo chiamato `anni_input.txt` contenente un elenco di anni (uno per riga).
    
2. **Verificare** se ogni anno è "felice" seguendo la regola matematica:
        
3. **Scrivere** i risultati in un nuovo file chiamato `risultati.txt`.

Esempio input.txt
2024
2025
2026
1991

Esempio output.txt
2024: NON FELICE
2025: NON FELICE
2026: FELICE
1991: FELICE

Suggerimenti per costruire i percorsi

```python
import os

# Cartella dello script (codice/), indipendente da dove lo lanci 
script_dir = os.path.dirname(os.path.abspath(__file__))
# Radice del progetto: un livello sopra 
radice_dir = os.path.dirname(script_dir)

# Percorsi ai file di input e output
cartella_dati = os.path.join(radice_dir, "dati")
file_input  = os.path.join(cartella_dati, "dati", "anni_input.txt")
file_output = os.path.join(cartella_dati, "dati", "risultati.txt")

print(f"Leggo da:   {file_input}")
print(f"Scrivo su:  {file_output}")
```

> 💡 `os.path.join()` può accettare più di due argomenti: `os.path.join(radice, "dati", "anni_input.txt")` costruisce l'intero percorso in un solo passaggio.

