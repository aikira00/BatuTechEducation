
Scrivi un programma che cripti una parola usando una "chiave" numerica fornita sotto forma di lista. Ogni lettera della parola deve essere spostata in avanti nell'alfabeto di un numero di posizioni indicato dalla lista.

- **Regola:** Se la lista di numeri è più corta della parola, il programma deve ricominciare a leggere la lista dall'inizio (ciclicamente).
    
- **Esempio:**
    
    - **Parola:** `"ciao"`
        
    - **Chiave:** `[1, 2]`
        
    - **Risultato:** `"dkbq"` (c+1, i+2, a+1, o+2)
        
- **Sfida:** Come puoi usare l'operatore modulo (`%`) per far sì che la chiave non "finisca" mai rispetto alla lunghezza della parola?
    

---`