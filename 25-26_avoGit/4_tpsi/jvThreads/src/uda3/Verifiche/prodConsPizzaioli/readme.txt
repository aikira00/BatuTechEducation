# Pizzeria Mamma Mia! - Istruzioni

## File necessari
1. BanconeCaldo.java - Classe Monitor per la sincronizzazione
2. Pizzaiolo.java - Thread produttore
3. Fattorino.java - Thread consumatore
4. PizzeriaMain.java - Classe principale

## Compilazione
Aprire il terminale nella cartella contenente i file e eseguire:
```bash
javac *.java
```

## Esecuzione
```bash
java PizzeriaMain
```

## Struttura della consegna
Creare un file .zip con nome `<nomecognome>_jvthmnt.zip` contenente:
- BanconeCaldo.java
- Pizzaiolo.java
- Fattorino.java
- PizzeriaMain.java
- README.txt (questo file)

## Note sulla sincronizzazione
- Il Monitor (BanconeCaldo) usa `synchronized` sui metodi
- Le condizioni di attesa usano `while` loops con `wait()`
- Si usa `notifyAll()` per svegliare tutti i thread in attesa
- I thread vengono fermati in modo sicuro con flag booleano e interrupt

## Output atteso
La simulazione dura 45 secondi e mostra:
- Messaggi in tempo reale delle operazioni
- Statistiche finali per ogni pizzaiolo e fattorino
- Premio al fattorino più efficiente
- Messaggio speciale se non ci sono sprechi

## Easter Egg
Se Chef Mario sforna esattamente 5 pizze, apparirà un messaggio speciale!