/*
Scrivere un programma che, una volta avviato, generi due processi figli che a loro volta generano un proprio
processo figlio.
Impostare opportunamente l’output in modo tale che sia comprensibile quali siano le relazioni di “parentela”.
Ogni processo deve rimanere attivo per 20 secondi usando `sleep()'
Tutti i processi devono terminare in modo appropriato (exit oppure return)
Ogni processo deve stampare le proprie informazioni nel seguente formato:
[RUOLO] PID: , PPID: ,
Esempio:
[NONNO] PID: 1234, PPID: 999, Inizio esecuzione
[PADRE1] PID: 1235, PPID: 1234, Sono il primo figlio
[NIPOTE1] PID: 1237, PPID: 1235, Sono figlio di PADRE1
[NIPOTE1] PID: 1237, Termino dopo 10 secondi

``
                    ┌─────────────────────┐
                    │      NONNO          │
                    │   PID: 1000         │
                    │   PPID: 500 (shell) │
                    └──────────┬──────────┘
                               │
                ┌──────────────┴──────────────┐
                │                             │
                ▼                             ▼
        ┌───────────────┐            ┌───────────────┐
        │    PADRE1     │            │    PADRE2     │
        │   PID: 1001   │            │   PID: 1002   │
        │   PPID: 1000  │            │   PPID: 1000  │
        └───────┬───────┘            └───────┬───────┘
                │                            │
                │                            │
                ▼                            ▼
        ┌───────────────┐            ┌───────────────┐
        │   NIPOTE1     │            │   NIPOTE2     │
        │   PID: 1003   │            │   PID: 1004   │
        │   PPID: 1001  │            │   PPID: 1002  │
        └───────────────┘            └───────────────┘
*/

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main() {

   printf("[NONNO] PID: %d, PPID: %d inizio esecuzione \n", getpid(), getppid());
   pid_t ret_val_primo_figlio = fork();

   if (ret_val_primo_figlio == 0) {
      // primo figlio
      printf("[PADRE1] PID: %d, PPID: %d sono il primo figlio di [NONNO], ora creo nipote \n", getpid(), getppid());

      pid_t ret_val_primo_nipote = fork();

      if (ret_val_primo_nipote == 0 ) {
         printf("[NIPOTE1] PID: %d sono figlio di %d [PADRE1] \n", getpid(), ret_val_primo_nipote);
         sleep(20);
      }
      else {
         printf("[PADRE1] PID: %d  ho creato nipote %d \n", getpid(), ret_val_primo_nipote);
         sleep(20);
      }
   }
   else {
      printf("[NONNO] PID: %d ho creato figlio %d \n", getpid(), ret_val_primo_figlio);
      // processo padre, crea secondo figlio
      pid_t ret_val_secondo_figlio = fork();
      if (ret_val_secondo_figlio==0) {
         // secondo figlio
         printf("[PADRE2] PID: %d, PPID: %d sono il secondo figlio di [NONNO], ora creo secondo nipote \n", getpid(), getppid());
         pid_t ret_val_secondo_nipote = fork();

         if (ret_val_secondo_nipote == 0 ) {
            printf("[NIPOTE2] PID: %d sono figlio di %d [PADRE2] \n", getpid(), getppid());
            sleep(20);
         }
         else {
            // PADRE2 aspetta il suo nipote
            printf("[PADRE2] PID: %d ho creato nipote %d \n", getpid(), ret_val_secondo_nipote);
            sleep(20);
         }
      }
      else {
         // NONNO
         printf("[NONNO] PID: %d ho creato secondo figlio %d \n", getpid(), ret_val_secondo_figlio);
         sleep(20);
      }
   }
   printf("Questa print chi la esegue?");
}