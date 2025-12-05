/*a = leggi a;
b = leggi b;
c = leggi c;
p1 = fork(AC);
p2 = fork(B1);
a1 = 2 * a;
join p1;
join p2;
d = sqrt(b1 - ac);
e = -b -d;
f = e/a1;

AC:
   ac = 4 * a * c;
   return ac;

B1:
   b 1 = b* b;
   return b1;
*/
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int computeA1(int a) {
    int a1 = 2 * a;
    return a1;
}

int computeAC(int a, int c) {
    int ac = 4 * a * c;
    return ac;
}

int computeB1(int b) {
    int b1 = b * b;
    return b1;
}

double computeD(int b1, int ac) {
    double d = sqrt((double)(b1 - ac));
    return d;
}

int computeE(int b, int d) {
    int e = (-b) - d;
    return e;
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
    int a, b, c, a1;
    int status1, status2;
    pid_t p1,p2;

    //leggi a, b e c
    //test with 5, 6 e 1 result should be -1.0000 etc.
    printf("Inserisci valore per variabile a\n");
    scanf("%d", &a);
    printf("Inserisci valore per variabile b\n");
    scanf("%d", &b);
    printf("Inserisci valore per variabile c\n");
    scanf("%d", &c);

    p1 = fork();

    if (p1 == -1){
        perror("Fork fallita");
        exit(-1);
    }
    else if(p1 == 0){
            //compute AC
            int ac = computeAC(a,c);
            writeToFile("taskAC.txt", ac);
            printf("Sono figlio %d, Ho finito di calcolare task AC => %d\n", getpid(), ac);
            exit(EXIT_SUCCESS);
    }
    else{
            p2 = fork();
            if(p2 ==-1){
                perror("fork fallita");
                return -1;
            }
            else if(p2 ==0){
                int b1 = computeB1(b);

                printf("Sono figlio %d, Ho finito di calcolare task B1 => %d\n", getpid(), b1);
                writeToFile("taskB1.txt", b1);
                exit(EXIT_SUCCESS);
            }
            else{
                //compute a1
                a1 = computeA1(a);
                printf("Ho finito di calcolare task A1 =>  %d\n", a1);
            }
    }

    printf("Aspetto primo figlio (join)\n");
    waitpid(p1, &status1, 0);
    if(WIFEXITED(status1)){
        //recupero b1
        int b1 = readFromFile("taskB1.txt");
        // e ac
        waitpid(p2, &status2, 0);
        if(WIFEXITED(status2)){
            int ac = readFromFile("taskAC.txt");
            double d = computeD(b1, ac);
            printf("Calcolo task D = sqrt(b1-ac) => sqrt(%d - %d) = %f\n", b1, ac, d);
            if(d>0){
                double e = computeE(b,d);
                printf("Calcolo task E = -b -d => -%d - %f = %f\n", b, d, e);
                double f = e/a1;
                printf("Calcolo task F = E\\A1 => (%f \\  %d) = %f\n", e, a1, f);
            }
            else{
                printf("Non esiste radice quadrata di numero negativo");
                return -1;
            }
        }
        else
            return -1;
    }
    else
        return -1;
    return 0;
}

