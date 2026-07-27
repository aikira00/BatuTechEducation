## **Esercizio – Sequenza di Collatz su più numeri**

  

Scrivi un programma che permetta di **calcolare la sequenza di Collatz per più numeri interi positivi**, utilizzando le **liste**. 
### **Fase 1 – Inserimento dati**
Il programma deve:

1. Chiedere ripetutamente all’utente di inserire un **numero intero positivo**.
    
2. Terminare l’inserimento quando l’utente inserisce un **numero negativo**.
    
3. Salvare tutti i numeri validi inseriti in una **lista**.
### **Fase 2 – Calcolo della sequenza**
Per ciascun numero presente nella lista, il programma deve:

1. Calcolare la **sequenza di Collatz** fino ad arrivare al valore 1, applicando le seguenti regole:
    
    - se il numero è **pari**, dividerlo per 2
        
    - se il numero è **dispari**, moltiplicarlo per 3 e aggiungere 1
        
    
2. Salvare **tutti i valori generati** in una nuova lista.
    
3. Contare il **numero di passi** necessari per raggiungere 1.
4. **Aggiungere la lista della sequenza di Collatz alla lista che contiene tutte le sequenze.**
    
### **Fase 3 – Output**
  
Alla fine, per ogni numero inserito, il programma deve stampare:

- il **numero iniziale**
    
- il **numero di passi** effettuati
    
- la **sequenza di Collatz**, leggendo i valori dalla lista
### **Esempio di output**

```
Hai inserito 2 numeri per calcolare la sequenza di Collatz... procediamo

Il numero 7 ha richiesto 16 passi
La sequenza di Collatz per il numero 7 è: 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1

Il numero 10 ha richiesto 6 passi
La sequenza di Collatz per il numero 10 è: 5 16 8 4 2 1

Fine calcolo
```
### **Vincoli**

- È obbligatorio usare:
    
    - almeno **una lista** per salvare i numeri inseriti
        
    - una **lista** per salvare i valori della sequenza di Collatz
        
    - cicli while e for
        

- Non usare funzioni predefinite per il calcolo della sequenza.
    

### **Fase 4 – Considerazioni sull'andamento**

Dopo aver stampato tutte le sequenze, osserva i valori stampati e scrivi una breve considerazione (come commento nel codice) su:

- Quale sequenza ha richiesto **più passi**?
- Quale sequenza ha raggiunto il **valore massimo** più alto durante il percorso?
- Noti qualche **pattern comune** nell'andamento delle sequenze?
