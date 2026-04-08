

# 🧪 **Esercitazione: Produttore-Consumatore con Buffer Circolare**

### 🎯 **Obiettivo**

Realizzare un sistema multi-thread in cui più produttori e consumatori accedono in modo sincronizzato a un **buffer circolare condiviso** (array di interi).


## 📋 **Consegna**

1. **Classe `ProductBuffer`**:
    
    - Deve contenere un array di interi come buffer e tre variabili per tracciare lo stato del buffer:
        
        - **`in`**: indice per l'inserimento dei dati. (produrre)
        -  **`out`**: indice per la lettura dei dati. (consumare)
        - **`count`**: numero di elementi attualmente presenti nel buffer. 
            
    - La classe deve gestire una **dimensione fissa** (`size`), passata tramite costruttore.
    - Durante l'implementazione del buffer circolare, è importante che il buffer sia gestito in modo tale che i produttori non cerchino di scrivere quando è pieno, e che i consumatori non cerchino di leggere quando è vuoto. Utilizza il contatore (`count`) per tracciare quanti elementi sono presenti nel buffer e gestisci l'accesso al buffer tramite i metodi sincronizzati.
        
2. **Metodi sincronizzati**:
    
    - `produce(int value)` → inserisce un valore nel buffer (solo se c'è spazio) all'indice `in` nell'array e aggiorna indice in. Se il buffer è pieno, il produttore deve aspettare fino a che non si liberi spazio. Buffer pieno quando count == size
        
    - `consume()` → preleva un valore dal buffer (solo se non è vuoto) dall'inidice `out` dell'array e aggiorna indice out. Se il buffer è vuoto, il consumatore deve aspettare fino a che non arrivi un nuovo valore. Buffer vuoti quando count == 0
    
    I metodi `produce()` e `consume()` devono essere sincronizzati utilizzando la parola chiave `synchronized`. Utilizza `wait()` per far attendere i thread e `notifyAll()` per svegliare i thread in attesa.
    
3. **Sincronizzazione tramite `wait()` e `notifyAll()`**:
    
    - Il produttore deve utilizzare `wait()` quando il buffer è pieno.
        
    - Il consumatore deve utilizzare `wait()` quando il buffer è vuoto.
        
    - Utilizza `notifyAll()` per svegliare i thread in attesa dopo ogni inserimento o rimozione. 
        
4. **Buffer Circolare**:
    
    - Implementa il comportamento circolare del buffer utilizzando l'operatore modulo per aggiornare indici in e out quando si produce e si consuma:
        
    
    ```java
    in = (in + 1) % size;
    out = (out + 1) % size;
    ```
    
5. **Thread Produttori e Consumatori**:
    
    - Crea almeno **2 thread produttori** e **2 thread consumatori**. Ogni thread deve produrre o consumare 5 valori.
        
    - Ogni thread produttore inserisce 5 valori nel buffer e ogni thread consumatore ne preleva 5.
        
    
    Usa i metodi `produce()` e `consume()` per interagire con il buffer.


## 📐 **Esperimento suggerito**

Imposta la **dimensione del buffer (`size`)** inizialmente a 3. Poi prova a cambiarla (es. 1 o 5) e osserva come cambia il comportamento del sistema (attese, interleaving tra thread, ecc.).

---

## 📌 **Consegna**

Crea un progetto con le seguenti classi:

- `ProductBuffer`: gestisce il buffer circolare e la sincronizzazione.
    
- `Producer`: rappresenta un thread produttore.
    
- `Consumer`: rappresenta un thread consumatore.
    
- `Main`: classe principale per eseguire il programma.
    

Aggiungi commenti al codice per spiegare come funziona la sincronizzazione.

Esegui il programma più volte per osservare come variano le interazioni tra i thread.

