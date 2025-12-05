//
// Created by Cristina Battaglino on 10/02/25.
//
#include <stdio.h>
#include <unistd.h>

int main() {
    int a = 20;
    int b = 30;
    printf("[PRIMA DEL FORK] PID: %d, a = %d, b = %d\n", getpid(), a, b);

    pid_t pid = fork();

    if (pid == 0) {
        // Processo figlio modifica le variabili
        a += 10;
        b -= 10;
        printf("[FIGLIO] PID: %d, a = %d, b = %d\n", getpid(), a, b);
    } else {
        // Processo padre modifica le variabili
        a -= 10;
        b += 10;
        printf("[PADRE] PID: %d, a = %d, b = %d\n", getpid(), a, b);
    }

    return 0;
}