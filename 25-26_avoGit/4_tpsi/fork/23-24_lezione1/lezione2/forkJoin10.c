//
// Created by Cristina Battaglino on 18/02/24.
//
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int  computeA(){
    return 3*4;
}

int  computeB(){
    return 2 + 3;
}

int computeC(){
    return 6 - 2;
}

int computeD(int b, int c){
    return b * c;
}

int computeE(int a, int d){
    return a + d;
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
    //calcolo espressione z = (3 * 4) + (2 + 3) * (6 – 2)
    // E = A + D dove D = B * C
    pid_t pid1, pid2;
    int status1, status2, x, y, z, j;
    //processo padre
    pid1 = fork();
    if (pid1 == -1) {
        // Errore durante la creazione del processo figlio
        perror("fork failed");
        exit(EXIT_FAILURE);
    } else if(pid1==0){//calcolo B
        printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
        //calcolo espressione B
        y = computeB();
        printf("Ho finito di calcolare task B  => %d\n", y);
        writeToFile("taskB.txt", y);
        exit(EXIT_SUCCESS);
    }
    else{
        printf("Sono processo padre con PID %d\n", getpid());
        pid2 = fork();
        if (pid2 == -1) {
                // Errore durante la creazione del processo figlio
                perror("fork failed");
                exit(EXIT_FAILURE);
        } else if(pid2==0){
            printf("Sono secondo processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
            //calcolo espressione C
            z = computeC();
            printf("Ho finito di calcolare task C => %d\n", z);
            writeToFile("taskC.txt", z);
            exit(EXIT_SUCCESS);
        }
        else{
            x = computeA();
            printf("Ho finito di calcolare task A =>  %d\n", x);
        }
    }
    //figli exit(0) quindi non esegueno questo codice
    printf("Aspetto primo figlio (join)\n");
    waitpid(pid1, &status1, 0);
    if (WIFEXITED(status1)) { // Controlla se il figlio è terminato normalmente
        printf("Figlio terminato correttamente\n");
        y = readFromFile("taskB.txt");
        printf("Valori letti da file:  y = %d\n", y);
        printf("Aspetto secondo figlio (join)\n");
        waitpid(pid2, &status2, 0);
        if (WIFEXITED(status2)) {
            printf("Figlio terminato correttamente\n");
            z = readFromFile("taskC.txt");
            printf("Valori letti da file:  z = %d\n", z);
            printf("Secondo figlio ritorna calcolo: %d\n", z);
            j = computeD(y, z);
            printf("Calcolo task D = B*C (%d * %d) = %d\n", y, z, j);
            int prev_x = x;
            x = computeE(x, j );
            printf("Calcolo task E = A+D => (%d +  %d) = %d\n", prev_x, j, x);
        }
        else{
            printf("Figlio %d NON terminato con successo %d", pid1, WEXITSTATUS(status1) );
            return -1;
        }
    }
    else{
        printf("Figlio %d NON terminato con successo %d", pid1, WEXITSTATUS(status1));
        return -1;
    }
    return 0;
}
