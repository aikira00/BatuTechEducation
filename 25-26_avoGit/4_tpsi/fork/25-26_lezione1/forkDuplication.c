//
// Created by Cristina Battaglino on 10/02/25.
//
#include <stdio.h>
#include <unistd.h>

int main() {
    int x = 10;
    printf("[PRIMA di chiamare FORK] PID: %d, x = %d\n", getpid(), x);

    pid_t pid = fork();

    printf("[DOPO aver chiamato  FORK] PID: %d, x = %d\n", getpid(), x);
    if (pid == 0) {
        // Processo figlio
        x += 5;
        printf("[FIGLIO] PID: %d, x = %d\n", getpid(), x);

;    } else {
        // Processo padre
        x -= 5;
        printf("[PADRE] PID: %d, x = %d\n", getpid(), x);
    }
    printf("[PADRE/FIGLIO?] PID: %d, x = %d\n", getpid(), x);
    return 0;
}