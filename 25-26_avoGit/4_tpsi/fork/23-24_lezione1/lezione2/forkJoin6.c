//
//calcolo espressione z = (3+2) * (6 - 4)
//
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int computeA(){
    return 3 + 2;
}

//se cambio in 4 - 6 non fa cosa ci aspettiamo... perché?
int computeB(){
    return 6 - 4;
}

int computeC(int a, int b){
    return a * b;
}
int main(){

    pid_t pid1;
    int status, a, b, c;
    //processo padre
    pid1 = fork();
    if (pid1 == -1) {
        // Errore durante la creazione del processo figlio
        perror("fork failed");
        exit(EXIT_FAILURE);
    } else if(pid1==0){
        printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
        //calcolo espressione B
        b = computeB();
        printf("Ho finito di calcolare  task B  => %d\n", b);
        exit(b);
    }
    else{
        printf("Sono processo padre con PID %d\n", getpid());
        a = computeA();
        printf("Ho finito di calcolare task A =>  %d\n", a);

    }
    //figlio exit(0) quindi non esegue questo codice
    printf("Aspetto figlio (join)\n");
    waitpid(pid1, &status, 0);
    b = WEXITSTATUS(status);
    printf("Figlio ritorna calcolo: %d\n", b);
    c = computeC(a,b);
    printf("Calcolo task C = A*B => (%d * %d) = %d", a, b, c);
}
