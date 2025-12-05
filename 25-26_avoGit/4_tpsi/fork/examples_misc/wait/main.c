//
//  main.c
//  exec
//
//  Created by Cristina Battaglino on 06/04/23.
//

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int main(int argc, const char * argv[]) {
    pid_t pid = fork(); //anche int ok
    int status;
    if(pid==0){
        printf("sono il figlio!");
        printf("Il mio PID è: %d\n", getpid());
        printf("Il mio papà è: %d\n", getppid());
        sleep(2);
        printf("ho finito di fare la nanna!\n");
        exit(2);
    }
    else{
        //sleep(3600);
        printf("sono il padre!\n");
        printf("Il mio PID è: %d\n", getpid());
        printf ("Parent: Waiting for Child to complete.\n");

        //int status_wait = waitpid(pid, &status, 0 );
        wait(&status);
        printf ("Parent: il bimbo ha finito di fare la nanna!\n");

    }
    
    printf("Hello, World!\n");
    return 0;
}
