#include <stdio.h>
#include <unistd.h>

int main() {
    int a = 5;
    printf("chiamo la fork... quante print?\n");
    pid_t pid = fork();
    printf("chiamo la fork... quante print?\n");
    printf("Ciao!\n");
    pid = fork();
    printf("chiamo la fork... quante print?\n");
    pid = fork();
    printf("chiamo la fork... quante print?\n");
    pid = fork();
    return 0;
}