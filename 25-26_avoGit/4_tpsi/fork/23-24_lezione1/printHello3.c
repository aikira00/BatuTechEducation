#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();

    if (pid == -1) {
        // Errore nella creazione del fork
        perror("fork");
        return 1;
    }

    if (pid == 0) {
        // Processo figlio
        printf("Ciao dal processo figlio!\n");
    } else {
        // Processo genitore
        printf("Ciao dal processo genitore!\n");
    }

    return 0;
}
