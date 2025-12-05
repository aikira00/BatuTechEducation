/*
// p = 2*(3 + 2) + (7 - 4) - [(2*3)*(5-1)] = 37
 versione in cui padre genera figli p1 p2 e p3
 per calcolare  B C1 C2
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

//calcolo eseguito da primo
int computeB(){
    return 7 - 4;
}

//calcolo eseguito da secondo figlio
int computeC1(){
    return 2 * 3;
}

//calcolo eseguito da terzo figlio
int computeC2(){
    return 5 - 1;
}

//calcolo eseguito dal padre
int computeA1(){
    return 3 + 2;
}

//calcolo eseguito dal padre
int computeC(int c1, int c2){
    return c1 * c2;
}

//calcolo eseguito dal padre
int computeD(int a, int b, int c){
    return a + b + c;
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
    //id processi figli
    pid_t p1, p2, p3;
    //stato processi figli
    int status1, status2, status3;
    p1 = fork();
    //variabili calcolo
    int a, b, c, c1, c2, d;

    if(p1 ==-1){
        perror("fork fallita");
        return -1;
    }
    else if (p1 == 0){
        b = computeB();
        printf("Sono figlio %d, Ho finito di calcolare task B => %d\n", getpid(), b);
        writeToFile("taskB.txt", b);
        exit(EXIT_SUCCESS);
    }
    else{
        //padre, fork secondo figlio per C1
        p2 = fork();
        if(p2 ==-1){
            perror("fork fallita");
            return -1;
        }
        else if (p2 == 0){
            c1 = computeC1();
            printf("Sono figlio %d, Ho finito di calcolare task C1 => %d\n", getpid(), c1);
            writeToFile("taskC1.txt", c1);
            exit(EXIT_SUCCESS);
        }
        else{
            //padre, fork terzo figlio per C2
            p3 = fork();
            if(p3 ==-1){
                perror("fork fallita");
                return -1;
            }
            else if (p3 == 0){
                c2 = computeC2();
                printf("Sono figlio %d, Ho finito di calcolare task C2 => %d\n", getpid(), c2);
                writeToFile("taskC2.txt", c2);
                exit(EXIT_SUCCESS);
            }
            else {
                //calcolo padre
                a = 2 * computeA1();
                printf("Sono padre %d, Ho finito di calcolare task A => %d\n", getpid(), a);

            }
        }
    }

    //join
    printf("Aspetto primo figlio B (join)\n");
    waitpid(p1, &status1, 0);
    if (WIFEXITED(status1)) {
        b = readFromFile("taskB.txt");
        printf("Aspetto secondo figlio C1\n");
        waitpid(p2, &status2, 0);
        if(WIFEXITED(status2)){
            c1 = readFromFile("taskC1.txt");
            printf("Aspetto terzo figlio C2\n");
            waitpid(p3, &status3, 0);
            if(WIFEXITED(status3)){
                c2 = readFromFile("taskC2.txt");
            }
            else{
                printf("Figlio %d NON terminato con successo %d", p3, WEXITSTATUS(status3));
                return -1;
            }

        }
        else{
            printf("Figlio %d NON terminato con successo %d", p2, WEXITSTATUS(status2) );
            return -1;
        }
    }
    else{
        printf("Figlio %d NON terminato con successo %d", p1, WEXITSTATUS(status1) );
        return -1;
    }

    //calcoli padre
    c = computeC(c1, c2);
    printf("Sono padre %d, Ho finito di calcolare task C => %d\n", getpid(), c);

    d = computeD(a,b,c);
    printf("Sono padre %d, Ho finito di calcolare task D => %d\n", getpid(), d);

    printf("valore finale %d", d);
    return 0;

}