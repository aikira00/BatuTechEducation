#include <stdio.h>

int main() {
    char carattere;
    
    printf("Inserisci un carattere: ");
    scanf("%c", &carattere);
    
    // Stampa il carattere
    printf("\nCarattere inserito: %c\n", carattere);
    
    // Stampa il byte (valore ASCII in decimale)
    printf("Valore ASCII (decimale): %d\n", (unsigned char)carattere);
    
    // Classificazione con codici ASCII numerici
    printf("Tipo: ");
    if (carattere >= 65 && carattere <= 90) {
        // A=65, Z=90
        printf("Lettera maiuscola ASCII\n");
    } else if (carattere >= 97 && carattere <= 122) {
        // a=97, z=122
        printf("Lettera minuscola ASCII\n");
    } else if (carattere >= 0 && carattere <= 127) {
        // ASCII valido: 0-127
        printf("Altro carattere ASCII\n");
    } else {
        // Valori > 127: non ASCII
        printf("Carattere NON ASCII\n");
    }
    
    return 0;
}