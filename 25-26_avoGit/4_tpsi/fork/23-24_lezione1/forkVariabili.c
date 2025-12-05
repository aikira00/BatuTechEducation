#include <stdio.h>
#include <unistd.h>

int main() {
    int var = 10; // Variabile che verrà sdoppiata

    pid_t pid = fork();

    if (pid < 0) {
        // Errore nel fork
        fprintf(stderr, "Fallimento di fork()\n");
        return 1;
    } else if (pid == 0) {
        // Processo figlio
        printf("Figlio inizia, var = %d\n", var);
        var = 20; // Modifica la variabile nel processo figlio
        printf("Figlio modifica, var = %d\n", var);
    } else {
        // Processo genitore
        printf("Genitore inizia, var = %d\n", var);
        var = 30; // Modifica la variabile nel processo genitore
        printf("Genitore modifica, var = %d\n", var);
        sleep(1); // Aspetta per assicurarsi che l'output del figlio venga stampato per primo
    }
    printf("Codice eseguito da entrambi, var = %d\n", var);
    return 0;
}
