#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
    pid_t returnValFork;

    // Creazione del processo figlio
    returnValFork = fork();

    if (returnValFork < 0) {
        // Errore nel fork
        printf("Fallimento di fork()\n");
        return 1;
    } else if (returnValFork == 0) {
        printf("[FIGLIO PID: %d]  Questo è il processo figlio. Terminerà ora.\n", getpid());
        sleep(60);
        exit(0);
    } else {
        printf("[PADRE PID %d] Questo è il processo genitore. Continuerà l'esecuzione.\n", getpid());
    }
    printf("[PID %d]: Questo messaggio viene dal genitore. Come faccio a farlo stampare solo dal genitore?\n", getpid());
    return 0;
}
