
# Setup IDE progetto

## Creazione di un Progetto Java in IntelliJ IDEA

Segui questi passaggi per configurare un progetto Java in IntelliJ IDEA:

1. **Aprire IntelliJ IDEA** e selezionare `File > New > Project`.
2. **Scegliere Java** come tipo di progetto e cliccare su `Next`.
3. **Configurare il progetto**:
   - Inserire il nome del progetto (es. `ThreadJoinExample`).
   - Selezionare la cartella di destinazione.
   - Assicurarsi che sia selezionata la versione corretta di JDK.
   - Cliccare su `Finish`.
4. **Creare una nuova classe**:
   - Nel pannello `Project`, fare clic con il tasto destro sulla cartella `src`.
   - Selezionare `New > Java Class` e assegnare un nome (es. `MainParte1`).
5. **Scrivere il codice** copiando e incollando le diverse parti dell'esercitazione nei file Java corrispondenti.
6. **Eseguire il programma**:
   - Selezionare la classe principale con `main`.
   - Fare clic con il tasto destro e selezionare `Run 'MainParte1'`.

## Creazione di un Progetto Java in NetBeans

Segui questi passaggi per configurare un progetto Java in NetBeans:

1. **Aprire NetBeans** e selezionare `File > New Project`.
2. **Selezionare 'Java' e poi 'Java Application'**, quindi cliccare su `Next`.
3. **Configurare il progetto**:
   - Inserire il nome del progetto (es. `ThreadJoinExample`).
   - Selezionare la cartella di destinazione.
   - Assicurarsi che sia selezionata la versione corretta di JDK.
   - Cliccare su `Finish`.
4. **Creare una nuova classe**:
   - Nel pannello `Projects`, fare clic con il tasto destro sulla cartella `Source Packages`.
   - Selezionare `New > Java Class` e assegnare un nome (es. `MainParte1`).
5. **Scrivere il codice** copiando e incollando le diverse parti dell'esercitazione nei file Java corrispondenti.
6. **Eseguire il programma**:
   - Selezionare la classe principale con `main`.
   - Fare clic con il tasto destro e selezionare `Run File`.

# Esercitazione Guidata: Uso di `join` nei Thread in Java

Questa esercitazione  è suddivisa in quattro parti:

1. Il main non aspetta i thread.
2. Il main aspetta i thread con `join`.
3. Passaggio di parametri ai thread.
4. Recupero di parametri dal thread

## Parte 1: Il main non aspetta i thread

### Domanda
- Cosa succede se il `main` termina prima dei thread? I thread vengono eseguiti comunque?

### Codice
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " in esecuzione.");
        try {
            Thread.sleep(1000); // Simula un lavoro
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " terminato.");
    }
}

public class MainParte1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
        System.out.println("Main terminato.");
    }
}
```

## Parte 2: Il main aspetta i thread con `join`

### Domanda
- Leggere la documentazione ufficiale del metodo join: [Java Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#join--)
- Qual è la differenza rispetto alla prima parte? Perché il `main` ora attende la fine dei thread?
- Cosa cambia se si usano `join(long millis)`, `join(long millis, int nanos)`? Il thread `main` aspetterà un tempo indefinito?
- Codice Perché il `main` ora attende la fine dei thread?

### Codice
```java
class MainParte2 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
        try {
            t1.join(); // Aspetta la terminazione di t1
            t2.join(); // Aspetta la terminazione di t2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main terminato dopo la fine dei thread.");
    }
}
```

## Parte 3: Passaggio di parametri al thread

### Domanda
- passaggio di parametri al thread
- Come possiamo passare più variabili al thread e modificarle nel `main`?

### Codice
```java
class ParamThread extends Thread {
    private int delay;
    public ParamThread(int delay) {
        this.delay = delay;
    }
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " in esecuzione con delay " + delay + "ms.");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " terminato.");
    }
}

public class MainParte3 {
    public static void main(String[] args) {
        ParamThread t1 = new ParamThread(1000);
        ParamThread t2 = new ParamThread(2000);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main terminato dopo la fine dei thread con parametri.");
    }
}
```

### Codice due

```java
class ParamThread extends Thread {
    private int delay;
    private String message;

    public ParamThread(int delay, String message) {
        this.delay = delay;
        this.message = message;
    }

    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " in esecuzione con delay " + delay + "ms e messaggio: " + message);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " terminato.");
    }
}

public class MainParte3 {
    public static void main(String[] args) {
        int delay1 = 1000;
        int delay2 = 2000;
        String message1 = "Messaggio per il primo thread";
        String message2 = "Messaggio per il secondo thread";

        ParamThread t1 = new ParamThread(delay1, message1);
        ParamThread t2 = new ParamThread(delay2, message2);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main terminato dopo la fine dei thread con parametri.");
    }
}
```
### Conclusione
Questa esercitazione mostra l'importanza di `join` nel garantire la corretta sincronizzazione tra il `main` e i thread. Prova a modificare i valori dei parametri nei thread per osservare come cambia il comportamento del programma!


## Parte 4: Recupero di un Attributo dal Thread

### Domanda
- Come può il `main` recuperare un valore calcolato da un thread?
- Perché è necessario sincronizzare l'accesso ai dati condivisi?

### Codice
```java
class ResultThread extends Thread {
    private int result;

    public void run() {
        result = (int) (Math.random() * 100); // Simula un calcolo
        System.out.println("Thread " + Thread.currentThread().getId() + " ha calcolato il valore: " + result);
    }

    public int getResult() {
        return result;
    }
}

public class MainParte4 {
    public static void main(String[] args) {
        ResultThread t1 = new ResultThread();
        t1.start();
        try {
            t1.join(); // Aspetta la fine del thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Il valore calcolato dal thread è: " + t1.getResult());
    }
}
```

### Conclusione
Questa esercitazione mostra l'importanza di `join` nel garantire la corretta sincronizzazione tra il `main` e i thread. Prova a modificare i valori dei parametri nei thread per osservare come cambia il comportamento del programma!
