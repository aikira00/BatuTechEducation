# ESERCIZIO: Processi Zombie vs Non-Zombie

## Obiettivo
Creare un programma che dimostra la differenza tra un processo zombie e un processo correttamente gestito.

## Consegna

Scrivi un programma C che:

1. Crea **DUE processi figli** (chiamiamoli Figlio A e Figlio B)

2. **Figlio A**:
   - Stampa "Figlio A: inizio ed esco subito"
   - Termina immediatamente con `exit(1)`

3. **Figlio B**:
   - Stampa "Figlio B: inizio ed esco subito"
   - Termina immediatamente con `exit(2)`

4. **Padre**:
   - Dopo aver creato i due figli, stampa i loro PID
   - Fa `wait()` o `waitpid()` SOLO per il Figlio B (non per il Figlio A!)
   - Stampa "Padre: ho raccolto lo stato del figlio B"
   - Dorme per 30 secondi (`sleep(30)`)
   - Alla fine stampa "Padre: termino"

## Cosa osservare

Mentre il padre dorme (30 secondi), **apri un altro terminale** ed esegui:

```bash
ps aux | grep Z
```

Oppure:

```bash
ps -eo pid,ppid,stat,cmd | grep defunct
```

**Domande:**

1. Quale dei due figli appare come `<defunct>` o con stato `Z` (zombie)?
2. Perché uno è zombie e l'altro no?
3. Cosa succede ai processi zombie quando il padre termina (dopo i 30 secondi)?
4. Come modificheresti il codice per evitare qualsiasi zombie?

## Suggerimenti

- Salva i PID dei figli in variabili separate
- Usa `waitpid(pid_specifico, &status, 0)` per aspettare solo il Figlio B
- Durante lo sleep, il Figlio A sarà zombie, il Figlio B sarà già terminato e rimosso

## Esempio di output atteso

```
Padre: creato figlio A con PID 1234
Padre: creato figlio B con PID 1235
Figlio A: inizio ed esco subito
Figlio B: inizio ed esco subito
Padre: ho raccolto lo stato del figlio B
Padre: aspetto 30 secondi... apri un altro terminale e fai 'ps aux | grep Z'
Padre: termino
```

Nel terminale parallelo durante lo sleep dovresti vedere:
```
user     1234  0.0  0.0      0     0 ?        Z    14:30   0:00 [a.out] <defunct>
```

## Approfondimento

Dopo aver completato l'esercizio, modifica il codice per:

1. Fare `wait()` per ENTRAMBI i figli → verifica che non ci siano più zombie
2. NON fare wait per nessuno → verifica che ENTRAMBI diventino zombie
3. Usare `WNOHANG` per controllare periodicamente se i figli sono terminati

## Soluzione (da guardare solo dopo aver provato)

```c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    // Crea Figlio A
    pid_t pid_a = fork();
    
    if (pid_a == 0) {
        // Codice Figlio A
        printf("Figlio A (PID %d): inizio ed esco subito\n", getpid());
        exit(1);
    }
    
    printf("Padre: creato figlio A con PID %d\n", pid_a);
    
    // Crea Figlio B
    pid_t pid_b = fork();
    
    if (pid_b == 0) {
        // Codice Figlio B
        printf("Figlio B (PID %d): inizio ed esco subito\n", getpid());
        exit(2);
    }
    
    printf("Padre: creato figlio B con PID %d\n", pid_b);
    
    // Piccola pausa per far terminare i figli
    sleep(1);
    
    // Aspetta SOLO il Figlio B
    int status;
    waitpid(pid_b, &status, 0);
    printf("Padre: ho raccolto lo stato del figlio B (exit status: %d)\n", 
           WEXITSTATUS(status));
    
    printf("\nPadre: aspetto 30 secondi...\n");
    printf("APRI UN ALTRO TERMINALE ED ESEGUI: ps aux | grep Z\n");
    printf("Dovresti vedere il Figlio A (PID %d) come ZOMBIE <defunct>\n\n", pid_a);
    
    sleep(30);
    
    printf("Padre: termino (init adotterà il figlio A zombie e lo pulirà)\n");
    
    return 0;
}
```

## Compilazione ed esecuzione

```bash
gcc -o zombie_demo zombie_demo.c
./zombie_demo
```

Mentre il programma dorme, in un altro terminale:

```bash
# Vedere tutti i processi zombie
ps aux | grep Z

# Vedere dettagli del processo specifico
ps -p <PID_FIGLIO_A> -o pid,ppid,stat,cmd

# Alternativa con pstree
pstree -p <PID_PADRE>
```

## Variante avanzata: osservazione con ciclo

```c
// Invece di sleep(30), fai un ciclo che mostra lo stato
for (int i = 0; i < 30; i++) {
    printf("Secondo %d: Figlio A ancora zombie\n", i+1);
    sleep(1);
}
```

Oppure usa `WNOHANG` per controllare periodicamente:

```c
for (int i = 0; i < 30; i++) {
    pid_t result = waitpid(pid_a, &status, WNOHANG);
    if (result == 0) {
        printf("Secondo %d: Figlio A ancora zombie\n", i+1);
    }
    sleep(1);
}
```
