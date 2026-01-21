#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

int main() {
    pid_t pid;  // Usa pid_t invece di int
    int status;

    printf("[PADRE] PID: %d - Creo un figlio...\n\n", getpid());

    pid = fork();

    if (pid < 0) {
        // Errore nel fork
        perror("Errore di fork");
        return 1;
    } else if (pid == 0) {
        // Processo FIGLIO
        printf("[FIGLIO] PID: %d - Inizio esecuzione\n", getpid());
        printf("[FIGLIO] Dormo per 5 secondi...\n");
        fflush(stdout);

        sleep(5);

        printf("[FIGLIO] Mi auto-termino con SIGKILL\n");
        fflush(stdout);

        // Termina il processo figlio inviando un segnale a se stesso
        kill(getpid(), SIGKILL);

        // Questo codice non verrà mai eseguito (SIGKILL uccide immediatamente)
        printf("Questa riga non si stamperà mai!\n");

    } else {
        // Processo PADRE
        printf("[PADRE] Ho creato il figlio con PID: %d\n", pid);
        printf("[PADRE] Attendo la sua terminazione...\n\n");
        fflush(stdout);

        // Attende il figlio
        pid_t child_pid = waitpid(pid, &status, 0);

        printf("\n[PADRE] Il figlio %d è terminato\n", child_pid);
        printf("[PADRE] Analizzo come è terminato...\n\n");

        // Controlla COME è terminato il figlio
        if (WIFEXITED(status)) {
            // Terminazione NORMALE (exit o return)
            printf("✓ Terminazione VOLONTARIA (exit o return)\n");
            printf("  Exit status: %d\n", WEXITSTATUS(status));
        } else if (WIFSIGNALED(status)) {
            // Terminazione da SEGNALE
            int signal_num = WTERMSIG(status);
            printf("Terminazione INVOLONTARIA da segnale\n");
            printf("Numero segnale: %d \n", signal_num);

            // Stampa il nome del segnale
            if (signal_num == SIGKILL) {
                printf("(SIGKILL - ucciso forzatamente)\n");
            } else if (signal_num == SIGSEGV) {
                printf("(SIGSEGV - segmentation fault)\n");
            } else if (signal_num == SIGTERM) {
                printf("(SIGTERM - terminazione richiesta)\n");
            } else {
                printf("(altro segnale)\n");
            }
        } else {
            printf("? Il processo figlio è terminato in un modo non previsto\n");
        }
    }

    return 0;
}