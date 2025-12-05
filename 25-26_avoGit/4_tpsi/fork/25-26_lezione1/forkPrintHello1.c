#include <stdio.h>
#include <unistd.h>

int main() {
    int a = 5;
    printf("chiamo la fork... quante print?\n");

    pid_t pid = fork();
    printf("Ciao!\n");
    return 0;
}
