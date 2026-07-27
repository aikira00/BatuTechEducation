# Esercizio 1 – Processo Orfano

Scrivi un programma in C in cui:

- Il processo padre crea un figlio con fork()
    
- Il figlio stampa il proprio PID e il PPID (Parent PID), poi entra in un ciclo che stampa un messaggio ogni 2 secondi per almeno 10 secondi
    
- Il padre termina dopo 3 secondi, rendendo il figlio orfano
    
- Il figlio, dopo essere diventato orfano, stampa nuovamente il proprio PPID per mostrare che è cambiato (adottato da init/systemd)
    

Consegna:

1. Codice sorgente .c commentato
    
2. Screenshot del terminale con l'esecuzione del comando ps -o pid,ppid,stat,cmd che mostra il figlio con PPID cambiato (dovrebbe essere 1 o il PID del processo systemd)
    

  

# Esercizio 2 – Processo Zombie

Scrivi un programma in C in cui:

- Il processo padre crea un figlio con fork()
    
- Il figlio calcola e stampa la somma dei primi 10 numeri (1+2+3+...+10), mostrando il risultato insieme al proprio PID, poi termina con exit(0)
    
- II padre non esegue wait() e stampa un countdown da 30 a 0 (un numero al secondo), lasciando il figlio in stato zombie per tutta la durata del countdown
    

Consegna:

1. Codice sorgente .c commentato
    
2. Screenshot del terminale con l'esecuzione del comando ps -o pid,ppid,stat,cmd che mostra il processo figlio in stato Z (zombie/defunct) e la risposta alla seguente domanda: 
    

3. Cosa succede al processo zombie quando il padre termina? Verifica con ps e spiega il comportamento osservato.
    

  
  

# Esercizio 3 – Eliminazione dello zombie

Modifica il programma Esercizio 2 – Processo Zombie in modo che:

- Il padre, dopo aver stampato il countdown, chiami wait() per raccogliere lo stato di terminazione del figlio.
    
- Il padre deve analizzare lo stato di terminazione usando le macro appropriate e stampare:
    

- Se il figlio è terminato normalmente: "Figlio terminato normalmente con exit status: X" (usa WIFEXITED e WEXITSTATUS)
    
- Se il figlio è stato terminato da un segnale: "Figlio terminato dal segnale: X" (usa WIFSIGNALED e WTERMSIG)
    

# Esercizio 4 – Non determinismo e sincronizzazione padre/figlio

### Parte A – Osservare il non determinismo

Scrivi un programma in C in cui:

- Il processo padre crea un figlio con fork()
    
- Il figlio stampa l'alfabeto maiuscolo (A-Z) per 10 volte, poi termina con exit(0)(e.g., ABCDEFGHIJKLMNOPQRSTUVWXYZ per 10 volte)
    
- Il padre stampa l'alfabeto minuscolo (a-z) per 10 volte, senza attendere il figlio
    

Esegui il programma più volte e osserva come l'output di padre e figlio si mescola in modo diverso ad ogni esecuzione. Prova ad inserire piccoli ritardi (usleep() o sleep()) in punti diversi del codice per variare il comportamento.

### Parte B – Ripristinare il determinismo con waitpid()

Modifica il programma della Parte A in modo che:

- Il padre attenda esplicitamente la terminazione del figlio prima di iniziare la propria stampa
    
- Il figlio termini con un codice di uscita significativo (es. exit(0))
    
- Il padre utilizzi waitpid() per:
    

1. Attendere specificamente il processo figlio
    
2. Salvare e analizzare il valore di ritorno di waitpid()
    
3. Usare le macro di <sys/wait.h> (WIFEXITED, WEXITSTATUS) per verificare se il figlio è terminato correttamente e stampare il codice di uscita
    

### Consegna:

1. Codice sorgente .c commentato della Parte A
    
2. Codice sorgente .c commentato della Parte B
    
3. Screenshot che mostrano:
    

- Almeno 2 esecuzioni della Parte A con output mescolato in modo diverso
    
- Un'esecuzione della Parte B con output ordinato (prima figlio, poi padre)
    

5. Risposte alle seguenti domande:
    

- Perché nella Parte A l'output è diverso ad ogni esecuzione?
    
- Qual è la differenza tra wait() e waitpid()?
    
- Cosa succede se il figlio termina con exit(0) e il padre usa WEXITSTATUS(status)?
    

# Esercizio 5 – Gestione di più figli con terminazioni diverse

Scrivi un programma in C in cui:

- Il processo padre crea tre figli con fork()
    
- Il primo figlio stampa "Figlio 1 (PID: ...) in attesa..." e rimane in esecuzione con un sleep(30)
    
- Il secondo figlio stampa "Figlio 2 (PID: ...) calcolo in corso...", calcola il fattoriale di 6, stampa il risultato e termina normalmente con exit(0)
    
- Il terzo figlio stampa "Figlio 3 (PID: ...) apertura file...", tenta di aprire un file inesistente con fopen(), verifica il fallimento e termina con exit(1) stampando un messaggio di errore
    
- Il padre, dopo aver creato entrambi i figli:
    

1. Attende 2 secondi
    
2. Invia il segnale SIGKILL al primo figlio usando la funzione kill()
    
3. Chiama waitpid() tre volte per raccogliere lo stato di tutti i figli
    
4. Per ogni figlio, analizza e stampa lo stato di terminazione usando le macro WIFEXITED, WEXITSTATUS, WIFSIGNALED, WTERMSIG
    

Per inviare un segnale: includi <signal.h> per usare SIGKILL e  kill(pid, SIGKILL) - SIGKILL non può essere intercettato, ignorato o bloccato perché è gestito direttamente dal kernel. Questo garantisce che il sistema operativo possa sempre terminare un processo, anche se malfunzionante o non collaborativo.

Per analizzare il segnale vedere slides docente (verso la fine) con esempio WIFSIGNALED/WTERMSIG/SIGKILL

Per aprire un file 

FILE *file = fopen("file_inesistente.txt", "r");

if (file == NULL) {

   printf("Figlio 3: impossibile aprire il file\n");

   exit(EXIT_FAILURE);  // equivale a exit(1)

}

  

// Se arriviamo qui, il file esiste (non dovrebbe succedere)

fclose(file);

exit(EXIT_SUCCESS);  // equivale a exit(0)

  

Outpu atteso (esempio)

Creati figli con PID: 1234, 5678, 9012

Invio SIGKILL al figlio 1...

Figlio 1234 terminato dal segnale: 9

Figlio 5678 terminato normalmente con exit status: 0

Figlio 9012 terminato normalmente con exit status: 1 - possibile errore

Consegna:

1. Codice sorgente .c commentato
    
2. Screenshot del terminale con l'esecuzione completa del programma
    
3. Risposte alle seguenti domande:
    

- Qual è il numero del segnale SIGKILL?
    
- Perché il primo figlio non può intercettare o ignorare SIGKILL?