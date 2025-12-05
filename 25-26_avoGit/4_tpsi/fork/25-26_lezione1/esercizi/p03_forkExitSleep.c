//
// Created by Cristina Battaglino on 29/11/25.
//

/* Progettare un programma che, una volta avviato, chieda in input
 * due valori interi e generi due processi figli, il primo si occuperà
 * di calcolare e visualizzare il prodotto dei due valori, il secondo la somma.
Usare sleep() per simulare tempo di eleborazione dei calcoli. Usare exit opportunamente.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int primo_numero = 0;
    int secondo_numero = 0;
    pid_t ret_fork_primo_figlio = 0;
    pid_t ret_fork_secondo_figlio = 0;

    //user input
    printf("Inserisci due numeri interi: ");
    scanf("%d", &primo_numero);
    scanf("%d", &secondo_numero);

    //creo primo figlio
    ret_fork_primo_figlio = fork();
    if (ret_fork_primo_figlio<0) {
        printf("error fork");
    }else if(ret_fork_primo_figlio == 0){
        sleep(2);
        printf("Prodotto tra %d e %d: %d\n", primo_numero, secondo_numero, primo_numero*secondo_numero);
        exit(0);
    }
    ret_fork_secondo_figlio = fork();
    if (ret_fork_secondo_figlio<0) {
        printf("error fork");
    }else if(ret_fork_secondo_figlio == 0){
        sleep(2);
        printf("Somma tra %d e %d: %d\n", primo_numero, secondo_numero, primo_numero+secondo_numero);
        exit(0);
    }

    printf("\n[Padre - PID: %d] Tutti i calcoli sono stati completati.\n", getpid());

}