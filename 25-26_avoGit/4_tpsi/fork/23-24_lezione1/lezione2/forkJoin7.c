//
// Created by Cristina Battaglino on 18/02/24.
//
/*isegnare grafo precedenze, pseudocodice fork/join e codice C di un programma che, una volta avviato,
 * generi un processo figlio
 * che si pone in sleep per 4 secondi e poi termini la sua esecuzione. Il processo padre attende la terminazione
 * del processo figlio visualizzandone il PID e il relativo stato di uscita una volta che avrà concluso.
 * */

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main(){

    int status;
    pid_t pid1;
    //processo padre
    pid1 = fork();
    if (pid1 == -1) {
        // Errore durante la creazione del processo figlio
        perror("fork failed");
        exit(EXIT_FAILURE);
    }
    else if(pid1==0){
        printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
        printf("Dormo 4 secondi \n");
        sleep(4);
        exit(0);
    }

    //figlio exit(0) quindi non esegue questo codice
    printf("Aspetto figlio (join)\n");
    waitpid(pid1, &status, 0);
    if(WEXITSTATUS(status)){
        printf("Il mio figlio %d è terminato con stato %d \n", pid1, WEXITSTATUS(status));
    }
}
