/*
*Scrivi un programma in C in cui:
Il processo padre crea un figlio con fork()
Il figlio calcola e stampa la somma dei primi 10 numeri (1+2+3+...+10), mostrando
il risultato insieme al proprio PID, poi termina con exit(0)
II padre non esegue wait() e stampa un countdown da 30 a 0 (un numero al secondo),
lasciando il figlio in stato zombie per tutta la durata del countdown
*/
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <signal.h>

int main() {
    pid_t ret_val_fork = fork();
    if (ret_val_fork == 0) {
        //sono nel figlio, calcolo la somma dei primi 10 numeri
        printf("Sono il figlio con [PID: %d] - [PPID: %d]\n", getpid(), getppid());
        int somma = 0;
        for (int i = 1; i <= 10; i++) {
            somma += i;
            printf("Ciclo for figlio con [PID: %d] - [PPID: %d]. Somma => %d\n", getpid(), getppid(), somma);
        }
        printf("Figlio [PID: %d] - Somma 1..10 = %d\n", getpid(), somma);
        exit(0);
    }
    //padre countdown da 30 a 0, lascia figlio zombie
    for (int i = 30; i >= 0; i--) {
        //posso non farlo dormire
        sleep(1);
        printf("Sono il padre con [PID: %d], countdown => %d\n", getpid(), i);
    }
    int status_wait;
    pid_t ret_val_wait = wait(&status_wait);
    int status_code = WEXITSTATUS(status_wait);
    printf("Figlio [PID: %d] terminato con exit status: %d\n", ret_val_wait, WIFEXITED(status_wait));
    if (ret_val_wait == ret_val_fork) {
        printf("Miiiii è proprio mio figlio\n");
    }

    sleep(10);// Tempo per osservare che il padre è ancora in esecuzione e a terminale figlio defunct stato Z non c'è
    printf("Padre fine!");

}