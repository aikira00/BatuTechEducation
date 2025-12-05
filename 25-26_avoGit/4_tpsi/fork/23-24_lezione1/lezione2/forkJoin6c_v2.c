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

void writeToFile(char* fileName, int number){
    FILE *file = fopen(fileName,"w");
    if (file == NULL) {
        printf("Impossibile creare il file");
        exit(EXIT_FAILURE);
    }
    fprintf(file, "%d\n", number);
    fclose(file);
}


int readFromFile(char* fileName) {
    int number = 0;
    FILE *file = fopen(fileName, "r");
    if (file == NULL) {
        printf("Impossibile aprire il file %s\n", fileName);
        exit(EXIT_FAILURE);
    }
    fscanf(file, "%d", &number); // Modificato da scanf a fscanf
    fclose(file);
    return number;
}


int main(){

    pid_t pid1;
    int status, a, b, c;

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
        writeToFile("taskB.txt", b);
        exit(EXIT_SUCCESS);
    }
    else{
        printf("Sono processo padre con PID %d\n", getpid());
        a = computeA();
        printf("Ho finito di calcolare task A =>  %d\n", a);
    }
    //figlio exit(0) quindi non esegue questo codice
    printf("Aspetto figlio (join)\n");
    waitpid(pid1, &status, 0);
    if(WIFEXITED(status)){
        printf("Figlio terminato correttamente\n");
        b = readFromFile("taskB.txt");
        printf("Valori letti da file:  b = %d\n", b);
    }
    else{
        printf("Figlio NON terminato con successo %d", WEXITSTATUS(status) );
    }

    printf("Figlio ritorna calcolo: %d\n", b);
    c = computeC(a,b);
    printf("Calcolo task C = A*B => (%d * %d) = %d", a, b, c);
    exit(EXIT_SUCCESS);
}
