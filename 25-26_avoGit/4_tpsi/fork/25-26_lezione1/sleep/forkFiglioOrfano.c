#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

 /**da terminale compilare
 * gcc forkFiglioOrfano.c -o forkFiglioOrfano
 * avviare programma
 * ./forkFiglioOrfano
 * aprire altro terminale ed eseguire
 * ps au -o ppid
 * per vedere che processo figlio ha genitore con pid = 1
 *
 * stato S in esecuzione
 * VSZ = quanta memoria il processo POTREBBE usare
 * Se il tuo programma fa malloc(1GB) ma usa solo 1MB, VSZ include tutto 1GB
* RSS - Resident Set Size (Memoria Fisica)
* %MEM = (RSS / RAM_totale) × 100
RSS = quanta memoria il processo STA DAVVERO usando (RAM fisica)
stati processo
+ processo attivo che riceve input da terminale (senza o backdround o no terminale attivo)
s processo leader di sessione
S sleep
Z zombie
R running
**/

int main() {
    pid_t pid;

    // Creazione del processo figlio
    pid = fork();

    if (pid < 0) {
        // Errore nel fork
        printf("Fallimento di fork()\n");
        return 1;
    }
    if (pid == 0) {
        printf("Figlio (PID: %d) mio padre PPID: %d\n", getpid(), getppid());
        // Questo è il processo figlio
        // Il figlio dorme per 10 secondi, rendendosi orfano
        sleep(15);
        printf("Figlio (PID: %d) sono diventato orfano, PPID: %d\n", getpid(), getppid());
        fflush(stdout);
        //exit(0);
    } else {
        // Questo è il processo genitore
        //sleep(2);
        printf("Genitore (PID: %d) termina\n", getpid());
        fflush(stdout);
        // Il genitore termina qui
    }

    return 0;
}
