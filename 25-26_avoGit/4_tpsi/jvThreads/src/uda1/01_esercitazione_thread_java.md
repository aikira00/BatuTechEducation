# JVTH01 - Crea un thread

Implementa e testa due diverse modalità di creazione e gestione dei thread in Java utilizzando l'interfaccia Runnable e l'estensione della classe Thread.

Crea due classi Java separate: una per l'implementazione dell'interfaccia Runnable e l'altra per l'estensione della classe Thread. Nella classe che implementa l'interfaccia Runnable, il thread verrà ciclicamente messo in pausa per 6 secondi, per un totale di 5 cicli consecutivi. Nella classe che estende la classe Thread, il thread verrà ciclicamente messo in pausa per un numero casuale di secondi, compreso tra 0 e 3, per 5 cicli consecutivi.

Successivamente, crea una classe Java in cui il metodo main crea un'istanza per ciascun tipo di thread e li avvia. Durante l'esecuzione, utilizza il metodo Thread.currentThread().getName() per ottenere il nome del thread corrente e il metodo Thread.sleep(ms) per mettere il thread in pausa per un determinato numero di millisecondi.

Inoltre, gestisci eventuali eccezioni derivanti dall'interrompere il thread durante il sonno utilizzando un blocco try-catch.

Si osservi come l’esecuzione dei tre thread risulti indipendente… quando termina il thread main? 

Vedere java.util.Random per generare numero random [https://docs.oracle.com/javase/8/docs/api/java/util/Random.html](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)