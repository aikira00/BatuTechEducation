#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid;
    
    printf("=== PROGRAMMA INIZIATO ===\n");
    printf("Processo originale - PID: %d, PPID: %d\n\n", getpid(), getppid());
    
    pid = fork();
    
    if (pid < 0) {
        // Errore nella fork
        perror("Errore nella fork");
        exit(1);
    }
    else if (pid == 0) {
        // Codice del FIGLIO
        printf("[FIGLIO] PID: %d, PPID: %d\n", getpid(), getppid());
        printf("[FIGLIO] Dormirò per 30 secondi...\n");
        printf("[FIGLIO] Usa 'ps' per visualizzarmi!\n\n");
        
        sleep(180);  // Dorme 30 secondi
        
        printf("[FIGLIO] Mi sto svegliando e termino\n");
        exit(0);
    }
    else {
        // Codice del PADRE
        printf("[PADRE] PID: %d, PID del figlio: %d\n", getpid(), pid);
        printf("[PADRE] Dormirò per 35 secondi...\n");
        printf("[PADRE] Apri un altro terminale e usa:\n");
        printf("        ps aux | grep -E \"USER|forkSleepVisualizzaProcessi\"\n");
        
        sleep(300);  // Dorme più del figlio per vederlo terminare
        
        printf("[PADRE] Mi sto svegliando e termino\n");
        exit(0);
    }
    
    return 0;
}