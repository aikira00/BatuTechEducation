## **Esercizio – Sequenza di Collatz su più numeri** - grafico

  
**Motivazione: Perché visualizzare graficamente?**

Osservare le sequenze di Collatz solo come numeri stampati a terminale rende difficile capire l'andamento complessivo. Un grafico permette di:

- Vedere immediatamente se una sequenza ha picchi alti o rimane bassa
- Confrontare visivamente diverse sequenze
- Osservare pattern e comportamenti interessanti

Per disegnare grafici in Python utilizziamo la libreria **matplotlib**, che fornisce funzioni simili a quelle di MATLAB per creare grafici di qualità professionale.
### **Struttura dati necessaria**

Per disegnare il grafico di una sequenza di Collatz abbiamo bisogno di due informazioni per ogni sequenza calcolata:

1. **Asse X**: il numero di passo (0, 1, 2, 3, ...)
2. **Asse Y**: i valori della sequenza di Collatz

**Esempio concreto**: per il numero iniziale 5, la sequenza è `[5, 16, 8, 4, 2, 1]`

- **Passi (asse X)**: `[0, 1, 2, 3, 4, 5]`
- **Valori (asse Y)**: `[5, 16, 8, 4, 2, 1]`

### **Modifica del programma della Parte 4**

Riprendi il programma della Parte 4 e modificalo salvando tutte le sequenze calcolate in una **lista di liste**.

**Crea due nuove liste vuote all'inizio del programma:**
lista_sequenze_collatz = []  # Lista che conterrà tutte le sequenze


**Durante il ciclo che calcola le sequenze**, dopo aver calcolato ciascuna sequenza di Collatz:

**Salva la sequenza appena calcolata**
```python
lista_sequenze_collatz.append(sequenza)
```


**Esempio**: se l'utente ha inserito i numeri `5` e `45`:

- `lista_sequenze_collatz[0]` contiene la sequenza per 5: `[5, 16, 8, 4, 2, 1]`
- `lista_sequenze_collatz[1]` contiene la sequenza per 45

Stessa cosa per la lista di passi (ma possiamo farne a meno perché i passi possono essere ricavati dalla lunghezza della sequenza)
### **Visualizzazione grafica**

Alla fine del programma, chiedi all'utente di quale numero vuole visualizzare la sequenza sotto forma di grafico e copia e completa il codice sottostante.

```python
import matplotlib.pyplot as plt

# Chiedi all'utente di quale numero vuole visualizzare la sequenza
print(f"\nSono state calcolate {len(lista_sequenze_collatz)} sequenze.")
numero = int(input("Quale sequenza vuoi visualizzare? (0 per la prima, 1 per la seconda, ...): "))

# CONTROLLA CHE UTENTE ABBIA INSERITO UN NUMERO PER CUI ˜E STATA CALCOLATA LA SEQUENZA DI COLLATZ altrimenti index ferma il proramma con errore

	indice = input_list_collatz.index(numero)
   
	# COMPLETA QUI CON IL CODICE MANCANTE PER RECUPERARE LA SEQUENZA DI COLLATZ 3
	# E I PASSI ASSOCIATI
	
	# Crea il grafico
	plt.plot(<QUI PASSA LISTA dei passi da 0 a passi necessari> , <QUI PASSA SEQUENZA COLLATZ PER NUMERO SCELTO>, marker='o', color='blue', linewidth=2)
	plt.title(f"Sequenza di Collatz per n={<QUI PASSA NUMERO SCELTO}")
	plt.xlabel("Passo")
	plt.ylabel("Valore")
	plt.grid(True, alpha=0.3)
	plt.show()

```

- **`import matplotlib.pyplot as plt`**: importa la libreria per disegnare grafici
- **`plt.plot(x, y, marker='o')`**: disegna un grafico a linee dove:
    - `x` è la lista dei valori per l'asse X (i passi)
    - `y` è la lista dei valori per l'asse Y (la sequenza)
    - `marker='o'` aggiunge un pallino su ogni punto
    - `color='blue'` imposta il colore della linea
    - `linewidth=2` imposta lo spessore della linea
- **`plt.title(...)`**: imposta il titolo del grafico
- **`plt.xlabel(...)` e `plt.ylabel(...)`**: etichette degli assi
- **`plt.grid(True)`**: aggiunge una griglia di riferimento
- **`plt.show()`**: mostra il grafico in una finestra
### **Esempio di output atteso**

Se l'utente inserisce i numeri `7` e `27`, e poi sceglie di visualizzare la sequenza di Collatz dell numero  7, il programma mostrerà un grafico con:

- Titolo: "Sequenza di Collatz per n=7"
- Asse X da 0 a 16 (ci sono 17 valori nella sequenza) => i passi
- Asse Y che mostra i valori: 7 → 22 → 11 → 34 → 17 → 52 → 26 → 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1 => la sequenza