In questa esercitazione applicheremo ai **file** l'algoritmo di compressione **RLE** (Run-Length Encoding) che abbiamo già usato con le stringhe.

Leggeremo un file di testo con sequenze di caratteri ripetuti, comprimeremo ogni riga, salveremo il risultato e poi verificheremo di saper tornare al testo originale.

---

## Richiamo: cos'è l'algoritmo RLE?

RLE è un metodo di compressione che sostituisce una sequenza di caratteri uguali con il **numero di ripetizioni** seguito dal **carattere stesso**.

**Esempio:**

```
Originale:    aaaaaaaaaabbbbbbbbbbCCCCCC
Compresso:    10a10b6C
```

La decompressione è l'operazione inversa: si legge il numero, si legge il carattere, si ripete il carattere tante volte.

 RLE funziona bene quando ci sono molte ripetizioni consecutive. Su testi normali invece può addirittura _aumentare_ la dimensione del file!
## Parte 1: Setup del Progetto (Manuale)

Organizza il tuo spazio di lavoro in PyCharm con questa struttura:

```
Progetto_strCompDecomp/
├── codice/
│   └── compressore.py          ← il file che scriveremo
└── dati/
    ├── sequenza.txt            ← il file di input (da creare tu)
    ├── compresso.txt           ← verrà creato dallo script
    └── decompresso.txt         ← verrà creato dallo script
```

**Contenuto di `sequenza.txt`** — copia e incolla esattamente:

aaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbCCCCCCCCCCCCCCCCCCCC
000000000011111111112222222222333333333344444444445555555555
!!!!!!!!!!!!!!!!!!!!????????????????????####################
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
sssssssssssttttttttttuuuuuuuuuuvvvvvvvvvvwwwwwwwwwwxyyyyyyyy
ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
....................,,,,,,,,,,,,,,,,,,,,--------------------
GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
111112222233333444445555566666777778888899999000001111122222
bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
(((((((((((((((((((())))))))))))))))))))[[[[[[[[[[[[[[[[[[[[
WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
333333333333333333333333333333333333333333333333333333333333
€€€€€€€€€€€€€€€€€€€€£££££££££££££££££££
RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
............................................................
zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
777777777777777777777777777777777777777777777777777777777777
||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq
NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
888888888888888888888888888888888888888888888888888888888888
<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>====================
LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp
222222222222222222222222222222222222222222222222222222222222
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj
HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
999999999999999999999999999999999999999999999999999999999999
////////////////////////////////////////////////////////////
cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc
YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
## Parte 2: Sviluppo del Codice
Crea `compressore.py` dentro `codice/` e costruisci il programma passo per passo.

**Suggerimento per costruire i percorsi**

```python
import os

# Cartella dello script (codice/), indipendente da dove lo lanci 
script_dir = os.path.dirname(os.path.abspath(__file__))
# Radice del progetto: un livello sopra 
radice_dir = os.path.dirname(script_dir)

# Percorsi ai file di input e output
cartella_dati = os.path.join(radice_dir, "dati")
file_input      = os.path.join(cartella_dati, "dati", "sequenza.txt")
file_compresso  = os.path.join(cartella_dati, "dati", "compresso.txt")
file_decomp     = os.path.join(cartella_dati, "dati", "decompresso.txt")
```

**Logica suggerita:**

1. Leggi il contenuto del file sequenza.txt`
    
2. Scorri ogni riga del file. se il carattere corrente è uguale al precedente, incrementa un contatore.
    
3. Appena il carattere cambia, scrivi nel file di output il `carattere + contatore`.
    
4. Gestisci l'ultimo gruppo di caratteri dopo la fine del ciclo.


**Esempio:**

- **Input:** `aaab`
    
- **Output in compresso.txt:** `a3b1`

**Esempio di alcune righe in `compresso.txt`:**

```
20a20b20C
10010111102222222222103103103104104104105105105105
20!20?20#
60X
11s10t10u10v10w10x8y
...
```
...

## Sfide

1. **Statistiche** — dopo la compressione, stampa per ogni riga la lunghezza originale, la lunghezza compressa e la percentuale di riduzione. Quale riga si comprime meglio? Quale peggio?
2. **Gestione dei numeri nel testo originale** — il formato `"4a"` funziona finché il testo originale non contiene cifre. Se la stringa fosse `"3a2b5"`, il compresso sarebbe `"3a2b15"`: come si distingue il `1` del contatore dal `5` del testo? Proponi una modifica al formato di compressione che risolva il problema.