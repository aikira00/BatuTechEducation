//
// Created by Cristina Battaglino on 18/02/24.
//
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h> // Inclusione di sys/wait.h per waitpid()

int computeA(){
    return 3 * 4;
}

int computeB(){
    return 2 + 3;
}

int calcolaC(){
    return 6 - 2;
}

int calcolaD(int x, int y ){
    return x * y;
}

int calcolaE(int x, int y ){
    return x + y;
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
    pid_t pid1, pid2;
    int status1, status2, x, y, z, j;

    pid1 = fork();
    if (pid1 == -1) {
        perror("fork failed");
        exit(EXIT_FAILURE);
    } else if(pid1 == 0) { // Processo figlio per calcolo B e C
        printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
        y = computeB();
        printf("Ho finito di calcolare y = 2 + 3, task B\n");
        writeToFile("taskB.txt", y);
        pid2 = fork();
        if (pid2 == -1) {
            perror("fork failed");
            exit(EXIT_FAILURE);
        } else if (pid2 == 0) { // Secondo livello di processo figlio per calcolo C
            z = calcolaC();
            printf("Ho finito di calcolare z = 6 - 2, task C\n");
            writeToFile("taskC.txt", z);
            exit(EXIT_SUCCESS); // Uso exit(z) per passare il risultato al processo padre
        } else {
            if (waitpid(pid2, &status2, 0) == -1) {
                perror("waitpid failed");
                exit(EXIT_FAILURE);
            }
            if (WIFEXITED(status2)) {
                z = readFromFile("taskC.txt");
                printf("Secondo figlio ritorna calcolo: %d\n", z);
                j = calcolaD(y,z); //y * z;
                printf("Calcolo task D = B*C (%d * %d) = %d\n", y, z, j);
                writeToFile("taskD.txt", j);
                exit(EXIT_SUCCESS); // Uso exit(j) per passare il risultato al processo padre
            }
            else{
                printf("Figlio %d NON terminato con successo %d", pid2, WEXITSTATUS(status1) );
                exit(EXIT_FAILURE);
            }
        }
    } else { // Processo padre per calcolo A
        x = computeA();
        printf("Ho finito di calcolare x = 3 * 4, task A\n");

        if (waitpid(pid1, &status1, 0) == -1) {
            perror("waitpid failed");
            exit(EXIT_FAILURE);
        }
        if (WIFEXITED(status1)) {
            j = readFromFile("taskD.txt");
            printf("Primo figlio ritorna calcolo: %d\n", j);
            x = calcolaE(x, j); //x + j;
            printf("Calcolo task E = A+D (%d + %d) = %d\n", computeA(), j, x);
        }
        else{
            printf("Figlio %d NON terminato con successo %d", pid1, WEXITSTATUS(status1) );
            return -1;
        }
    }
    return 0;
}
