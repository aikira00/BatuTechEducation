//
//calcolo espressione z = (3+2) * (4 -6)
//
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int computeA(){
    return 3 + 2;
}

//se cambio in 4 - 6 non fa cosa ci aspettiamo... perché?
int computeB(){
    return 4 - 6;//numero negativo ... ritorna stato errore, calcolo scombinato
}

int computeC(int a, int b){
    return a * b;
}
int main(){

    pid_t pid1;
    int status, a, b, c;
    FILE *file = fopen("shared_file.txt","w");
    if (file == -1) {
        printf("Impossibile creare il file");
        exit(EXIT_FAILURE);
    }
    //processo padre
    pid1 = fork();
    if (pid1 == -1) {
        // Errore durante la creazione del processo figlio
        printf("fork failed");
        exit(EXIT_FAILURE);
    } else if(pid1==0){
        printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
        //calcolo espressione B
        b = computeB();
        printf("Ho finito di calcolare  task B  => %d\n", b);
        // Spostati alla posizione appropriata nel file e scrivi il risultato
        if (fseek(file, 1, SEEK_SET) == -1) {
            printf("lseek errore per process figlio %d", getpid());
            exit(EXIT_FAILURE);
        }
        printf("%lu\n", sizeof("%d\n"));
        if (fprintf(file, "%d\n", b) == -1) {
            printf("write errore per processo figlio %d", getpid());
            exit(EXIT_FAILURE);
        }
        exit(EXIT_SUCCESS);
    }
    else{
        printf("Sono processo padre con PID %d\n", getpid());
        a = computeA();
        printf("Ho finito di calcolare task A =>  %d\n", a);
        if (fseek(file, 0, SEEK_SET) == -1) {
            printf("lseek errore per process figlio %d", getpid());
            exit(EXIT_FAILURE);
        }
        if (fprintf(file, "%d\n", a) == -1) {
            printf("write errore per processo figlio %d", getpid());
            exit(EXIT_FAILURE);
        }

    }
    //figlio exit(0) quindi non esegue questo codice
    printf("Aspetto figlio (join)\n");
    waitpid(pid1, &status, 0);
    if(WIFEXITED(status)){
        printf("Figlio terminato correttamente\n");
    }
    else{
        printf("Figlio NON terminato con successo %d", WEXITSTATUS(status) );
    }

    fclose(file);
    file = fopen("shared_file.txt","r");
    rewind(file);
    fseek(file, 0, SEEK_SET);
    fscanf(file, "%d", &a);
    fscanf(file, "%d", &b);
    printf("Valori letti da file: a = %d, b = %d\n", a, b);

    printf("Figlio ritorna calcolo: %d\n", b);
    c = computeC(a,b);
    printf("Calcolo task C = A*B => (%d * %d) = %d", a, b, c);
    fclose(file);
}
