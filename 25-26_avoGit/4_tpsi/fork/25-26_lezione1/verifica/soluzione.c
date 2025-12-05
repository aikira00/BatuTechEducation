//
// Created by Cristina Battaglino on 03/12/25.
//
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
void fai_figlio() {
    pid_t pid = fork();
    if (pid <0) {
        printf("Errore fork\n");
    } else if (pid == 0) {
        printf("PID figlio: %d\n", getpid());
        printf("PID padre di %d: %d\n", getpid(), getppid());
        exit(0);
    }
}

#define NUMERO_FIGLI 5

int main() {
    pid_t figli[NUMERO_FIGLI];

    int i = 1;
    while (i < NUMERO_FIGLI+1) {
        pid_t pid = fork();

        if (pid < 0) {
            perror("Errore nella fork");
            return 1;
        }

        if (pid == 0) {
            printf("Sono il  %d figlio, il mio PID è %d mio padre è il processo %d\n",i, getpid(), getppid());

            sleep(3);
            return 0;
        } else {
            figli[i-1] = pid;  // il padre salva il PID del figlio
        }
        i++;
    }

    printf("Sono il processo principale con PID %d\n", getpid());
    sleep(3);
    printf("I miei figli sono \n");
    for (int i = 0; i < 5; i++) {
        printf("%d \n", figli[i]);
    }
}