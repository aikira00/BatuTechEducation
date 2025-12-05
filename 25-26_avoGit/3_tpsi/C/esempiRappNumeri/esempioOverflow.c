#include <stdio.h>
#include <stdint.h>   // int8_t, int16_t, int32_t, int64_t

int main() {

    int8_t   a = 120;
    int16_t  b = 120;
    printf("Valori iniziali:\n");
    printf("int8_t a = %d\n", a);
    printf("int16_t b = %d\n\n", b);
    a = 300;
    b = 300;
    printf("Dopo:\n");
    printf("int8_t a = %d\n", a);
    printf("int16_t b = %d\n\n", b);


    //con tipi standard
    short s = 32767;   // massimo valore per short con segno (2 byte)
    int i = 32767;     // lo stesso valore ma dentro un int

    printf("Valori iniziali:\n");
    printf("short s = %d\n", s);
    printf("int   i = %d\n\n", i);

    // Aggiungiamo 1
    s = s + 1;
    i = i + 1;

    printf("Dopo aver aggiunto 1:\n");
    printf("short s = %d   <-- overflow!\n", s);
    printf("int   i = %d   <-- ok\n", i);


    return 0;
}