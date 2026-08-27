/*
 * Demo "compilo una volta, eseguo ovunque" — versione C.
 *
 * Il programma dice su che macchina sta girando. Ma attenzione a COME lo sa:
 * le righe qui sotto le sceglie il PREPROCESSORE, prima della compilazione.
 * Nell'eseguibile finisce UNA SOLA di queste stringhe, e ci resta per sempre.
 */

#include <stdio.h>

int main(void) {
    printf("Ciao 4A! Sono un programma in C.\n");

#if   defined(__aarch64__)
    printf("  architettura : ARM64\n");
#elif defined(__x86_64__)
    printf("  architettura : x86-64\n");
#else
    printf("  architettura : boh\n");
#endif

#if   defined(_WIN32)
    printf("  sistema      : Windows\n");
#elif defined(__APPLE__)
    printf("  sistema      : macOS\n");
#elif defined(__linux__)
    printf("  sistema      : Linux\n");
#endif

    printf("Lo sapevo gia' da quando mi hanno COMPILATO.\n");
    return 0;
}
