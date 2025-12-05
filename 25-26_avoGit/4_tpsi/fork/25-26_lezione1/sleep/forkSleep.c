#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();
    
    if (pid > 0) {
        // Padre
        sleep(1);  // Aspetta 1 secondo
        printf("PADRE eseguito DOPO il figlio\n");
    } else {
        // Figlio
        printf("FIGLIO eseguito PRIMA del padre\n");
        sleep(2);  // Continua a lavorare
        printf("FIGLIO termina\n");
    }
    
    return 0;
}