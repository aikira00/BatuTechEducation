#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    for (int i = 0; i < 2; i++) {
        pid_t pid = fork();
        if (pid == 0) {
            // FIGLIO
            printf("[FIGLIO] PID: %d - termino SUBITO\n", getpid());
            exit(0);  // Termina con exit code 42
        }
    }

    // PADRE
    for (int i = 1; i <= 15; i++) {
        sleep(1);
        printf("[PADRE] %d/15 - dormo... zzzZZZZZzzzzz \n", i);
    }
    printf("Ho due zombie");

    int status;
    printf("\n[PADRE] Ora chiamo wait()...\n");
    pid_t terminated = wait(&status);
    printf("[PADRE] Figlio %d aspettato\n", terminated);

    printf("[PADRE] Fase due.. devo fare così altrimenti cosa succede????\n");
    for (int i = 1; i <= 15; i++) {
        sleep(1);
        printf("[PADRE] %d/15 - dormo... zzzZZZZZzzzzz \n", i);
    }
    printf("Ho un figlio zombie");

    return 0;
}
