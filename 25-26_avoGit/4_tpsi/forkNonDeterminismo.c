//
// Created by Cristina Battaglino on 10/02/25.
//
#include <stdio.h>
#include <unistd.h>
int main() {
    printf("[PRIMA DEL FORK] Processo PID: %d\n", getpid());

    pid_t pid = fork();

    printf("[DOPO FORK] Processo PID: %d, PID del padre: %d\n", getpid(), getppid());
    printf("[DOPO FORK] Processo PID: %d, PID del padre: %d\n", getpid(), getppid());
    printf("[DOPO FORK] Processo PID: %d, PID del padre: %d\n", getpid(), getppid());
    return 0;
}