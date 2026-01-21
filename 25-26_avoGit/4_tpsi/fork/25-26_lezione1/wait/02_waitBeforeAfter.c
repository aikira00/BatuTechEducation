#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();
    
    if (pid == 0) {
        // FIGLIO
        printf("[FIGLIO] PID: %d - termino SUBITO\n", getpid());
        exit(42);  // Termina con exit code 42
    } else {
        // PADRE
        printf("[PADRE] PID: %d, figlio: %d\n", getpid(), pid);
        printf("[PADRE] Il figlio è già terminato (è zombie)\n");
        printf("[PADRE] Aspetto 10 secondi prima di fare wait()...\n");
        
        // Verifica zombie in altro terminale
        printf("\nControlla in altro terminale:\n");
        printf("  ps -o pid,ppid,state,cmd %d %d\n\n", getpid(), pid);
        
        for (int i = 1; i <= 10; i++) {
            sleep(1);
            printf("[PADRE] %d/10 - zombie persiste\n", i);
        }
        
        printf("\n[PADRE] Ora chiamo wait()...\n");
        
        int status;
        pid_t terminated = wait(&status);
        
        printf("[PADRE] wait() ritornato IMMEDIATAMENTE!\n");
        printf("[PADRE] Figlio %d raccolto\n", terminated);
        
        if (WIFEXITED(status)) {
            printf("[PADRE] Exit code: %d\n", WEXITSTATUS(status));
        }
        
        printf("[PADRE] Zombie pulito!\n");
    }
    
    return 0;
}
