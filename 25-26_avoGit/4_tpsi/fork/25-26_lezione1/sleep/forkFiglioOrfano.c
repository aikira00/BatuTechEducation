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
    }
    if (pid == 0) {
        printf("Figlio (PID: %d) mio padre PPID: %d\n", getpid(), getppid());
        // Questo è il processo figlio
        // Il figlio dorme per 10 secondi, rendendosi orfano
        sleep(15);
        printf("Figlio (PID: %d) sono diventato orfano, PPID: %d\n", getpid(), getppid());
        fflush(stdout);
        //exit(0);
    } else {
        // Questo è il processo genitore
        //sleep(2);
        printf("Genitore (PID: %d) termina\n", getpid());
        fflush(stdout);
        // Il genitore termina qui
    }

    return 0;
}
