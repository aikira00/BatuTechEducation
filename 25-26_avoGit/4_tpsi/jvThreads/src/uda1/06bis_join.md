Dopo aver realizzato il diagramma delle precedenze,scrivere un’applicazione che calcoli la seguente espressione utilizzando la

programmazione parallela con thread

(3a+5)/(b-c)*(3a+5c)+(7-a/b+22)/(c+b+a)

(3a+5)/(b-c) gialla
*(3a+5c) arancione
(7-a/b+22) verde chiaro
(c+b+a) verde scuro

Realizzare un thread per l’intera espressione. Il thread nel suo run creerà e farà partire

due thread: un thread per la parte nelle tonalità di giallo e uno per quella nelle tonalità in

verde. All’interno di ognuno di esso verranno fatti partire altri due: uno per ogni tonalità. Le

variabili vengono passate ai thread nei costruttori. Tutti i thread avranno un metodo

getValue che restituirà il valore del calcolo effettuato. Tale metodo verrà utilizzato dal

thread chiamante per svolgere i suoi calcoli.