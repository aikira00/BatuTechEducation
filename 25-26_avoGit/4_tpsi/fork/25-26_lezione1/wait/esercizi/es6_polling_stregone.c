#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define N 3  // numero di golem

// Funzione per trovare l'indice di un golem dato il suo PID
int trova_golem(pid_t pid, pid_t golem_pid[], int n) {
    for (int i = 0; i < n; i++) {
        if (golem_pid[i] == pid) {
            return i;
        }
    }
    return -1;  // non trovato
}

int main() {
    // Array per memorizzare i PID dei golem
    pid_t golem_pid[N];

    // Array con i nomi delle missioni
    char *missioni[N] = {
        "Raccolta erbe magiche",
        "Caccia al basilisco",
        "Esplorazione dungeon"
    };

    printf("Lo stregone evoca %d golem!\n", N);

    // Evocazione dei golem
    for (int i = 0; i < N; i++) {
        pid_t pid = fork();

        if (pid < 0) {
            // Errore nella fork
            perror("Errore fork");
            exit(EXIT_FAILURE);
        }
        else if (pid == 0) {
            // Codice del golem (figlio)
            int tempo = (i + 1) * 5;  // 5, 10, 15 secondi...
            printf("  Golem %d (PID: %d) → %s\n", i + 1, getpid(), missioni[i]);
            sleep(tempo);  // simula la missione
            exit(0);       // missione completata con successo
        }
        else {
            // Codice dello stregone (padre)
            golem_pid[i] = pid;  // salva il PID del golem
        }
    }

    printf("\n");

    // Polling: lo stregone controlla periodicamente i golem
    int golem_tornati = 0;
    int status;
    pid_t pid_terminato;

    while (golem_tornati < N) {
        // Controlla se un golem è tornato (non bloccante)
        pid_terminato = waitpid(-1, &status, WNOHANG);

        if (pid_terminato > 0) {
            // Un golem è tornato!
            int indice = trova_golem(pid_terminato, golem_pid, N);

            if (WIFEXITED(status)) {
                printf("Golem %d tornato! Missione \"%s\" completata (exit: %d)\n",
                       pid_terminato, missioni[indice], WEXITSTATUS(status));
            }

            golem_tornati++;
        }
        else if (pid_terminato == 0) {
            // Nessun golem tornato, lo stregone studia
            printf("Lo stregone studia il grimorio...\n");
            sleep(2);
        }
        else {
            // Errore (pid_terminato == -1)
            perror("Errore waitpid");
            break;
        }
    }

    printf("\nTutti i golem sono tornati! Lo stregone può riposare.\n");

    return 0;
}