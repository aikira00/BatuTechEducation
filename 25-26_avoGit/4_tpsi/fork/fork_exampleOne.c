//
// Created by Cristina Battaglino on 10/02/25.
//
#include <stdio.h>
#include <unistd.h>
int main() {
    int a = 5;
    printf("Processo principale \n");
    printf("Io sono il processo con pid %d \n", getpid());
    printf("Il mio processo padre è %d \n", getppid());
    printf("Variabile a contiene: %d\n", a);

    pid_t pid = fork();
    printf("Quante volte viene eseguita questa printf? \n");
    printf("e questa ? Variabile a contiene: %d\n", a);
    printf("Io sono il processo con pid %d \n", getpid());
    printf("Il mio processo padre è %d \n", getppid());
    return 0;
}
