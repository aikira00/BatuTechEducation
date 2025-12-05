//
//  main.c
//  oneProgramTwoProcesses_pid
//
//  Created by Cristina Battaglino on 06/04/23.
//

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, const char * argv[]) {
    pid_t pid = fork(); //anche int ok
    if(pid==0){
        printf("sono il figlio!\n");
        printf("Il mio PID è: %d\n", getpid());
        printf("Il mio papà è: %d\n", getppid());
        printf("QUI\n");
    }
    else{
        //sleep(3600);
        pid = fork();
        if(pid==0){
            printf("sono il secondo figlio!\n");
            printf("Il mio PID è: %d\n", getpid());
            printf("Il mio papà è: %d\n", getppid());
            printf("QUO\n");
        }
        else{
            pid = fork();
            if(pid==0){
                printf("sono il terzo figlio!");
                printf("Il mio PID è: %d\n", getpid());
                printf("Il mio papà è: %d\n", getppid());
                printf("QUA\n");
            }
        }
        
    }
    if(pid!=0){
        printf("sono il padre!\n");
        //sleep(3450); cosa succede se commento questo??
        printf("Il mio PID è: %d\n", getpid());
    }
    return 0;
}
