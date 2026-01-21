/*Esercizio 4 – Non determinismo e sincronizzazione padre/figlio
Parte A – Osservare il non determinismo
Scrivi un programma in C in cui:
Il processo padre crea un figlio con fork()
Il figlio stampa l'alfabeto maiuscolo (A-Z) per 10 volte, poi termina con exit(0)(e.g.,
ABCDEFGHIJKLMNOPQRSTUVWXYZ per 10 volte)
Il padre stampa l'alfabeto minuscolo (a-z) per 10 volte, senza attendere il figlio
Esegui il programma più volte e osserva come l'output di padre e figlio si mescola in modo diverso ad ogni esecuzione.
Prova ad inserire piccoli ritardi (usleep() o sleep()) in punti diversi del codice per variare il comportamento.
*/

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {

    pid_t ret_val_fork = fork();
    if (ret_val_fork ==0) {
        //sono nel processo figlio
        printf("Sono il figlio con [PID: %d] - [PPID: %d]\n", getpid(), getppid());
        for (int i = 0; i < 10; i++) {
            printf("[PID: %d] - [PPID: %d] ABCDEFGHILMNOPQRSTUVZ\n", getpid(), getppid());
            sleep(i);//ritardo per mescolare le stampe
        }
        exit(0);
    }
    //padre
    printf("Sono il padre con [PID: %d] - [PPID: %d]\n", getpid(), getppid());
    for (int i = 0; i < 10; i++) {
        printf("[PID: %d] - [PPID: %d] abcdefghilmnopqrstuvz\n", getpid(), getppid());
        sleep(i);//ritardo per mescolare le stampe
    }
    return 0;
}