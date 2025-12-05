#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void faiFiglio() {
    pid_t pid = fork();

    if (pid < 0) {
        // Errore nel fork
        printf("Fallimento di fork()\n");
    } else if (pid == 0) {
        // Siamo nel processo figlio
        printf("Figlio (PID: %d) del genitore (PID: %d)\n", getpid(), getppid());
        exit(0); //    // prova a togliere questa istruzione!
    } else {
        // Siamo nel processo genitore
        printf( "Sono il processo padre con pid:%d.\n", getpid() );
    }
}

int main() {
    int n, i;

    printf("Inserisci il numero di processi figli da creare: ");
    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        faiFiglio();
    }

    return 0;
}
