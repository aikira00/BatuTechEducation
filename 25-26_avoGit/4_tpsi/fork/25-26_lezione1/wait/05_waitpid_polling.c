#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();

    if (pid == 0) {
        // FIGLIO
        printf("[FIGLIO] PID: %d - lavoro per 5 secondi...\n", getpid());
        sleep(5);  // Figlio lavora per 5 secondi
        printf("[FIGLIO] Termino!\n");
        exit(0);
    } else {
        // PADRE
        int status;

        printf("[PADRE] Controllo periodicamente il figlio %d...\n\n", pid);

        // Controllo periodico senza bloccarsi
        for (int i = 0; i < 10; i++) {
            pid_t result = waitpid(pid, &status, WNOHANG);

            if (result == 0) {
                // Figlio ancora in esecuzione
                printf("[PADRE] Secondo %d: Figlio ancora in esecuzione...\n", i + 1);
                sleep(1);
            } else if (result == pid) {
                // Figlio terminato
                printf("[PADRE] Figlio terminato!\n");
                if (WIFEXITED(status)) {
                    printf("[PADRE] Exit status: %d\n", WEXITSTATUS(status));
                }
                break;
            } else if (result == -1) {
                // Errore
                printf("[PADRE] Errore in waitpid!\n");
                break;
            }
        }

        printf("[PADRE] Fine.\n");
    }

    return 0;
}