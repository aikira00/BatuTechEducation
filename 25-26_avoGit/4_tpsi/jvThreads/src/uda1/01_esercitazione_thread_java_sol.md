
**Esercitazione JVTH01 - Creazione e gestione dei thread**

  

In questa esercitazione, implementerai e testerai due diverse modalità di creazione e gestione dei thread in Java: utilizzando l’interfaccia Runnable e estendendo la classe Thread. Segui i passaggi descritti di seguito.

  

**1. Creazione di classi separate per l’implementazione dei thread**

• **Classe RunnableThread**:

Questa classe deve implementare l’interfaccia Runnable e, nel metodo run(), eseguire un ciclo che mette in pausa il thread per 6 secondi per 5 cicli consecutivi.

```
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " - Ciclo " + (i + 1));
                Thread.sleep(6000);  // Pausa di 6 secondi
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrotto");
            }
        }
    }
}
```

  

• **Classe ThreadExtension**:

Questa classe deve estendere la classe Thread e, nel metodo run(), eseguire un ciclo che mette in pausa il thread per un numero casuale di secondi (compreso tra 0 e 3) per 5 cicli consecutivi. Utilizzerai la classe java.util.Random per generare il numero casuale.

```
import java.util.Random;

public class ThreadExtension extends Thread {
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int sleepTime = random.nextInt(4);  // Numero casuale tra 0 e 3
                System.out.println(Thread.currentThread().getName() + " - Pausa per " + sleepTime + " secondi");
                Thread.sleep(sleepTime * 1000);  // Pausa in millisecondi
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrotto");
            }
        }
    }
}
```

  

  

**2. Classe principale con il metodo main**

  

Crea una classe principale che avvii entrambi i thread creati sopra, utilizzando il metodo Thread.currentThread().getName() per stampare il nome del thread corrente e Thread.sleep(ms) per mettere in pausa il thread principale. Gestisci le eccezioni con un blocco try-catch in caso di interruzione del thread durante il sonno.

```
public class Main {
    public static void main(String[] args) {
        // Crea e avvia il thread che implementa Runnable
        RunnableThread runnableThread = new RunnableThread();
        Thread thread1 = new Thread(runnableThread, "RunnableThread");
        thread1.start();

        // Crea e avvia il thread che estende Thread
        ThreadExtension thread2 = new ThreadExtension();
        thread2.setName("ThreadExtension");
        thread2.start();

        try {
            // Pausa il thread principale per osservare l'esecuzione dei thread
            System.out.println(Thread.currentThread().getName() + " in pausa per 10 secondi");
            Thread.sleep(10000);  // Pausa il thread principale
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrotto");
        }

        System.out.println("Il thread principale è terminato.");
    }
}
```

**3. Comportamento previsto e osservazioni**

• **Esecuzione dei thread**:

I thread devono eseguire indipendentemente, e ogni thread, sia quello creato tramite Runnable che quello esteso dalla classe Thread, deve mettere in pausa il proprio thread per il tempo specificato.

Puoi osservare che l’ordine di esecuzione dei thread non è garantito e varia a seconda della gestione dei thread da parte della JVM.

• **Gestione delle eccezioni**:

Se un thread viene interrotto durante il periodo di sleep, l’eccezione InterruptedException deve essere gestita in modo appropriato, come mostrato nei blocchi try-catch.

• **Tempo di terminazione del thread principale**:

Il thread principale, dopo aver avviato gli altri due thread, si metterà in pausa per 10 secondi, ma la sua durata di esecuzione non influenzerà l’esecuzione dei thread secondari. Quando il thread principale termina, gli altri thread potrebbero continuare a girare, e questo ti permetterà di osservare come i thread lavorano indipendentemente.

  

**4. Domande di riflessione**

• Quando termina il thread principale?

Dopo che la pausa di 10 secondi è terminata, il thread principale termina. Tuttavia, i thread RunnableThread e ThreadExtension potrebbero continuare a essere in esecuzione.

• Quali sono le differenze tra i due approcci per la gestione dei thread (Runnable vs Thread)?

Questa versione dell’esercitazione dovrebbe risultare chiara e stimolante, lasciando agli studenti la possibilità di esplorare la gestione dei thread con entrambe le tecniche. Se ti occorrono ulteriori modifiche o chiarimenti, fammelo sapere!