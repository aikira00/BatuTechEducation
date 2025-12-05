#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    pid_t pid;

    while(1) {
        pid = fork();

        if (pid < 0) {
            perror("errore fork");
        }
        else if (pid == 0) {
            // Sono il figlio
            printf("Figlio creato! PID: %d, Padre: %d\n", getpid(), getppid());
        }
        else {
            // Sono il padre
            printf("Padre %d ha creato figlio %d\n", getpid(), pid);
        }
    }
}