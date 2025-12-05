//
// Progettare un programma in cui un processo padre accetti in input due valori numerici interi e,
// successivamente generi quattro processi figli che eseguano le quattro operazioni aritmetiche,
// restituendo al processo padre il relativo risultato. Il processo padre attenderà la terminazione di ogni figlio,
// visualizzando a video il risultato.
//Effettuare le prove con i valori (7, 4) e (4, 7) e osservare il comportamento del programma.
//
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define ADD "add.txt"
#define SUB "sub.txt"
#define MUL "mul.txt"
#define DIV "div.txt"


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
    //pid1 somma pid2 moltp pid 3 sott pid 4 divisione
    pid_t pid1, pid2, pid3, pid4;
    int status1, status2, status3, status4;
    int a, b, add, mul, sub, div; //variabili input

    printf("Inserisci valore per variabile a\n");
    scanf("%d", &a);
    printf("Inserisci valore per variabile b\n");
    scanf("%d", &b);

    //processo padre
    pid1 = fork();
    if (pid1 == -1) {
        // Errore durante la creazione del processo figlio
        perror("fork failed");
        exit(EXIT_FAILURE);
    } else if(pid1==0){//calcolo somma
        printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
        //calcolo espressione B
        add = a+b;
        writeToFile(ADD, add);
        printf("Ho finito di calcolare la somma  => %d\n", add);
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
            printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
            //calcolo espressione B
            mul = a*b;
            writeToFile(MUL, mul);
            printf("Ho finito di calcolare la moltiplicazione  => %d\n", mul);
            exit(EXIT_SUCCESS);
        }
        else{
            printf("Sono processo padre con PID %d\n", getpid());
            pid3 = fork();
            if (pid3 == -1) {
                // Errore durante la creazione del processo figlio
                perror("fork failed");
                exit(EXIT_FAILURE);
            } else if(pid3==0){
                printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
                //calcolo espressione B
                sub = a-b;
                writeToFile(SUB, sub);
                printf("Ho finito di calcolare la sottrazione  => %d\n", sub);
                exit(EXIT_SUCCESS);
            }
            else{
                printf("Sono processo padre con PID %d\n", getpid());
                pid4 = fork();
                if (pid4 == -1) {
                    // Errore durante la creazione del processo figlio
                    perror("fork failed");
                    exit(EXIT_FAILURE);
                } else if(pid4==0){
                    printf("Sono processo figlio con PID %d, mio padre è %d\n", getpid(), getppid());
                    //calcolo espressione B
                    div = a/b;
                    writeToFile(DIV, div);
                    printf("Ho finito di calcolare la divisione... ATTENZIONE NON CONTROLLO OPERAZIONE LECITA  => %d\n", div);
                    exit(EXIT_SUCCESS);
                }
                else{
                    printf("Sono processo padre con PID %d, aspetto i figli \n", getpid());
                    waitpid(pid1, &status1, 0);
                    waitpid(pid2, &status2, 0);
                    waitpid(pid3, &status3, 0);
                    waitpid(pid4, &status4, 0);
                    if(WIFEXITED(status1)&& WIFEXITED(status2)&& WIFEXITED(status3) && WIFEXITED(status4))d{
                     //tutto bene
                     add = readFromFile(ADD);
                     sub = readFromFile(SUB);
                     mul = readFromFile(MUL);
                     div = readFromFile(DIV);
                     printf(" addizione  => %d\n", add);
                     printf(" sottrazione  => %d\n", sub);
                     printf(" moltiplicazione  => %d\n", mul);
                     printf(" divisione  => %d\n", div);

                    }
                    else{
                        printf("Sempre guai questi figli... fallimento");
                        return -1;
                    }
                }
            }
        }
    }
    return 0;
}
