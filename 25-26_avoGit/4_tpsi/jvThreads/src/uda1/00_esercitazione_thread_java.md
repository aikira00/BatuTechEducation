**Esercitazione Guidata: Introduzione ai Thread in Java**

### **Obiettivo**

Comprendere:

- I due modi per creare un thread in Java.
    
- L’uso di `setName()` e `getCurrentThread()`.
    
- Il concetto di **non determinismo** nell’esecuzione dei thread.
    

---

### **Parte 1: Creazione di un Thread (Runnable vs. Estendere Thread)**

#### **1.1 Implementazione con Runnable**

1. Creare una classe `MyRunnable` che implementa `Runnable`:
    

```
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Esecuzione del thread: " + Thread.currentThread().getName());
    }
}
```

2. Nel `main`, avviare il thread:
    

```
public class ThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.setName("Thread-Runnable");
        t1.start();
        
        System.out.println("Thread principale: " + Thread.currentThread().getName());
    }
}
```

**Domande:**

- Quale sarà l’output del programma?
    
- Cosa succede se non si chiama `start()` su `t1`?
    
- Cosa succede se aggiungiamo `t1.join();` subito dopo `t1.start();`? Perché?
    

---

#### **1.2 Implementazione estendendo Thread**

1. Creare una classe `MyThread` che estende `Thread`:
    

```
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Esecuzione del thread: " + this.getName());
    }
}
```

2. Nel `main`, avviare il thread:
    

```
public class ThreadExample2 {
    public static void main(String[] args) {
        MyThread t2 = new MyThread();
        t2.setName("Thread-Esteso");
        t2.start();
        System.out.println("Thread principale: " + Thread.currentThread().getName());
    }
}
```

**Domande:**

- Qual è la differenza tra `Runnable` e `Thread`?
    
- Quale metodo preferiresti e perché?
    

---

### **Parte 2: Osservare il Non Determinismo**

1. Creare una classe con più thread:
    

```
class Task implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Iterazione " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

2. Nel `main`, creare più thread:
    

```
public class NonDeterminismExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(), "Thread-1");
        Thread t2 = new Thread(new Task(), "Thread-2");
        Thread t3 = new Thread(new Task(), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
	    
	    System.out.println("Thread principale: " + Thread.currentThread().getName() + "finisce esecuzione ");
    }
}
```

**Domande:**

- L’ordine delle stampe è sempre lo stesso?
    
- Perché il risultato cambia ad ogni esecuzione?
    
- Cosa succede se aggiungiamo `t1.join(); t2.join(); t3.join() ` nel `main()`? 
    

---

### **Conclusioni**

- Un thread può essere creato implementando `Runnable` o estendendo `Thread`.
    
- `setName()` aiuta a distinguere i thread nell’output.
    
- `getCurrentThread()` permette di ottenere informazioni sul thread in esecuzione.
    
- L’ordine di esecuzione dei thread è **non deterministico** e dipende dalla gestione della CPU