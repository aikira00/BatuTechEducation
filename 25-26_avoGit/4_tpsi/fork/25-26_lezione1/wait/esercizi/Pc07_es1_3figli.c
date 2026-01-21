#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

int main() {
    pid_t pid_a, pid_b, pid_c;
    int status_a, status_c;
    int exit_status_a = -1;
    int segnale_c = -1;

    printf("═══════════════════════════════════════════════\n");
    printf("   IL PADRE E I TRE FIGLI CON DESTINI DIVERSI\n");
    printf("═══════════════════════════════════════════════\n");
    printf("[PADRE] PID: %d\n\n", getpid());

    // =====================================================
    // CREAZIONE FIGLIO A - IL BRAVO
    // =====================================================
    printf("--- Creo Figlio A: Il Bravo ---\n");
    pid_a = fork();

    if (pid_a < 0) {
        perror("Errore fork Figlio A");
        exit(EXIT_FAILURE);
    }
    if (pid_a == 0) {
        // Codice Figlio A
        printf("[FIGLIO A] PID: %d - Sono il bravo!\n", getpid());
        printf("[FIGLIO A] Faccio il mio lavoro per 2 secondi...\n");
        sleep(2);
        printf("[FIGLIO A] Lavoro completato! Termino con exit(0)\n");
        exit(0);
    }
    printf("[PADRE] Figlio A creato con PID: %d\n\n", pid_a);

    // =====================================================
    // CREAZIONE FIGLIO B - IL DIMENTICATO
    // =====================================================
    printf("--- Creo Figlio B: Il Dimenticato ---\n");
    pid_b = fork();

    if (pid_b < 0) {
        perror("Errore fork Figlio B");
        exit(EXIT_FAILURE);
    }
    if (pid_b == 0) {
        // Codice Figlio B
        printf("[FIGLIO B] PID: %d - Sono quello dimenticato...\n", getpid());
        printf("[FIGLIO B] Lavoro velocemente...\n");
        sleep(1);
        printf("[FIGLIO B] Ho finito! Ma il padre non mi aspetterà...\n");
        exit(42);
    }
    printf("[PADRE] Figlio B creato con PID: %d\n\n", pid_b);

    // =====================================================
    // CREAZIONE FIGLIO C - L'AVVENTUROSO
    // =====================================================
    printf("--- Creo Figlio C: L'Avventuroso ---\n");
    pid_c = fork();

    if (pid_c < 0) {
        perror("Errore fork Figlio C");
        exit(EXIT_FAILURE);
    }
    if (pid_c == 0) {
        // Codice Figlio C
        printf("[FIGLIO C] PID: %d - Sono l'avventuroso!\n", getpid());
        printf("[FIGLIO C] Faccio cose pericolose...\n");
        sleep(3);
        printf("[FIGLIO C] Ops! Mi auto-uccido con SIGKILL!\n");
        kill(getpid(), SIGKILL);
        // Questo codice non verrà mai eseguito
        printf("[FIGLIO C] Non stamperò mai questo!\n");
    }
    printf("[PADRE] Figlio C creato con PID: %d\n\n", pid_c);

    // =====================================================
    // FASE 1: ASPETTO FIGLIO A
    // =====================================================
    printf("═══════════════════════════════════════════════\n");
    printf("          FASE 1: ASPETTO FIGLIO A\n");
    printf("═══════════════════════════════════════════════\n");

    waitpid(pid_a, &status_a, 0);

    printf("\n[PADRE] Ho aspettato il Figlio A (PID: %d)\n", pid_a);

    if (WIFEXITED(status_a)) {
        exit_status_a = WEXITSTATUS(status_a);
        printf("[PADRE] ✓ Figlio A terminato NORMALMENTE\n");
        printf("[PADRE]   Exit status: %d\n", exit_status_a);
    }

    // =====================================================
    // FASE 2: FIGLIO B È ZOMBIE
    // =====================================================
    printf("\n═══════════════════════════════════════════════\n");
    printf("    FASE 2: FIGLIO B È ZOMBIE (non aspettato!)\n");
    printf("═══════════════════════════════════════════════\n\n");

    printf(">>> ADESSO! Apri un altro terminale ed esegui: ps aux | grep Z\n\n");

    // Loop di 10 secondi per osservare lo zombie
    for (int i = 1; i <= 10; i++) {
        printf("[PADRE] %d/10 - Figlio B (PID: %d) è ZOMBIE...\n", i, pid_b);
        sleep(1);
    }

    // =====================================================
    // FASE 3: ASPETTO FIGLIO C
    // =====================================================
    printf("\n═══════════════════════════════════════════════\n");
    printf("          FASE 3: ASPETTO FIGLIO C\n");
    printf("═══════════════════════════════════════════════\n");

    waitpid(pid_c, &status_c, 0);

    printf("[PADRE] Ho aspettato il Figlio C (PID: %d)\n", pid_c);

    if (WIFSIGNALED(status_c)) {
        segnale_c = WTERMSIG(status_c);
        printf("[PADRE] ✗ Figlio C terminato da SEGNALE\n");
        printf("[PADRE]   Segnale: %d", segnale_c);
        if (segnale_c == SIGKILL) {
            printf(" (SIGKILL)");
        }
        printf("\n");
    }

    // =====================================================
    // RIEPILOGO FINALE
    // =====================================================
    printf("\n═══════════════════════════════════════════════\n");
    printf("              RIEPILOGO FINALE\n");
    printf("═══════════════════════════════════════════════\n");

    printf("Figlio A (PID: %d) - Aspettato, terminato NORMALMENTE (exit: %d)\n",
           pid_a, exit_status_a);
    printf("Figlio B (PID: %d) - NON aspettato, era ZOMBIE!\n", pid_b);
    printf("Figlio C (PID: %d) - Aspettato, terminato da SEGNALE (%d)\n",
           pid_c, segnale_c);

    printf("\n[PADRE] Termino. Init adotterà e pulirà il Figlio B zombie.\n");
    
    return 0;
}