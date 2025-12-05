//
//  main.c
//  oneProgramTwoProcesses
//
//  Created by Cristina Battaglino on 06/04/23.
//
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#define KEY 12345

int main() {
    int shmid;
    int *sharedInt;
    
    // Creazione della memoria condivisa
    shmid = shmget(KEY, sizeof(int), IPC_CREAT | 0666);
    if (shmid == -1) {
        perror("shmget");
        exit(1);
    }
    // Attach della memoria condivisa
    sharedInt = (int*) shmat(shmid, NULL, 0);
    if (sharedInt == (int*) -1) {
        perror("shmat");
        exit(1);
    }
    
    // Inizializzazione del valore condiviso
    *sharedInt = 0;
    
    // Fork per creare un nuovo processo
    pid_t pid = fork();
    
    if (pid > 0) {
        // Codice eseguito dal processo padre
        printf("Processo padre: Valore condiviso incrementato = %d\n", *sharedInt);
        sleep(500);
        (*sharedInt)++;
    }
    if(pid==0){
        (*sharedInt)++;
        printf("Processo figlio: Valore condiviso  = %d\n", *sharedInt);
        
    }
    
    return 0;
}

/*#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int g_var = 0;
int* g_var_p = &g_var;

int main(int argc, const char * argv[]) {

    *int num_1 = 0;
    int* num_1_p = &num_1;
    printf("%d: sono il padre \n", getpid());
    pid_t pid = fork();
    if(pid == 0){
        printf("%d: sono il figlio \n", getpid());
    }
    (*num_1_p) ++;
    int num_2 = 0;
    int* num_2_p = &num_2;
    (*num_2_p) ++;
    (*g_var_p)++;
    printf("%d: num_1 value %d \n", getpid(), *num_1_p);
    printf("%d: num_2 value %d \n", getpid(), *num_2_p);
    printf("%d: global_variable value %d \n", getpid(), *g_var_p);
    
    
    
    return 0;
    
}

*/
