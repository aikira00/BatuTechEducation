
# Esercizio 1

Fare grafo precedenze, pseudocodice fork/join e codice Java con i thread. Prevedere che i threads abbiano un metodo per recuperare il risultato dal thread chiamante. Le variabili vengono passate ai threads con il costruttore.

z = (3-2) * (5+4)

![[JVTH10_grafiPrec_Join.png]]

# Esercizio 2

Data l'espressione 

  

p = 2*(3 + 2) + (7 – 4) + [(2*3)*(5-1)] = 37

  

costruire il diagramma delle precedenze, e il codice Java con i thread per entrambe le versioni proposte.

  

versione 1: il thread main recupera c1 e c2 e calcola C

versione 2: il thread main crea un altro thread per calcolare c2 e recupera il valore C 

  

Il main principale deve recuperare i calcoli dai singoli thread. Prevedere che i threads abbiano un metodo per recuperare il risultato dal thread chiamante. Le variabili vengono passate ai threads con il costruttore.

![[JVTH09 - grafi prec join.png]]

# Esercizio 3 colori

Dopo aver realizzato il diagramma delle precedenze,scrivere un’applicazione che calcoli la seguente espressione utilizzando la

programmazione parallela con thread

(3a+5)/(b-c) *(3a+5c)+(7-a/b+22)/(c+b+a)
![[Screenshot 2025-03-17 at 11.32.57.png]]

Realizzare un thread per l’intera espressione. Il thread nel suo run creerà e farà partire

due thread: un thread per la parte nelle tonalità di giallo e uno per quella nelle tonalità in

verde. All’interno di ognuno di esso verranno fatti partire altri due: uno per ogni tonalità. Le

variabili vengono passate ai thread nei costruttori. Tutti i thread avranno un metodo

getValue che restituirà il valore del calcolo effettuato. Tale metodo verrà utilizzato dal

thread chiamante per svolgere i suoi calcoli.