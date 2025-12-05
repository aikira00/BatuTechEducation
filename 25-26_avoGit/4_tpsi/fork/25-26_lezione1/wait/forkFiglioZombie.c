#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();

    if (pid < 0) {
        // Errore nel fork
        printf("Fallimento di fork()\n");
        return 1;
    } else if (pid == 0) {
        // Siamo nel processo figlio
        printf("Figlio (PID: %d) termina\n", getpid());
        fflush(stdout);
        exit(0);
    } else {
        // Siamo nel processo genitore
        printf("Genitore (PID: %d) in esecuzione, figlio (PID: %d) è zombie\n", getpid(), pid);
        // Il genitore continua a eseguire senza chiamare wait(),
        // quindi il figlio rimane zombie
        // Il genitore esegue un ciclo di attesa per un certo periodo di tempo
        for (int i = 0; i < 30; i++) {
            sleep(1);
            printf("Genitore in attesa %d/30 secondi...\n", i + 1);
            fflush(stdout);
        }
    }
    return 0;
}
