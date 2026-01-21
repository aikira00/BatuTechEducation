/*
*Scrivi un programma in C in cui:
Il processo padre crea tre figli con fork()
Il primo figlio stampa "Figlio 1 (PID: ...) in attesa..." e rimane in esecuzione con un sleep(30)
Il secondo figlio stampa "Figlio 2 (PID: ...) calcolo in corso...", calcola il fattoriale di 6, stampa il risultato
e termina normalmente con exit(0)
Il terzo figlio stampa "Figlio 3 (PID: ...) apertura file...", tenta di aprire un file inesistente con fopen(),
verifica il fallimento e termina con exit(1) stampando un messaggio di errore
Il padre, dopo aver creato entrambi i figli:
Attende 2 secondi
Invia il segnale SIGKILL al primo figlio usando la funzione kill()
Chiama waitpid() tre volte per raccogliere lo stato di tutti i figli
Per ogni figlio, analizza e stampa lo stato di terminazione usando le macro WIFEXITED, WEXITSTATUS, WIFSIGNALED, WTERMSIG
Per inviare un segnale: includi <signal.h> per usare SIGKILL e  kill(pid, SIGKILL) - SIGKILL non può essere intercettato,
ignorato o bloccato perché è gestito direttamente dal kernel. Questo garantisce che il sistema operativo
possa sempre terminare un processo, anche se malfunzionante o non collaborativo.
Per analizzare il segnale vedere slides docente (verso la fine) con esempio WIFSIGNALED/WTERMSIG/SIGKILL
Per aprire un file
FILE *file = fopen("file_inesistente.txt", "r");
if (file == NULL) {
printf("Figlio 3: impossibile aprire il file\n");
exit(EXIT_FAILURE);  // equivale a exit(1)
}

// Se arriviamo qui, il file esiste (non dovrebbe succedere)
fclose(file);
exit(EXIT_SUCCESS);  // equivale a exit(0)
*/

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    //creo tre figli
    pid_t ret_vals_fork[3];
    for (int i= 0; i<3; i++){
        ret_vals_fork[i] = fork();
        if (ret_vals_fork[i] == 0){
            //sono nei figli
            switch (i){
                case 0:
                    //primo figlio
                    printf("Figlio %d (PID: %d - PPID: %d), dormo 30 secondi\n", i+1, getpid(), getppid());
                    sleep(30);
                    exit(0);
                case 1:
                    //secondo figlio
                    printf("Figlio %d (PID: %d - PPID: %d), calcolo fattoriale di 6\n", i+1,  getpid(), getppid());
                    int fatt_res = 0;
                    for(int j=1; j<=6; j++) {
                        fatt_res *= j;
                    }
                    printf("Figlio %d (PID: %d - PPID: %d), RISULTATO fattoriale di 6 => %d\n", i+1,  getpid(), getppid(), fatt_res);
                    exit(0);
                case 2:
                    //terzo filio
                    printf("Figlio %d (PID: %d - PPID: %d), apro un file inesistente\n", i+1,  getpid(), getppid());
                    FILE *file = fopen("file_inesistente.txt", "r");
                    if (file == NULL) {
                        printf("Figlio 3: impossibile aprire il file\n");
                        exit(EXIT_FAILURE);  // equivale a exit(1)
                    }
                    // Se arriviamo qui, il file esiste (non dovrebbe succedere)
                    fclose(file);
                    exit(EXIT_SUCCESS);  // equivale a exit(0)
                default:
                    printf("QUALCOSA ANDATO STORTO\n");
                    exit(2);
            }
        }
        sleep(2); //padre dorme dopo aver creato un figlio

    }

    //padre
    printf("Creati i figli ");
    for (int i=0; i<3; i++) {
        printf(" %d ", ret_vals_fork[i]);
    }
    printf("\n");
    //Invia il segnale SIGKILL al primo figlio usando la funzione kill()
    printf("Sono il padre con [PID: %d], invio SIGKILL a primo figlio con [PID: %d]\n", getpid(), ret_vals_fork[0]);
    kill(ret_vals_fork[0], SIGKILL);
    printf("Sono il padre con [PID: %d] - attendo i figli\n");
    for (int i=0; i<3; i++) {
        printf("Sono il padre con [PID: %d] - attendo mio figlio %d \n", getpid(), ret_vals_fork[i]);
        int status_wait_pid;
        int exit_status_wait_pid;
        int signal_wait_pid;
        pid_t ret_val_wait_pid = waitpid(ret_vals_fork[i], &status_wait_pid, 0);
        if (WIFEXITED(status_wait_pid)) {
            exit_status_wait_pid = WEXITSTATUS(status_wait_pid);
            printf("Sono il padre con [PID: %d] - mio figlio [PID: %d] è terminato con status %d\n", getpid(), ret_val_wait_pid, exit_status_wait_pid);
            if (exit_status_wait_pid == 0) printf("Figlio [PID: %d] terminato correttamente\n", ret_val_wait_pid);
            else printf("Figlio [PID: %d] terminato con errore\n", ret_val_wait_pid);
        }
        if (WIFSIGNALED(status_wait_pid)) {
            signal_wait_pid = WTERMSIG(status_wait_pid);
            printf("Sono il padre con [PID: %d] - mio figlio [PID: %d] ha avuto problemi %d\n", getpid(), ret_val_wait_pid,signal_wait_pid );
            if (signal_wait_pid == SIGKILL) printf("Figlio [PID: %d] terminato da SIGKILL\n", ret_val_wait_pid);
            if (signal_wait_pid == SIGSEGV) printf("Figlio [PID: %d] terminato da SEGMENTATION FAULT\n", ret_val_wait_pid);

        }
    }

}