 #include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

/*Scrivi un programma dove un padre genera un numero
 * di processi figli definiti dall’operatore mediante
 * la lettura di un valore intero mediante una funzione
 * faiFiglio(). Visualizza per ciascuno di essi il proprio
 * PID e quello del padre. Togli e aggiungi l'istruzione
 * exit(0) all'interno del segmento eseguito dal figlio:
 * cosa succede? cosa cambia? Perché?*/
int fai_figlio() {
    pid_t pid = fork();
    if (pid <0) {
        printf("Errore fork\n");
    } else if (pid == 0) {
        printf("PID figlio: %d\n", getpid());
        printf("PID padre di %d: %d\n", getpid(), getppid());
        exit(0);
    }
    return pid;
}

int main() {
    int numero_processi;

    printf("Inserisci un numero intero: ");
    scanf("%d", &numero_processi);

    pid_t figli_pid[numero_processi];

    int count = 0;
    while (count < numero_processi) {
        figli_pid[count] =  fai_figlio();
        count++;
    }

    for (int i = 0; i < numero_processi; i++) {
        printf("Processo padre %d ha fatto figlio %d\n", getpid(), figli_pid[i]);
    }

}