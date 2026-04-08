# Esercitazione: Thread e `join` nel Mondo di Harry Potter

Questa esercitazione ti guiderà nel consolidare i concetti di thread e `join` attraverso un'ambientazione ispirata al mondo di Harry Potter. Creeremo un programma che simula diversi incantesimi lanciati da maghi, ciascuno eseguito in un thread separato.

## Obiettivo

- Creare e gestire thread in Java.
- Utilizzare `join` per sincronizzare l'esecuzione del main thread.
- Passare parametri ai thread e recuperarne il risultato.

---

## Parte 1: Maghi che lanciano incantesimi (Thread senza `join`)

### Domande

- Cosa succede se il `main` termina prima che tutti gli incantesimi siano completati?
- Gli incantesimi vengono eseguiti in ordine?

### Codice

```java
class Incantesimo extends Thread {
    private String mago;
    private String incantesimo;

    public Incantesimo(String mago, String incantesimo) {
        this.mago = mago;
        this.incantesimo = incantesimo;
    }

    public void run() {
        System.out.println(mago + " lancia " + incantesimo + "...");
        try {
            Thread.sleep(1000); // Simula il tempo di lancio dell'incantesimo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mago + " ha completato " + incantesimo + "!");
    }
}

public class ScuolaDiMagia {
    public static void main(String[] args) {
        Incantesimo t1 = new Incantesimo("Harry", "Expelliarmus");
        Incantesimo t2 = new Incantesimo("Hermione", "Alohomora");
        Incantesimo t3 = new Incantesimo("Ron", "Lumos");
        
        t1.start();
        t2.start();
        t3.start();
        
        System.out.println("Il main termina, ma gli incantesimi potrebbero non essere finiti!");
    }
}
```

---

## Parte 2: Attendere il completamento degli incantesimi con `join`

### Domande

- Qual è la differenza rispetto alla prima parte?
- Cosa accade se rimuoviamo `join`?

### Codice

```java
public class ScuolaDiMagiaJoin {
    public static void main(String[] args) {
        Incantesimo t1 = new Incantesimo("Harry", "Expelliarmus");
        Incantesimo t2 = new Incantesimo("Hermione", "Alohomora");
        Incantesimo t3 = new Incantesimo("Ron", "Lumos");
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Tutti gli incantesimi sono stati completati! Il main può terminare in sicurezza.");
    }
}
```

---

## Parte 3: Passare parametri e ottenere risultati dagli incantesimi

### Domande

- Come possiamo fare in modo che ogni mago restituisca il successo dell'incantesimo?

### Codice

```java
class IncantesimoConRisultato extends Thread {
    private String mago;
    private String incantesimo;
    private boolean successo;

    public IncantesimoConRisultato(String mago, String incantesimo) {
        this.mago = mago;
        this.incantesimo = incantesimo;
    }

    public void run() {
        System.out.println(mago + " lancia " + incantesimo + "...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        successo = Math.random() > 0.2; // 80% di successo
        System.out.println(mago + " ha " + (successo ? "successo" : "fallito") + " con " + incantesimo + "!");
    }

    public boolean getSuccesso() {
        return successo;
    }
}

public class ScuolaDiMagiaConRisultati {
    public static void main(String[] args) {
        IncantesimoConRisultato t1 = new IncantesimoConRisultato("Harry", "Expelliarmus");
        IncantesimoConRisultato t2 = new IncantesimoConRisultato("Hermione", "Alohomora");
        IncantesimoConRisultato t3 = new IncantesimoConRisultato("Ron", "Lumos");
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Risultati degli incantesimi:");
        System.out.println("Harry successo: " + t1.getSuccesso());
        System.out.println("Hermione successo: " + t2.getSuccesso());
        System.out.println("Ron successo: " + t3.getSuccesso());
    }
}
```

---

## Conclusione

Questa esercitazione ha mostrato come creare e gestire thread in Java nel magico mondo di Harry Potter. Abbiamo visto:

- Come i thread possono eseguire operazioni in parallelo.
- Come `join` permette al main thread di aspettare la fine degli altri thread.
- Come passare parametri ai thread e recuperarne i risultati.

Ora prova a modificare il codice per aggiungere nuovi incantesimi, altri maghi o cambiare il tasso di successo! 🧙✨