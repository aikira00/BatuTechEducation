//
// Created by Cristina Battaglino on 13/01/26.
//

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid1 = fork();
    if (pid1 == 0) { sleep(5); exit(1); }

    pid_t pid2 = fork();
    if (pid2 == 0) { sleep(2); exit(2); }

    // Padre: attendo SPECIFICAMENTE pid2
    int status;
    printf("Padre: attendo figlio %d (non %d!)\n", pid2, pid1);

    pid_t child_terminated = waitpid(pid2, &status, 0);  // Attende SOLO pid2

    printf("Figlio %d terminato con stato %d\n",
           child_terminated, WEXITSTATUS(status));

    // pid1 è ancora in esecuzione!
   child_terminated = waitpid(pid1, &status, 0);  // Ora attende pid1
    printf("Figlio %d terminato con stato %d\n",
           child_terminated, WEXITSTATUS(status));

    return 0;
}