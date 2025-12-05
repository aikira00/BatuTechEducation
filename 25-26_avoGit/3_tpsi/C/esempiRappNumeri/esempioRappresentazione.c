#include <stdio.h>
#include <stdint.h>   // int8_t, int16_t, int32_t, int64_t
#include <limits.h>   // per i range SHRT_MIN, SHRT_MAX, INT_MIN, INT_MAX

int main(void) {
    printf("Hello, World!\n");

    // --- Tipi "classici" ---
    short s = 30000;   // tipicamente 2 byte
    int   i = 10;      // tipicamente 4 byte

    printf("\nEsempio con short e int\n\n");

    printf("Valore di s (short) = %d\n", s);
    printf("Dimensione di short = %zu byte\n", sizeof(s));
    printf("Range short         = da %d a %d\n\n", SHRT_MIN, SHRT_MAX);

    printf("Valore di i (int)   = %d\n", i);
    printf("Dimensione di int   = %zu byte\n", sizeof(i));
    printf("Range int           = da %d a %d\n\n", INT_MIN, INT_MAX);

    // --- Tipi a larghezza fissa ---
    int8_t   a = 120;
    int16_t  b = 30000;
    int32_t  c = 2000000;
    int64_t  d = 9000000000LL; // suffisso LL per literal a 64 bit

    printf("Valori e dimensioni (tipi <stdint.h>):\n\n");

    printf("int8_t  a = %d (0x%X), dimensione = %zu byte\n",
           a, (unsigned char)a, sizeof(a));
    printf("int16_t b = %d (0x%X), dimensione = %zu byte\n",
           b, (unsigned short)b, sizeof(b));
    printf("int32_t c = %d (0x%X), dimensione = %zu byte\n",
           c, (unsigned int)c, sizeof(c));
    printf("int64_t d = %lld (0x%llX), dimensione = %zu byte\n",
           (long long)d, (unsigned long long)d, sizeof(d));

    return 0;
}