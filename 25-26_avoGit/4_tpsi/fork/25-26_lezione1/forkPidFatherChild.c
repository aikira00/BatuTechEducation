//
// Created by Cristina Battaglino on 10/02/25.
//
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid;
    pid_t child_pid;

    // Print the PID of the initial process
    printf("Parent process PID: %d\n", getpid());
    // Fork a new process
    pid = fork();

    if (pid < 0) {
        // Fork failed
        printf("Fork failed");
        return 1;
    } else if (pid == 0) {
        // This is the child process
        printf("Child process PID: %d\n", getpid());
        printf("Child's parent PID: %d\n", getppid());
    } else {
        // This is the parent process
        child_pid = pid; // PID of the child
        printf("Parent process PID: %d\n", getpid());
        printf("Child PID: %d\n", child_pid);
    }

    return 0;
}
