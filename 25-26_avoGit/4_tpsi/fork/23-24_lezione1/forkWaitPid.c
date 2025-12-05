#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main(){
    int pid, status;
    pid = fork();
    if(pid == 0){                       // ramo eseguito dal solo processo figlio
        printf("eseguito dal figlio, dormo 25 secondi \n");
        sleep(5);
        exit(0);
    }
    else{
        // ramo eseguito dal solo processo padre
        printf("Sono il padre, aspetto il figlio: %d\n", pid);
        pid = waitpid(pid, &status, 0);              // controllo stato del processo figlio
        if(WIFEXITED(status)){
            printf("Term. volontaria di %d con stato %d\n", pid, WEXITSTATUS(status));
        }
        else{
            if(WIFSIGNALED(status)){
                printf("terminazione involontaria per segnale %d\n", WTERMSIG(status));
            }
        }
    }
}

