/*
*Scrivi un programma in C in cui:
Il processo padre crea un figlio con fork()
Il figlio stampa il proprio PID e il PPID (Parent PID), poi entra in un ciclo che stampa un messaggio ogni 2 secondi per almeno 10 secondi
Il padre termina dopo 3 secondi, rendendo il figlio orfano
Il figlio, dopo essere diventato orfano, stampa nuovamente il proprio PPID per mostrare che è cambiato (adottato da init/systemd)
*/
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
    pid_t ret_val_fork = fork();
    if (ret_val_fork == 0) {
        //sono nel figlio
        printf("Sono il figlio con [PID: %d] - [PPID: %d]\n", getpid(), getppid());
        for (int i = 0; i < 10; i++) {
            sleep(2);
            printf("Ciclo for figlio con [PID: %d] - [PPID: %d]\n", getpid(), getppid());
        }
    }
    else {
        //padre dorme 3 secondi e termina
        sleep(3);
        printf("Sono il padre con [PID: %d]\n", getpid());
        exit(0);
    }
    //eseguito solo dal figlio
    printf("Sono il figlio chi è mio padre????? [PID: %d] - [PPID: %d]\n", getpid(), getppid());
}