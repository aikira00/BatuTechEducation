#include <stdio.h>
#include <unistd.h>

int main() {
    printf("[PRIMA DEL FORK] Processo PID: %d\n", getpid());
    
    pid_t pid = fork();
    
    if (pid > 0) {
        // Padre
        printf("[PADRE] PID: %d - stampa 1", getpid());
        printf("[PADRE] PID: %d - stampa 2", getpid());
       printf("[PADRE] PID: %d - stampa 3", getpid());

    } else {
        // Figlio
        printf("[FIGLIO] PID: %d, Padre: %d - stampa 1", getpid(), getppid());
        printf("[FIGLIO] PID: %d, Padre: %d - stampa 2", getpid(), getppid());
        printf("[FIGLIO] PID: %d, Padre: %d - stampa 3", getpid(), getppid());

    }
    
    return 0;
}   