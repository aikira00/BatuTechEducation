#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>

int main() {
    int pid, status;

    pid = fork();
    if (pid < 0) {
        // Errore nel fork
        perror("Errore di fork");
        return 1;
    } else if (pid == 0) {
        // Ramo eseguito dal solo processo figlio
        printf("Eseguito dal figlio \n");

        // Termina il processo figlio inviando un segnale a se stesso
        kill(getpid(), SIGKILL);
    } else {
        // Ramo eseguito dal solo processo padre
        pid = wait(&status); // Controllo dello stato del processo figlio
        if (WIFEXITED(status)) {
            printf("Terminazione volontaria di %d con stato %d\n", pid, WEXITSTATUS(status));
        } else if (WIFSIGNALED(status)) {
            printf("Terminazione involontaria per segnale %d\n", WTERMSIG(status));
        } else {
            printf("Il processo figlio è terminato in un modo non previsto.\n");
        }
    }

    return 0;
}