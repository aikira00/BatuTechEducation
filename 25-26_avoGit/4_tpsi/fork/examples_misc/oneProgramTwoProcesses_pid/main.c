//
//  main.c
//  oneProgramTwoProcesses_pid
//
//  Created by Cristina Battaglino on 06/04/23.
//

#include <stdio.h>
#include <sys/types.h>;
#include <unistd.h>;

int main(int argc, const char * argv[]) {
    pid_t pid = fork(); //anche int ok
    if(pid==0){
        printf("sono il figlio!");
        printf("Il mio PID è: %d\n", getpid());
        printf("Il mio papà è: %d\n", getppid());
    }
    else{
        //sleep(3600);
        printf("sono il padre!\n");
        printf("Il mio PID è: %d\n", getpid());
    }
    
    printf("Hello, World!\n");
    return 0;
}
