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
        
        // the execv() only return if error occurred.
        // The return value is -1
        execl("/bin/ls", "/bin/ls", "-lh", "/Users/cristina", NULL);
        //execl return only if an error occur
        perror("problema comando");
        exit(-1);
    }
    else{
        //sleep(3600);
        printf("sono il padre!\n");
        printf("Il mio PID è: %d\n", getpid());
        printf ("Parent: Waiting for Child to complete.\n");

        wait(&status);
        if(WIFEXITED(status) && (WEXITSTATUS(status)==0))
            printf ("Parent: il bimbo ha finito di eseguire LS!\n");
        else
           printf("ERRORE FIGLIO\n");
    }
    
    printf("Hello, World!\n");
    exit(0);
}
