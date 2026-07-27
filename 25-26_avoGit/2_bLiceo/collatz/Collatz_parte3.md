Scrivi un programma che **calcoli la sequenza di Collatz** per un numero intero positivo inserito dall’utente

(riprendi l’esercizio della **parte I**).

  

Il programma deve:

1. Chiedere all’utente un **numero intero positivo**.
    
2. Calcolare la **sequenza di Collatz** applicando le seguenti regole fino a quando il valore diventa 1:
    
    - se il numero è **pari**, dividilo per 2
        
    - se il numero è **dispari**, moltiplicalo per 3 e aggiungi 1
        
    
3. **Salvare tutti i valori generati** (escluso il numero iniziale) in una **lista**.
    
4. Contare il **numero di passi** necessari per arrivare a 1.
    
5. Alla fine, il programma stampa:
    
    - il **numero iniziale** e il **numero di passi** effettuati
        
    - la **sequenza di Collatz**, leggendo e stampando i valori contenuti nella lista

**Esempio di output**
Il numero 7 richiede : 16 passi
La sequenza di Collatz per il numero 7 è
22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1