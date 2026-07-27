
### ⚠️ **Attenzione alla stampa dei valori teorici!**

Per stampare correttamente il valore teorico di un'operazione, è necessario utilizzare un tipo di dato con una rappresentazione più ampia (maggior numero di bit) rispetto agli operandi originali.

#### Esempio con `risultato1` (unsigned):

Se la variabile `risultato1` è di tipo **senza segno** (`unsigned`), per calcolare e stampare il valore teorico dovete effettuare il cast a `uint16_t`:

```c
printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b);
```

#### Esempio con `risultato2` (signed):

Se la variabile `risultato2` è di tipo `int8_t` (quindi **con segno**), per calcolare e stampare il valore teorico dovete effettuare il cast a `int16_t`:

```c
printf("Risultato teorico: %d\n", (int16_t)a + (int16_t)b);
```

 #### Riepilogo formato printf:

|Tipo di dato|Specificatore di formato|
|---|---|
|`uint16_t` (unsigned)|`%u`|
|`int16_t` (signed)|`%d`|
|`uint8_t` (unsigned)|`%u`|
|`int8_t` (signed)|`%d`|
Quando usi lo specificatore `%u` (unsigned) per stampare un valore **signed**, il compilatore interpreta i bit del numero come se fossero **senza segno**, causando risultati errati per i valori negativi.

#####  Uso di `%u` con un tipo `signed`

Quando usi lo specificatore `%u` (unsigned) per stampare un valore **signed**, il compilatore interpreta i bit del numero come se fossero **senza segno**, causando risultati errati per i valori negativi.

```c
#include <stdio.h>
#include <stdint.h>

int main() {
    int8_t valore_signed = -1;
    
    printf("Con %%d (corretto): %d\n", valore_signed);
    printf("Con %%u (errato):   %u\n", valore_signed);
    
    return 0;
}
```

**Output:**

```
Con %d (corretto): -1
Con %u (errato):   255
```

### 🔍 Perché succede questo?

1. **In memoria**, `-1` come `int8_t` è rappresentato in complemento a 2: `11111111` (0xFF)
2. Quando usi `%u`, il printf interpreta questi bit come **unsigned**
3. `11111111` interpretato come unsigned = **255**

### Tabella di conversione (int8_t → uint8_t):

|Valore signed|Binario (complemento a 2)|Interpretato come unsigned|
|---|---|---|
|-1|`11111111`|255|
|-2|`11111110`|254|
|-128|`10000000`|128|
|0|`00000000`|0|
|127|`01111111`|127|
 Conseguenze:

- ✅ **Valori positivi**: funzionano correttamente (stessa rappresentazione)
- ❌ **Valori negativi**: diventano numeri unsigned molto grandi
- ⚠️ **Warning del compilatore**: alcuni compilatori avvisano di questo mismatch, in C no!!!!!!

**Usa sempre lo specificatore corretto:**

- `%d` per tipi `signed` (int8_t, int16_t, int32_t)
- `%u` per tipi `unsigned` (uint8_t, uint16_t, uint32_t)