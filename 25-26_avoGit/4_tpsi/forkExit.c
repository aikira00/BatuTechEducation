#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
    pid_t pid;

    // Creazione del processo figlio
    pid = fork();

    if (pid < 0) {
        // Errore nel fork
        printf("Fallimento di fork()\n");
        return 1;
    } else if (pid == 0) {
        printf("[FIGLIO PID: %d]  Questo è il processo figlio. Terminerà ora.\n", getpid());
        exit(0);
    } else {
        printf("[PADRE PID %d] Questo è il processo genitore. Continuerà l'esecuzione.\n", getpid());
    }
    printf("[PID %d]: Questo messaggio viene dal genitore. Come faccio a farlo stampare solo dal genitore?\n", getpid());
    return 0;
}
