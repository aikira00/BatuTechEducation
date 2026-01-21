/*Esercizio 4 – Non determinismo e sincronizzazione padre/figlio
Parte B – Ripristinare il determinismo con waitpid()
Modifica il programma della Parte A in modo che:
Il padre attenda esplicitamente la terminazione del figlio prima di iniziare la propria stampa
Il figlio termini con un codice di uscita significativo (es. exit(0))
Il padre utilizzi waitpid() per:
Attendere specificamente il processo figlio
Salvare e analizzare il valore di ritorno di waitpid()
Usare le macro di <sys/wait.h> (WIFEXITED, WEXITSTATUS) per verificare se il figlio è terminato correttamente e stampare il codice di uscita

*/

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {

    pid_t ret_val_fork = fork();
    if (ret_val_fork ==0) {
        //sono nel processo figlio
        printf("Sono il figlio con [PID: %d] - [PPID: %d]\n", getpid(), getppid());
        for (int i = 0; i < 10; i++) {
            printf("[PID: %d] - [PPID: %d] ABCDEFGHILMNOPQRSTUVZ\n", getpid(), getppid());
            sleep(1);//ritardo per mescolare le stampe
        }
        exit(0);
    }
    //padre
    printf("Sono il padre con [PID: %d] - [PPID: %d] attendo mio figlio %d \n", getpid(), getppid(), ret_val_fork);
    int status_wait_pid;
    pid_t ret_val_wait_pid = waitpid(ret_val_fork, &status_wait_pid, 0); //bloccante
    if (WIFEXITED(status_wait_pid)) {
        printf("Sono il padre con [PID: %d] mio figlio %d è terminato correttamente", getpid(), ret_val_wait_pid);
    }
    for (int i = 0; i < 10; i++) {
        printf("[PID: %d] - [PPID: %d] abcdefghilmnopqrstuvz\n", getpid(), getppid());
        sleep(1);//ritardo per mescolare le stampe
    }
    return 0;
}