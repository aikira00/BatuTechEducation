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
#include <time.h>

#define num_children 4
int main(){

    int status;
    pid_t children[num_children];
    //processo padre
   for(int i=0; i<num_children; i++){
       children[i] = fork();
       if(children[i]==-1){
           // Errore durante la creazione del processo figlio
           perror("fork failed");
           exit(EXIT_FAILURE);
       }
       else if (children[i] ==0){
           srand(getpid()); //imposto seed diverso, altrimenti rand() genera sempre stessa serie numeri casuali per seed uguale
           int sleep_time = rand() % 4 + 1; // Sleep per un tempo variabile tra 1 e 4 secondi
           printf("%d\n", rand());
           printf("Sono il processo figlio con pid %d e ora mi metterò a dormire per %d secondi\n", getpid(), sleep_time);
           sleep(sleep_time);
           printf("Sono il processo figlio con pid %d e mi sono svegliato\n", getpid());
           exit(sleep_time); // Termina passando il tempo di sleep come stato di uscita
       }
   }
   //qui sono usciti tutti i figli. esegue solo il padre
    for(int i=0; i<num_children; i++){
        pid_t child_pid = waitpid(children[i], &status, 0);
        if(WIFEXITED(status)){
            printf("Il processo figlio con PID %d è terminato. Stato di uscita: %d\n", child_pid, WEXITSTATUS(status));
        }
    }
}
