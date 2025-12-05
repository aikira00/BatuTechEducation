#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int pid1, pid2, pid3;

    pid1 = fork(); // creo il primo processo figlio
    if (pid1 == 0) {
        // Sono nel processo figlio 1
        printf("Sono il processo figlio con pid: %d.", getpid());
        printf(" Il mio papi ha pid: %d\n", getppid());
        sleep(1); // attesa per non creare orfani
        exit(0); // termina il processo figlio
    } else if (pid1 > 0) {
        // Sono nel processo genitore
        pid2 = fork(); // creo il secondo processo figlio
        if (pid2 == 0) {
            // Sono nel processo figlio 2
            printf("Sono il processo figlio con pid: %d.", getpid());
            printf(" Il mio papi ha pid: %d\n", getppid());
            sleep(1); // attesa per non creare orfani
            exit(0); // termina il processo figlio
        } else if (pid2 > 0) {
            // Sono nel processo genitore
            pid3 = fork(); // creo il terzo processo figlio
            if (pid3 == 0) {
                // Sono nel processo figlio 3
                printf("Sono il processo figlio con pid: %d.", getpid());
                printf(" Il mio papi ha pid: %d\n", getppid());
                sleep(1); // attesa per non creare orfani
                exit(0); // termina il processo figlio
            }
        }
    }

    // Solo il genitore arriva qui
    sleep(10); // se togli questa istruzione cosa succede?
    if (pid1 > 0 && pid2 > 0 && pid3 > 0) {
        printf("Sono il processo padre con pid: %d. \n", getpid());
    }

    return 0;
}


