#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    pid_t pid;

    pid = fork();
    if (pid < 0) {
        // Errore nel fork
        perror("Errore di fork");
        return 1;
    } else if (pid == 0) {
        // Siamo nel processo figlio
        printf("Questo è il processo figlio. Terminerà ora.\n");
        //exit(0); 
    } else {
        // Siamo nel processo genitore
        printf("Questo è il processo genitore. Continuerà l'esecuzione.\n");
        // Il genitore continua a eseguire senza attendere il figlio
    }

   // sleep(1);
    printf("Questo messaggio viene dal genitore. Come faccio a farlo stampare solo dal genitore?\n");

    return 0;
}
