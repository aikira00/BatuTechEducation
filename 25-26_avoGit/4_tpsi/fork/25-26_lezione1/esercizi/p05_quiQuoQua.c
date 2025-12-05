#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
    pid_t pid1, pid2, pid3;

    pid1 = fork();
    if (pid1 == 0) {
        // Primo processo figlio
        printf("Ciao, io sono Qui\n");
        exit(0); // Termina il primo processo figlio
    }

    // Processo genitore - else non necessario se si usa exit per figlio
    pid2 = fork();
    if (pid2 == 0) {
        // Secondo processo figlio
        printf("Ciao, io sono Quo\n");
        exit(0); // Termina il secondo processo figlio
    }

    // Processo genitore
    pid3 = fork();
    if (pid3 == 0) {
        // Terzo processo figlio
        printf("Ciao, io sono Qua\n");
        exit(0); // Termina il terzo processo figlio
    }

    // Il processo genitore attende che tutti i processi figli terminino
    // non è una vera sincronizzazione!!!
    sleep(10);

    return 0;
}
