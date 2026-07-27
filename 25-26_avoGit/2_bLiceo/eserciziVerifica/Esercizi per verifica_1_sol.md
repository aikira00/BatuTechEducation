
  ## **Soluzione rapida per il docente (check)**

- **Parte B – Attesi (primo set di input)**
    
    - zone = ceil(47.2/10) = 5
        
    - prezzo_base = 5 * 1.50 = 7.50
        
    - sconto = 0.20 (studente, età 15)
        
    - prezzo (1°) = 7.50 * 0.8 = 6.00
        
    - len("GITA2025") = 8 ≥ 5 ⇒ prezzo (2°) = 6.00 - 1.00 = 5.00
        
    - assicurazione=False ⇒ nessun cambio (prezzo = 5.00)
        
- **Parte C – Flag corretti (quando completati dagli studenti)**
    

```
viaggio_lungo = (km >= 50)
nome_lungo = (len(nome) > 12)
sconto_massimo = (sconto == 0.50)
prezzo_zero = (prezzo == 0.0)
```

- **Variazione – Attesi**
    
    - codice = "A12" ⇒ len = 3 < 5 ⇒ **niente −1€**
        
    - assicurazione = "s" ⇒ prezzo (3°) = 6.00 + 0.80 = 6.80
        
    - I flag: prezzo_zero resta False; gli altri dipendono dai dati inseriti.
- 

## **RISPOSTE PARTE A:**

### 1. Perché si importa math?
Si importa `math` per usare la funzione `math.ceil()` alla **riga 10** (`zone = math.ceil(km / 10)`).

### 2. Tipo e significato delle variabili:
- **km**: `float` - chilometri del viaggio
- **eta**: `int` - età del passeggero
- **studente**: `str` - risposta se è studente ("s"/"n")
- **codice**: `str` - codice sconto inserito (può essere vuoto)
- **assicurazione**: `str` - risposta se vuole assicurazione ("s"/"n")
- **zone**: `int` - numero di zone tariffarie (arrotondato per eccesso)
- **prezzo_base**: `float` - prezzo prima degli sconti (zone × 1.50€)
- **sconto**: `float` - percentuale di sconto applicata (0.0, 0.20, 0.30, 0.50)
- **prezzo**: `float` - prezzo finale dopo tutti gli aggiornamenti

### 3. Cosa fa math.ceil(km / 10)?
Arrotonda **per eccesso** il risultato di km/10.
**Esempio**: se km = 47.2 → 47.2/10 = 4.72 → math.ceil(4.72) = **5**

### 4. Catena di if annidati per lo sconto:
- Se età < 14 → sconto 50%
- Altrimenti, se età ≥ 65 → sconto 30%
- Altrimenti, se studente → sconto 20%
- **Sconto vale 0** quando: età ≥ 14 AND età < 65 AND NON è studente

### 5. Perché len(codice) >= 5?
Verifica che il codice sconto abbia almeno 5 caratteri (validazione codice).
**Se è 3**: la condizione è falsa, quindi NON viene applicato lo sconto di 1€.

### 6. A cosa serve max(0.0, prezzo - 1.00)?
Impedisce che il prezzo diventi negativo dopo aver applicato il coupon da 1€. Se `prezzo - 1.00` è negativo, imposta il prezzo a 0.0.

---

## **PARTE B - SIMULAZIONE:**

**Input:**
- nome = "Giulia Verdi" (12 caratteri)
- km = 47.2
- eta = 15
- studente = "s"
- codice = "GITA2025" (8 caratteri)
- assicurazione = "n"

**Esecuzione:**
1. `zone = math.ceil(47.2 / 10) = math.ceil(4.72) = 5`
2. `prezzo_base = 5 * 1.50 = 7.50`
3. **Sconto per età/stato:**
   - età = 15 → NON < 14
   - età = 15 → NON ≥ 65
   - studente = "s" → **sconto = 0.20** (20%)
4. `prezzo = 7.50 * (1 - 0.20) = 7.50 * 0.80 = 6.00`
5. **Codice sconto:**
   - len("GITA2025") = 8 ≥ 5 → SI
   - `prezzo = max(0.0, 6.00 - 1.00) = 5.00`
6. **Assicurazione:**
   - assicurazione = "n" → NON aggiunge 0.80€
   - `prezzo = 5.00` (rimane invariato)

**Risultati finali:**
- zone = 5
- prezzo_base = 7.50€
- sconto = 0.20
- prezzo = 5.00€

---

## **PARTE C - COMPLETAMENTO TODO:**

