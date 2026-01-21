
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define N 5

int main() {
    pid_t workers[N];
    int workers_finished = 0;
    
    printf("═══════════════════════════════════════\n");
    printf("  TASK MANAGER: Creo %d worker\n", N);
    printf("═══════════════════════════════════════\n\n");
    
    // PARTE 1: Creazione worker
    for (int i = 0; i < N; i++) {
        pid_t pid = fork();
        
        if (pid == 0) {
            // FIGLIO (worker)
            int worker_id = i + 1;
            int sleep_time = worker_id * 5;
            
            printf("[WORKER %d] PID: %d - Dormo per %d secondi\n", 
                   worker_id, getpid(), sleep_time);
            fflush(stdout);
            
            sleep(sleep_time);
            
            printf("[WORKER %d] Completato!\n", worker_id);
            fflush(stdout);
            
            exit(sleep_time);
        } else {
            // PADRE
            workers[i] = pid;
            printf("[PADRE] Worker %d creato con PID: %d\n", i+1, pid);
        }
    }
    
    printf("\n[PADRE] Tutti i worker creati. Inizio polling...\n\n");
    
    // PARTE 2: Polling
    while (workers_finished < N) {
        int status;
        pid_t result = waitpid(-1, &status, WNOHANG);
        
        if (result > 0) {
            // Un worker ha terminato
            workers_finished++;
            
            // Trova quale worker
            int worker_id = -1;
            for (int i = 0; i < N; i++) {
                if (workers[i] == result) {
                    worker_id = i + 1;
                    break;
                }
            }
            
            int exit_status = WEXITSTATUS(status);
            printf("[PADRE] Worker %d (PID: %d) terminato con exit status: %d\n",
                   worker_id, result, exit_status);
            printf("[PADRE] Worker completati: %d/%d\n\n", workers_finished, N);
            
        } else if (result == 0) {
            // Nessuno pronto
            printf("[PADRE] Polling... %d/%d worker completati\n", 
                   workers_finished, N);
            fflush(stdout);
            sleep(1);  // Aspetta 1 secondo prima di controllare di nuovo
        }
    }
    
    
    // PARTE 3: Riepilogo
    printf("\n═══════════════════════════════════════\n");
    printf("         RIEPILOGO FINALE\n");
    printf("═══════════════════════════════════════\n");
    printf("Tutti i %d worker sono terminati!\n", N);
    printf("═══════════════════════════════════════\n");
    
    return 0;
}
