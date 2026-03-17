//
// Created by Cristina Battaglino on 29/01/26.
//

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>


int main() {
    pid_t pid_a, pid_b, pid_c;
    int status;


    pid_a = fork();


    if (pid_a == 0) {
        printf("Sono il figlio bravo ed inizio a lavorare\n");
        sleep(2);
        printf("Sono il figlio bravo e finisco di lavorare\n");
        exit(0);
    }

    pid_b = fork();

    if (pid_b == 0) {
        printf("Sono il figlio dimenticato ed inizio a lavorare\n");
        sleep(1);
        printf("Il padre non mi aspetterà...\n");
        exit(42);
    }

    pid_c = fork();


    if (pid_c == 0) {
        printf("Sono il figlio avventuroso ed inizio a lavorare\n");
        sleep(3);
        printf("Mi auto-uccido!\n");
        kill(getpid(), SIGKILL);
        printf("Mi sono uscciso davvero?\n");
    }


    waitpid(pid_a, &status, 0);
    if (WIFEXITED(status)) {
        printf("codiceWEXITSTATUS: %d\n", WEXITSTATUS(status));
    }
    printf("Sono il padre e questo è il pid del figlio bravo: %d\n", pid_a);


    sleep(2);
    printf("Sono il padre e questo è il pid del figlio dimenticato: %d\n", pid_b);
    for (int i = 0; i < 10; ++i) {
        printf("%d, \n", i);
        sleep(1);
    }


    waitpid(pid_c, &status, 0);
    if (WIFSIGNALED(status)) {
        printf("WIFSIGNALED: %d\n", WIFSIGNALED(status));
    }
    printf("Sono il padre e questo è il pid del figlio avventuroso: %d\n", pid_c);


    return 0;
}
