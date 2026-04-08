## Concetti Chiave

1. **Interruzione di un thread**: Un thread può essere interrotto chiamando `interrupt()`, ma non viene forzatamente terminato.
    
2. **Stato di interruzione**: Il flag di interruzione di un thread viene azzerato quando viene catturata un'eccezione `InterruptedException`. Questo può creare comportamenti inaspettati


Leggere specifiche documentazione ufficiale
https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#isInterrupted--
https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupted--
https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--


In Java, i thread possono essere interrotti utilizzando il metodo `interrupt()`. Questo metodo non provoca un arresto immediato del thread, ma imposta un flag di interruzione, che il thread può controllare per terminare in modo ordinato. Il flag di interruzione viene automaticamente resettato a `false` se il thread viene interrotto mentre è in stato di attesa o di sleep, generando un'eccezione `InterruptedException`, oppure può essere azzerato manualmente tramite la chiamata al metodo statico `Thread.interrupted()`.
## Attività 1: Creazione di un Thread con un Contatore con for
1. Creazione della classe `MyThread`
Questa classe estende `Thread` e implementa un contatore che simula un'attività all'interno del thread.

```java
public class MyThread extends Thread {
    private String name;
    private int counter;

    public MyThread(String name, int counter) {
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " avviato.");
        try {
            for (int i = 0; i < counter; i++) {
                if (Thread.interrupted()) { // Controllo del flag di interruzione
                    System.out.println("Thread " + name + " interrotto.");
                    return; // Terminazione pulita del thread
                }
                System.out.println("Thread " + name + ": Contatore = " + i);
                Thread.sleep(1000); // Simula un'attività del thread
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + " interrotto durante sleep.");
        }
        System.out.println("Thread " + name + " terminato.");
    }
}
```

2. Creazione della classe `InterruptExample`
Questa classe avvia il thread e lo interrompe dopo un certo periodo di tempo.

```java
public class InterruptExample {
    public static void main(String[] args) {
        // Creazione e avvio del thread
        Thread myThread = new MyThread("Thread-1", 5);
        myThread.start();

        System.out.println("Thread principale: Il thread è stato avviato.");

        // Aspetta un po' prima di interrompere il thread
        try {
            System.out.println("Thread principale: Aspetto 2 secondi prima di interrompere il thread.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrompi il thread
        System.out.println("Thread principale: Interrompo il thread.");
        myThread.interrupt();
        System.out.println("Thread principale: thread interrotto.");
    }
}
```

### Spiegazione del Codice

1. `MyThread` esegue un ciclo fino a `counter`, simulando un'attività con `Thread.sleep(1000);`.
2. Se il thread viene interrotto, il flag di interruzione viene rilevato:
    - Il metodo `Thread.interrupted()` restituisce `true`, e il thread termina immediatamente.
    - Se l'interruzione avviene durante `Thread.sleep()`, viene generata un'`InterruptedException`, gestita con un messaggio di terminazione.
3. La classe `InterruptExample` avvia un thread e lo interrompe dopo 2 secondi.

### Output Previsto

L'output potrebbe apparire come segue:

```
Thread principale: Il thread è stato avviato.
Thread Thread-1 avviato.
Thread Thread-1: Contatore = 0
Thread principale: Aspetto 2 secondi prima di interrompere il thread.
Thread Thread-1: Contatore = 1
Thread Thread-1: Contatore = 2
Thread principale: Interrompo il thread.
Thread principale: thread interrotto.
Thread Thread-1 interrotto.
```

### Conclusione

'uso di `interrupt()` nei thread permette una gestione controllata dell'interruzione, evitando la terminazione forzata e consentendo al thread di chiudersi in modo ordinato. Questo approccio è fondamentale per scrivere codice multi-threading robusto ed efficiente. In caso di eccezione, è una buona pratica chiudere eventuali risorse utilizzate dal thread, come file o connessioni di rete, per evitare perdite di memoria o blocchi indesiderati.


## Attività 2: Creazione di un Thread con un Contatore con while

1. Creazione della classe `MyThread`
Questa classe estende `Thread` e implementa un contatore che simula un'attività all'interno del thread.

```java
class MyThread extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");
        }
        System.out.println("Sono vivo: " + this.isAlive());
        // Utilizzando interrupted(), possiamo controllare lo stato dell'interruzione
        if (Thread.interrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }
    }
}
```

2. Creazione della classe `L0200InterruptWhileV1`
Questa classe avvia il thread e lo interrompe dopo un certo periodo di tempo.

```java
public class MainInterruptWhile {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(500); // Attendiamo 2 secondi
        } catch (InterruptedException e) {
            System.out.println("Main interrotto");
        }
        // Interrumpiamo il thread dopo 2 secondi
        myThread.interrupt();
        System.out.println("Ho interrotto il thread...");
        System.out.println(myThread.getName() + " è interrotto: " + myThread.isInterrupted());
        System.out.println(myThread.getName() + " è interrotto: " + myThread.isInterrupted());
        System.out.println(myThread.getName() + " è vivo: " + myThread.isAlive());
    }
}
```

3. Ora modifichiamo il thread `MyThread` in modo che dorma per un breve periodo all'interno del ciclo `while`.
```java
class MyThread extends Thread {
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("Thread in esecuzione...");
            try {  
		    Thread.sleep(500); // Simuliamo un'attività  
		} catch (InterruptedException e) {  
		    System.out.println("Thread interrotto!"); //così abbiamo shadow exception  
		}
       }
        System.out.println("Sono vivo: " + this.isAlive());
        // Utilizzando interrupted(), possiamo controllare lo stato dell'interruzione
        if (Thread.interrupted()) {
            System.out.println("L'interruzione è stata segnalata.");
        } else {
            System.out.println("L'interruzione non è stata segnalata.");
        }
    }
}
```


Che cosa succede con questa modifica? perché il thread MyThread non termina esecuzione?

### Esperimento con `**isInterrupted()**` 
Rimuovi eventuali break e return nell'eccezione all'interno del metodo run. Sostituisci `Thread.interrupted()` con `this.isInterrupted()`. Il comportamento è lo stesso? 


## Domande di verifica

1. Qual è lo scopo del metodo `interrupt()` in Java?
2. Il metodo `interrupt()` ferma immediatamente un thread? Perché?
3. Perché l’uso di `return;` all’interno del blocco `catch` garantisce l’interruzione del thread?
4. Cosa succede quando un thread in stato di sleep viene interrotto?
5. Qual è la differenza tra `Thread.interrupted()` e il metodo `isInterrupted()`?
6. Perché è importante controllare periodicamente il flag di interruzione all'interno del metodo `run()`?
7. Qual è la differenza tra `Thread.interrupted()` e `isInterrupted()`?
8. Come si può verificare se un thread è ancora attivo?
9. Perché `InterruptedException` azzera lo stato di interruzione del thread?
10. Cosa succede se un thread non gestisce correttamente l'interruzione? Quali potrebbero essere le conseguenze?
    


## Attività 3  - fermare un thread in modo safe

**Obiettivo**: Scrivere un programma in Java che avvia un thread che esegue un conteggio numerico. Il thread deve fermarsi correttamente dopo una determinata richiesta, utilizzando una variabile di osservazione booleana.

**Descrizione**
Creerai una classe che estende Thread e che implementa un ciclo di conteggio. Il ciclo continua fino a quando una variabile di osservazione booleana non diventa true. Nel main, simulerai un’operazione che richiede di fermare il thread dopo un certo periodo di tempo, aggiornando la variabile di osservazione.

**Passaggi da seguire:**

1. **Definire la classe MyThreadSafeStop**:

• La classe deve estendere Thread e avere una variabile booleana stop che indica se il thread deve fermarsi.

• Il metodo run dovrà contenere un ciclo while che stampa un contatore, continuando fino a che stop è false o finché il contatore non raggiunge il limite di 10.000.

• Nel ciclo, inserisci una pausa di 500 millisecondi (Thread.sleep(500)).

• Se la variabile stop è true, stampa “Richiesta di stop ricevuta”. Altrimenti, stampa “Conteggio completato”.

1. **Creare il metodo requestStop()**:

• Questo metodo deve cambiare il valore di stop a true, fermando così il ciclo nel metodo run.

1. **Nel main**:

• Avvia il thread.

• Simula una richiesta di stop dopo 5 secondi, invocando requestStop().

• Usa join() per attendere la conclusione del thread prima di proseguire con altre operazioni nel main.

• Dopo che il thread è terminato, verifica il suo stato utilizzando i metodi isInterrupted(), isAlive(), e getState().

```java 
public class MyThreadSafeStop extends Thread {
    // Variabile di osservazione booleana per indicare se il thread deve terminare
    private boolean stop;

    // Costruttore che inizializza la variabile di osservazione
    public MyThreadSafeStop(boolean stop) {
        this.stop = stop;
    }

    // Metodo eseguito dal thread
    public void run() {
        int counter = 0;
        while (!stop && counter < 10000) {
            System.out.println("Contatore: " + counter++);
            try {
                Thread.sleep(500); // Attendi 500 millisecondi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.stop) {
            System.out.println("Richiesta di stop ricevuta.");
        } else {
            System.out.println("Conteggio completato.");
        }
        System.out.println("Fine metodo run.");
    }

    // Metodo per fermare il thread
    public void requestStop() {
        this.stop = true;
    }

public class MainSafeStop{
    // Metodo main per avviare il thread e fermarlo
    public static void main(String[] args) {
        // Creazione e avvio del thread
        MyThreadSafeStop counterThread = new MyThreadSafeStop(false);
        counterThread.start();

        // Simula una richiesta di stop dopo 5 secondi
        try {
            Thread.sleep(5000); // Attendi 5000 millisecondi (5 secondi)
            counterThread.requestStop(); // Richiedi lo stop del thread
            System.out.println("Ho richiesto lo stop.");
            counterThread.join(); // Attendi che il thread termini
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verifica lo stato del thread
        System.out.println(counterThread.getName() + " è interrotto: " + counterThread.isInterrupted());
        System.out.println(counterThread.getName() + " è vivo: " + counterThread.isAlive());
        System.out.println(counterThread.getName() + " stato: " + counterThread.getState());
    }
}
```


1. **Implementa la variabile booleana** stop e assicurati che il ciclo nel metodo run termini correttamente quando viene modificata.

2. **Verifica che il thread venga effettivamente fermato**: quando il valore di stop è cambiato a true, il ciclo while deve terminare.

3. **Testa l’interazione tra il thread e il metodo requestStop()**.

4. **Verifica lo stato del thread** dopo che il ciclo è terminato e il thread è stato fermato.


**Cosa ti aiuterà a capire:**

• Come utilizzare variabili di osservazione per gestire il flusso di un thread in modo sicuro e controllato.

• Come fermare un thread in modo corretto senza utilizzare Thread.interrupt().

• L’importanza di usare il metodo join() per sincronizzare il flusso tra thread.