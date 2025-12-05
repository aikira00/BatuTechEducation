//
//  main.c
//  variables
//
//  Created by Cristina Battaglino on 06/04/23.
//
//Parent process and child process are running the same program, but it doe no mean they are identical.
//
//

#include <stdio.h>
#include <sys/types.h>;
#include <unistd.h>;

int main(int argc, const char * argv[]) {
    pid_t pid = fork(); //anche int ok
    
    int x = 0; // global variable
    if(pid==0){
        printf("sono il figlio!");
        printf("Il mio PID è: %d\n", getpid());
        printf("Il mio papà è: %d\n", getppid());
        x++;
        printf("[SON %d] - Il valore di x è: %d\n", getpid(), x);
    }
    else{
        //sleep(3600);
        printf("sono il padre!\n");
        printf("Il mio PID è: %d\n", getpid());
        x = x + 5;
        printf("[DAD %d] - Il valore di x è: %d\n", getpid(), x);
    }
    x = x + 2;
    printf("[WHO %d] - comune valore di x è %d\n", getpid(), x);
    printf("Hello, World!\n");
    return 0;
}
