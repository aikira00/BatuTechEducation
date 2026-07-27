
# Risposte alle Domande - Analisi Overflow e CA2

## Parte 2: Analisi e Motivazione

### Domanda 1: Analisi degli intervalli rappresentabili

**Cosa succede quando assegnamo un numero non rappresentabile?**

Due concetti principali
-  **unsigned** In termini di rappresentazione binaria, vengono **conservati solo gli 8 bit meno significativi (LSB)**, mentre i bit più significativi oltre l'**MSB** vengono "persi"
- **signed** Quando un valore fuori intervallo viene assegnato a un tipo signed, il pattern binario risultante viene interpretato secondo il complemento a 2: se l'MSB è 1, il numero è negativo; se l'MSB è 0, il numero è positivo. Questo spiega perché 145 diventa -111 (MSB=1) e -145 diventa +111 (MSB=0) in un int8_t.
- 
Il codice seguente mostra i problemi descritti sopra
```C

//RIASSEGNA VALORI ALLE VARIABILI FUORI INTERVALLO e RISTAMPA VALORI  
a = 320;  // tipo uint8_t 
c = 145;  
e = -145;  
printf("Variabile a contiene, giusto??: %u\n", a);  //stampa 64
printf("Variabile c contiene, giusto??: %d\n", c);  //stampa -111
printf("Variabile e contiene, giusto??: %d\n", e);  //stampa 111
```
**Quando assegniamo un valore fuori dall'intervallo rappresentabile a un tipo signed (int8_t, int16_t, ecc.):**

1. **A livello teorico**: si verifica **undefined behavior** secondo lo standard C
2. **In pratica** (nella maggior parte delle implementazioni): avviene un **wrap-around**
3. **L'interpretazione finale** dipende dal bit di segno (MSB) del pattern risultante:
    - **MSB = 1** → interpretato come **negativo** (CA2)
    - **MSB = 0** → interpretato come **positivo**

**Variabile a**
La variabile **a** è di tipo **uint8_t** e rappresenta numeri con 8 bit senza segno (solo positivi), con intervallo da 0 a 255 ovvero da 0 a $2^n-1$

Quando assegniamo il valore 320, che richiede 9 bit per essere rappresentato, si verifica un **troncamento**: vengono conservati solo gli 8 bit meno significativi (posizioni 0-7, dove il bit in posizione 7 è l'MSB del risultato), mentre tutti i bit dalla posizione 8 in poi vengono scartati.

**Calcolo binario:**

```
#### Variabile a uint8_t

320₁₀ = 1 0100 0000₂ (9 bit necessari)
        │ └────┬────┘
        │      └─ 8 bit conservati (posizioni 0-7)
        └─ bit posizione 8 (scartato)

Rimangono 8 bit:        0100 0000 = 64₁₀
```

**Variabile c** 
La variabile **c** è di tipo **int8_t**, quindi con segno: si possono rappresentare sia numeri negativi che positivi. In C, i numeri negativi sono rappresentati in **complemento a 2 (CA2)**, quindi con **n** bit l'intervallo va da . Con 8 bit possiamo rappresentare i numeri da **-128** a **+127** ovvero $-2^n$ a $2^n-1$.

Quando assegniamo il valore **145** (fuori dall'intervallo positivo), il pattern binario risultante ha **MSB = 1**, quindi viene interpretato come **numero negativo** in CA2.


```
#### Variabile c int8_t
Valore assegnato: 145₁₀
Intervallo int8_t: -128 ÷ +127

145₁₀ in binario (8 bit):
┌───┬───┬───┬───┬───┬───┬───┬───┐
│ 1 │ 0 │ 0 │ 1 │ 0 │ 0 │ 0 │ 1 │
└─┬─┴───┴───┴───┴───┴───┴───┴───┘
  │
  └─ MSB = 1 → interpretato come NEGATIVO in CA2

- 1001 0001 in CA2 = -111
  
Verifica CA2:

- Complemento a 1: `0110 1110`
- +1: `0110 1111` = 111
- Quindi: `1001 0001` = -111
```


**Variabile e**
La variabile **e** è di tipo **int8_t**, un tipo **signed** (con segno) che rappresenta sia numeri negativi che positivi. Il valore **-145** è **fuori dall'intervallo rappresentabile** (che inizia da -128)
```
Valore assegnato: -145₁₀
Intervallo int8_t: -128 ÷ +127

Wrap-around (aggiungiamo 2^8 = 256):
-145 + 256 = +111₁₀

111₁₀ in binario (8 bit):
┌───┬───┬───┬───┬───┬───┬───┬───┐
│ 0 │ 1 │ 1 │ 0 │ 1 │ 1 │ 1 │ 1 │
└─┬─┴───┴───┴───┴───┴───┴───┴───┘
  │
  └─ MSB = 0 → interpretato come POSITIVO

Verifica CA2:
- CA2(1001 0001) =  01101111
- MSB è zero e viene interpreato come positivo
- In binario: 0110 1111 = 111 (positivo)
```

---
### Domanda 2: Operazioni in CA2

#### Regola fondamentale: quando scartare il riporto oltre l'MSB

 **✅ Il riporto oltre l'MSB si può scartare quando:**

**Condizione: Riporto N = Riporto N+1 dove N=posizione MSB**

Questo significa:

- **Entrambi 0**: nessun riporto coinvolge l'MSB → nessun problema
- **Entrambi 1**: il riporto "attraversa" completamente l'MSB → operazione CA2 valida

**❌ Il riporto indica overflow quando:**

**Condizione: Riporto N≠ Riporto N+1 dove N=posizione MSB**

Questo significa che il segno cambia in modo incongruente.

##### 📊 Tabella XOR per rilevamento overflow e processori (CPU)

 **Tabella completa riporti e overflow:**

Se risultato = 0 → Nessun overflow ✅
Se risultato = 1 → Overflow rilevato ❌

 livello hardware, i processori rilevano l'overflow nelle operazioni aritmetiche su numeri con segno confrontando due riporti relativi al bit più significativo (MSB):**

> - **C_in(MSB)**: riporto **entrante** nella colonna dell'MSB (dalla posizione n-1 verso n -> RIPORTO N)
> - **C_out(MSB)**: riporto **uscente** dalla colonna dell'MSB (dalla posizione n verso n+1 -> RIPORTO COLONNA N+1)
> 
> **Il processore esegue l'operazione XOR tra questi due riporti:**
> - Se N ⊕ N+1 = 0** → operazione **valida** ✅
> - Se N ⊕ N+1 = 1** → **overflow rilevato** ❌
> 
> **Questo segnale viene memorizzato in un flag specifico (Overflow Flag o V-flag) nel registro di stato del processore.**
> 
> **Nei linguaggi di alto livello come C:**
> - Per tipi **unsigned** (es. `uint8_t`): l'overflow è **ben definito** (wrap-around modulo 2^n)
> - Per tipi **signed** (es. `int8_t`): l'overflow è **undefined behavior** secondo lo standard C
> - Il compilatore **non genera automaticamente** controlli di overflow nel codice
> - Il programmatore deve verificare manualmente o usare estensioni specifiche del compilatore

| C_in(MSB) | C_out(MSB) | XOR (C_in ⊕ C_out) | Overflow? | Significato                                              |     |
| --------- | ---------- | ------------------ | --------- | -------------------------------------------------------- | --- |
| 0         | 0          | 0                  | **NO** ✅  | Nessun riporto coinvolge MSB → operazione valida         |     |
| 0         | 1          | 1                  | **SÌ** ❌  | Riporto esce senza entrare → overflow (caso raro)        |     |
| 1         | 0          | 1                  | **SÌ** ❌  | Riporto entra ma non esce → segno cambia erroneamente    |     |
| 1         | 1          | 0                  | **NO** ✅  | Riporto attraversa MSB completamente → operazione valida |     |
 **✅ Soluzione 1: Controllo manuale**

```c
int8_t safe_add(int8_t a, int8_t b) {
    if ((b > 0 && a > INT8_MAX - b) || 
        (b < 0 && a < INT8_MIN - b)) {
        // Overflow rilevato!
        fprintf(stderr, "Overflow!\n");
        return 0;  // o gestisci l'errore
    }
    return a + b;
}
```

 **✅ Soluzione 2: Estensioni GCC/Clang**

```c
#include <stdint.h>

int8_t a = 100, b = 65, result;
if (__builtin_add_overflow(a, b, &result)) {
    // Overflow rilevato
    printf("Overflow!\n");
} else {
    printf("Risultato: %d\n", result);
}
```

 **✅ Soluzione 3: Usare tipi più grandi**

```c
int8_t a = 100, b = 65;
int16_t result = (int16_t)a + (int16_t)b;  // 165 (corretto)

if (result > INT8_MAX || result < INT8_MIN) {
    printf("Overflow se si usa int8_t!\n");
}
```
#### Domanda 2: Operazioni per `risultato1` (unsigned)

**Operazione 1a: 150 + 50 = 200**

**Risultato ottenuto:** 200 ✅  
**Risultato teorico:** 200 ✅  
**Risultato:** CORRETTO (nessun overflow)

**Calcolo binario:**

```
  Riporti:    1   1   1 1
           ─────────────────
            1 0 0 1 0 1 1 0  (150₁₀)
          + 0 0 1 1 0 0 1 0  (50₁₀)
           ─────────────────
            1 1 0 0 1 0 0 0  (200₁₀) ✅
```

---

**Operazione 1b: 150 + 200 = 350**

**Risultato ottenuto:** 94 ❌  
**Risultato teorico:** 350 ✅  
**Risultato:** ERRATO (overflow) - fuori dal range

**Perché `printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b);` stampa il risultato corretto?**

Perché il cast a `uint16_t` (16 bit) **prima** dell'addizione permette di rappresentare il valore 350, che richiede 9 bit:

```
350 = 0000 0001 0101 1110 (16 bit) ✅
```

Senza il cast, l'operazione avviene su 8 bit e il riporto viene perso.

**Calcolo binario con overflow:**

```
Riporti: 1 0 0 0 0 0 0 0 
          ─────────────────
           1 0 0 1 0 1 1 0  + (150₁₀)
           1 1 0 0 1 0 0 0    (200₁₀)
          ─────────────────
         1 0 1 0 1 1 1 1 0  (9 bit = 350₁₀)
         ↑ riporto perso! OVERFLOW
           
Rimangono 8 bit: 0101 1110 = 94₁₀ ❌
```


**Cosa succede ai bit quando si verifica overflow su unsigned:**

- Il riporto oltre l 'MSB viene **scartato** 
- Il risultato è equivalente a: `(a + b) mod 256`
- ❌ Non c'è errore segnalato, il programma continua normalmente

---

### Domanda 3: Operazioni per `risultato2` (signed positivi)


**Operazione 2a: 100 + 25 = 125**

**Risultato ottenuto:** 125 ✅  
**Risultato teorico:** 125 ✅  
**Risultato:** CORRETTO

**Calcolo binario:**

```
 Riporti 0 0 0 0 0 0 0 0
           0 1 1 0 0 1 0 0         100 +         
           0 0 0 1 1 0 0 1          25 =
         ------------------        ------
           0 1 1 1 1 1 0 1 ✅          125
  
Bit di segno = 0 (positivo) ✅
Riporto colonna n: 0, segno risultante MSB 0 quindi + 
Riporto colonna n+1:0 
Tutto ok!
```

---

**Operazione 2b: 100 + 65 = 165**

**Risultato ottenuto:** -91 ❌  
**Risultato teorico:** 165 ✅  
**Risultato:** ERRATO (overflow signed)

**Perché un'addizione di due numeri positivi dà un numero negativo?**

Perché si verifica **overflow signed**. Il risultato supera il valore massimo rappresentabile su `int8_t`.

**Calcolo binario:**

```
  100 = 0110 0100
+  65 = 0100 0001
--------------------
  165 = 1010 0101
        ↑ bit di segno = 1 → interpretato come negativo!
        

Riporti:  0 1 0 0 0 0 0 0 0
           ─────────────────
            0 1 1 0 0 1 0 0  (+100₁₀)
          + 0 1 0 0 0 0 0 1  (+65₁₀)
           ─────────────────
            1 0 1 0 0 1 0 1  (-91₁₀) ❌ OVERFLOW!
            ↑
            └─ MSB = 1 (negativo, ma dovrebbe essere positivo!)
Bit di segno = 1 (negativo) ❌
Riporto colonna n: 1, segno risultante MSB 1 quindi - ❌
Riporto colonna n+1:0  0 non ho fatto il giro, non è un riporto che posso scartare
I riporti n e n+1 sono diversi, infatti ho overflow!
```

**Interpretazione come CA2:**

```
1010 0101 in CA2:
Complemento a 1: 0101 1010
+1:              0101 1011 = 91
Quindi: 1010 0101 = -91 ❌
```

**Valore massimo rappresentabile su `int8_t`:** 127 (0111 1111)

Quando superiamo 127, il bit di segno diventa 1 e il numero viene interpretato come negativo.

---

### Domanda 4: Operazioni per `risultato3` (signed negativi)

 **Operazione 3a: -80 + (-20) = -100**

**Risultato ottenuto:** -100 ✅  
**Risultato teorico:** -100 ✅  
**Risultato:** CORRETTO

**Calcolo binario CA2:**

```
-80 in CA2:
  80 = 0101 0000
  Complemento a 1: 1010 1111
  +1:              1011 0000 = -80

-20 in CA2:
  20 = 0001 0100
  Complemento a 1: 1110 1011
  +1:              1110 1100 = -20

Addizione:
R:11100 000
   1011 0000  (-80)
 + 1110 1100  (-20)
 --------------------
 1 1001 1100  (riporto oltre 8 bit viene scartato)
  ↑
  1001 1100 = -100 in CA2 ✅

Bit di segno = 1 (negativo)
Riporto colonna n: 1 e MSB dice 1
Riporto colonna n+1: 1 sono passato dalla base per operazione complemento che trasforma sottrazione in somma
I riporti sono uguali, il bit oltre MSB non è overflow e lo posso scartare perché scarto il passaggio dalla base (cerchio)!```

**Verifica:**
1001 1100 in CA2:
Complemento a 1: 0110 0011
+1:              0110 0100 = 100
Quindi: 1001 1100 = -100 ✅
```


---

 **Operazione 3b: -80 + (-60) = -140**

**Risultato ottenuto:** 116 ❌  
**Risultato teorico:** -140 ✅  
**Risultato:** ERRATO (overflow signed)

**Perché l'addizione di due numeri negativi dà un numero positivo?**

Perché si verifica overflow: il risultato supera il valore minimo rappresentabile (-128).

**Calcolo binario CA2:**

```
-60 in CA2:
  60 = 0011 1100
  Complemento a 1: 1100 0011
  +1:              1100 0100 = -60

Addizione:
R: 1 0000 000
     1011 0000  (-80)
   + 1100 0100  (-60)
  --------------------
  1  0111 0100  (riporto oltre 8 bit viene scartato)
  ↑
  0111 0100 = 116 (positivo!) ❌

Bit di segno = 0 (positivo) ❌
Riporto colonna n: 0 MSB dice positivo ma stiamo sommando due negativi
Riporto colonna n+1: 1  ho fatto il giro, ma sto superando i numeri rappresentabili! 
I riporti sono diversi, ho un overflow   ❌
```

Il bit di segno diventa 0, quindi il numero viene interpretato come positivo.

**Verifica:** -140 + 256 = 116 ✅

---

### Domanda 5: Operazione 4 (100 + (-80) = 20)

**Risultato ottenuto:** 20 ✅  
**Risultato teorico:** 20 ✅  
**Risultato:** CORRETTO

**Si verifica overflow?** NO ✅  

**Calcolo binario CA2:**

```
R:1 1100  000 
    0110 0100 +         (100) +
    1011 0000            (-80)=
--------------------     
  1 0001 0100  (riporto viene scartato)
  ↑
  0001 0100 = 20 ✅
  
Bit di segno = 0 (positivo) sommando numeri discorsi può andare bene✅  
Riporto colonna n: 1 
Riporto colonna n+1: 1 sono passato dalla base per operazione complemento che trasforma sottrazione in somma
I riporti sono uguali, il bit oltre MSB non è overflow e lo posso scartare perché scarto il passaggio dalla base (cerchio)!```
```

Questo è un caso di **scarto base** in CA2: il riporto oltre l'8° bit viene ignorato, ma il risultato è corretto perché non c'è overflow.

**Condizioni per overflow in addizione signed:**

- Due positivi → risultato negativo ❌
- Due negativi → risultato positivo ❌
- Un positivo + un negativo → **MAI overflow** ✅

---

### Domanda 6: Operazione 5 (150 - 200 = -50)

**Risultato ottenuto:** 206 ❌  
**Risultato teorico:** -50 ✅  
**Risultato:** ERRATO (problema di interpretazione)

**Perché è errato?**

La variabile `risultato5` è `uint8_t` (unsigned), ma il risultato dell'operazione è **negativo**. Un tipo unsigned non può rappresentare numeri negativi.

**Calcolo binario:**

```
150 - 200 = 150 + (-200) in CA2

-200 in CA2 (8 bit):
200 mod 256 = 200
200 = 1100 1000
Complemento a 1: 0011 0111
+1:              0011 1000 = -200 (in signed)

Addizione:
  1001 0110  (150)
+ 0011 1000  (-200)
--------------------
  1100 1110 = 206 (interpretato come unsigned) ❌
            = -50 (se fosse signed) ✅
```

**Cosa succede alla rappresentazione?**

La sequenza di bit `1100 1110`:

- **Come unsigned (uint8_t):** 206 ❌
- **Come signed (int8_t):** -50 ✅

**Verifica CA2:**

```
1100 1110 in CA2:
Complemento a 1: 0011 0001
+1:              0011 0010 = 50
Quindi: 1100 1110 = -50 ✅
```

Per stampare correttamente il valore teorico, il codice usa:

```c
printf("Risultato teorico: %d\n", (int16_t)a - (int16_t)b);
```

Questo effettua l'operazione su 16 bit signed, permettendo di rappresentare -50 correttamente.

---

## Parte 3: Domande Extra

### 1. Conversione manuale: -80 in CA2 su 8 bit

**Passaggi:**

1. **Valore assoluto:** 80
    
2. **Conversione in binario:**
    
    ```
    80 ÷ 2 = 40 resto 0
    40 ÷ 2 = 20 resto 0
    20 ÷ 2 = 10 resto 0
    10 ÷ 2 = 5  resto 0
    5  ÷ 2 = 2  resto 1
    2  ÷ 2 = 1  resto 0
    1  ÷ 2 = 0  resto 1
    
    80 = 0101 0000 (8 bit)
    ```
    
3. **Complemento a 1:** Invertire tutti i bit
    
    ```
    0101 0000 → 1010 1111
    ```
    
4. **Aggiungere 1:**
    
    ```
      1010 1111
    +         1
    -----------
      1011 0000
    ```
    

**Risultato:** `-80 = 1011 0000` in CA2 su 8 bit ✅

---

### 2. Limiti teorici

|Tipo|Numero di bit|Valore Minimo|Valore Massimo|
|---|---|---|---|
|`uint8_t`|8|**0**|**255**|
|`int8_t`|8|**-128**|**127**|
|`uint16_t`|16|**0**|**65535**|
|`int16_t`|16|**-32768**|**32767**|

**Formule:**

- **Unsigned n bit:**
    
    Min = 0, Max = $2^n - 1$
    
- **Signed n bit (CA2):**
    
    Min = $-2^{\,n-1}$, Max = $2^{\,n-1} - 1$


---

### 3. Previsione

#### **Caso 1:** `int8_t x = 127; x = x + 1;`

**Previsione:** `x = -128` ❌

**Spiegazione:**

```
127 = 0111 1111 (valore massimo int8_t)
+1  = 0000 0001
-----------------
128 = 1000 0000 → interpretato come -128 in CA2
```

**Overflow signed:** il valore "avvolge" dal massimo al minimo.

---

#### **Caso 2:** `uint8_t y = 0; y = y - 1;`

**Previsione:** `y = 255` ❌

**Spiegazione:**

```
0 - 1 = 0 + (-1)

-1 in CA2 (8 bit):
1 = 0000 0001
Complemento a 1: 1111 1110
+1:              1111 1111 = 255 (in unsigned)

Risultato: 1111 1111 = 255
```

**Underflow unsigned:** il valore "avvolge" da 0 a 255.

---

