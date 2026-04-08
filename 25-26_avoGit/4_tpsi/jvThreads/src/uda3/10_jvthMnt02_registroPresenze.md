Si simula una classe RegistroPresenze in cui più thread scrivono nomi all’interno di una lista condivisa (List<String>). Ogni thread rappresenta uno studente che “firma” il registro.

Si vuole simulare una classe RegistroPresenze in cui più thread scrivono nomi all’interno di una lista condivisa (List<String>).

Ogni thread rappresenta uno studente che “firma” il registro.

Hai una classe RegistroPresenze che contiene:

- una lista di stringhe (List<String>),  
      
    
- due metodi per aggiungere un nome:  
      
    

- aggiungiPresenza(String nome) senza sincronizzazione,  
      
    
- aggiungiPresenzaSync(String nome) con sincronizzazione.  
      
    

  

Il main crea un oggetto di tipo RegistroPresenze e lo passa ai thread come parametro (nota bene: questo oggeto sarà quindi una porzione di memoria condivisa). Il main crea dieci thread. Ogni thread rappresenta uno studente che “firma” il registro 100 volte. Alla fine, ti aspetti che ci siano esattamente 1000 firme.

1. Scrivi la classe RegistroPresenze con i seguenti metodi:  
      
    

- aggiungiPresenza(String nome) senza synchronized.  
      
    
- aggiungiPresenzaSync(String nome) con synchronized.  
      
    

2. Scrivi la classe RegistroPresenzeThread che estende Thread.  
      
    Nel metodo run(), ogni thread deve firmare il registro 100 volte usando il proprio nome.  
      
    
3. Scrivi la classe RegistroPresenzeMain:  
      
    

- Crea un oggetto di tipo RegistroPresenze da condividere tra tutti i thread (attenzione: è un oggetto condiviso, quindi una porzione di memoria condivisa).  
      
    
- Crea 10 thread con nomi diversi (Studente0, Studente1, …, Studente9). //usare un array di threads o una lista di threads  
      
    
- Avvia tutti i thread.  
      
    
- Attendi la fine dell’esecuzione con join().  
      
    
- Stampa il numero totale delle presenze registrate.  
      
    

5. Esegui due versioni del test:  
      
    

- Versione senza sincronizzazione: usa aggiungiPresenza() nel metodo run.  
      
    
- Versione con sincronizzazione: usa aggiungiPresenzaSync() nel metodo run.  
      
    

senza sincronizzazione, ottieni un numero inferiore a 1000 firme a causa di accessi simultanei alla lista condivisa (race condition). Se il risultato senza sincronizzazione è casualmente 1000, ripeti più volte. Il problema può non emergere sempre, ma è reale.

### Domande di riflessione

1. Perché il numero di firme non è sempre 1000 senza synchronized?  
      
    
2. Cos’è una race condition?  
      
    
3. Perché il metodo synchronized risolve il problema?  
      
    
4. Cosa succede se due thread tentano di accedere alla lista nello stesso istante?  
      
    

Consegna un .zip contenente:

- RegistroPresenze.java  
      
    
- RegistroPresenzeThread.java  
      
    
- RegistroPresenzeMain.java  
      
    
- Un file README.txt con le risposte alle domande di riflessione e i risultati ottenuti nelle due versioni.