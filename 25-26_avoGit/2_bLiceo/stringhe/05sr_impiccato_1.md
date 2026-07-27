Simula la logica del gioco dell'impiccato. La **parola segreta** viene scelta **casualmente da una lista di 6 parole**. Lo studente deve **simulare passo passo il funzionamento del programma**.

- L’utente può provare a indovinare **una lettera alla volta**.
    
- Sono concessi **5 tentativi**; ogni volta che l’utente sbaglia, i tentativi diminuiscono.
    
- Dopo ogni inserimento, il programma deve **stampare lo stato attuale della parola**, mostrando le lettere indovinate e `_` per quelle non ancora indovinate.
    
- Se l’utente esaurisce i tentativi senza indovinare tutta la parola, il programma deve stampare: Hai perso! La parola era: <parola_segreta>

Ad ogni tentativo il programma stampa lo stato (lettere tentate e output attuale)

- **Esempio:**
    
    - **Parola segreta:** `"programmazione"` (<= non stampare)
        
    - **Lettere tentate:** `['a', 'o', 'r']`
        
    - **Output atteso:** `_ r o _ r a _ _ a _ _ _ o _ _`


- **Simulare a mano o con carta e penna il funzionamento del programma per capire come cambiano i tentativi e lo stato della parola.**


**Suggerimento:**

- Le stringhe in Python sono immutabili.
    
- Se vuoi “comporre” una stringa a partire da più caratteri o aggiornarla passo passo, puoi usare **una lista di caratteri modificabile** e poi trasformarla in stringa con `"".join(lista_caratteri)`.