//
// Created by Cristina Battaglino on 23/10/25.
//

#include <stdio.h>
#include <stdint.h>

// Funzione per stampare un numero in binario (8 bit)
void stampa_binario(uint8_t n) {
    for (int i = 7; i >= 0; i--) {
        printf("%d", (n >> i) & 1);
        if (i == 4) printf(" "); // Separa i 4 bit più significativi
    }
}

// Funzione per stampare separatore
void stampa_separatore() {
    printf("\n========================================================\n\n");
}

int main() {
    printf("DIMOSTRAZIONE OVERFLOW SU 8 BIT\n");
    printf("========================================================\n\n");

    // ===== 1. OVERFLOW CON INTERI SENZA SEGNO (UNSIGNED) =====
    printf("1. OVERFLOW CON UNSIGNED (0-255)\n");
    printf("----------------------------------------\n");

    //Overflow Unsigned: Mostra come la somma di 200 + 100 = 300 diventi 44 su 8 bit (perché 300 % 256 = 44)
    uint8_t u1 = 200;
    uint8_t u2 = 100;
    uint8_t u_somma = u1 + u2;

    printf("Operazione: %u + %u\n", u1, u2);
    printf("Risultato teorico: %d (supera 255)\n", u1 + u2);
    printf("Risultato su 8 bit: %u\n\n", u_somma);

    printf("In binario:\n");
    printf("  "); stampa_binario(u1); printf(" (%u)\n", u1);
    printf("+ "); stampa_binario(u2); printf(" (%u)\n", u2);
    printf("  ----------------\n");
    printf("  "); stampa_binario(u_somma); printf(" (%u) <- OVERFLOW!\n", u_somma);
    printf("\nIl bit di riporto viene perso, il risultato 'riparte' da 0\n");

    stampa_separatore();

    // ===== 2. OVERFLOW CON INTERI CON SEGNO (SIGNED - CA2) =====
    printf("2. OVERFLOW CON SIGNED CA2 (-128 a +127)\n");
    printf("----------------------------------------\n");

    //Overflow Signed Positivo: Due numeri positivi (100 + 50 = 150) che superano +127 e diventano negativi (-106)
    // Caso A: Due numeri positivi che danno overflow
    int8_t s1 = 100;
    int8_t s2 = 50;
    int8_t s_somma = s1 + s2;

    //
    printf("Caso A: Overflow positivo\n");
    printf("Operazione: %d + %d\n", s1, s2);
    printf("Risultato teorico: %d (supera +127)\n", s1 + s2);
    printf("Risultato su 8 bit: %d\n\n", s_somma);

    printf("In binario:\n");
    printf("  "); stampa_binario((uint8_t)s1); printf(" (+%d)\n", s1);
    printf("+ "); stampa_binario((uint8_t)s2); printf(" (+%d)\n", s2);
    printf("  ----------------\n");
    printf("  "); stampa_binario((uint8_t)s_somma); printf(" (%d) <- OVERFLOW!\n", s_somma);
    printf("\nRisultato positivo diventa negativo!\n\n");

    //Overflow Signed Negativo: Due numeri negativi (-100 + -50 = -150) che vanno sotto -128 e diventano positivi (+106)
    // Caso B: Due numeri negativi che danno overflow
    int8_t s3 = -100;
    int8_t s4 = -50;
    int8_t s_somma2 = s3 + s4;

    printf("Caso B: Overflow negativo\n");
    printf("Operazione: %d + %d\n", s3, s4);
    printf("Risultato teorico: %d (sotto -128)\n", s3 + s4);
    printf("Risultato su 8 bit: %d\n\n", s_somma2);

    printf("In binario:\n");
    printf("  "); stampa_binario((uint8_t)s3); printf(" (%d)\n", s3);
    printf("+ "); stampa_binario((uint8_t)s4); printf(" (%d)\n", s4);
    printf("  ----------------\n");
    printf("  "); stampa_binario((uint8_t)s_somma2); printf(" (%d) <- OVERFLOW!\n", s_somma2);
    printf("\nRisultato negativo diventa positivo!\n");

    stampa_separatore();

    //Sottrazione con Overflow: Mostra sia underflow unsigned (50 - 100) che overflow signed (-100 - 50)
    // ===== 3. SOTTRAZIONE CON OVERFLOW =====
    printf("3. OVERFLOW NELLA SOTTRAZIONE\n");
    printf("----------------------------------------\n");

    // Sottrazione unsigned
    uint8_t u3 = 50;
    uint8_t u4 = 100;
    uint8_t u_diff = u3 - u4;

    printf("Unsigned: %u - %u\n", u3, u4);
    printf("Risultato teorico: %d\n", u3 - u4);
    printf("Risultato su 8 bit: %u (underflow!)\n\n", u_diff);

    printf("In binario:\n");
    printf("  "); stampa_binario(u3); printf(" (%u)\n", u3);
    printf("- "); stampa_binario(u4); printf(" (%u)\n", u4);
    printf("  ----------------\n");
    printf("  "); stampa_binario(u_diff); printf(" (%u)\n\n", u_diff);

    // Sottrazione signed
    int8_t s5 = -100;
    int8_t s6 = 50;
    int8_t s_diff = s5 - s6;

    printf("Signed: %d - %d\n", s5, s6);
    printf("Risultato teorico: %d (sotto -128)\n", s5 - s6);
    printf("Risultato su 8 bit: %d (overflow!)\n\n", s_diff);

    printf("In binario:\n");
    printf("  "); stampa_binario((uint8_t)s5); printf(" (%d)\n", s5);
    printf("- "); stampa_binario((uint8_t)s6); printf(" (%d)\n", s6);
    printf("  ----------------\n");
    printf("  "); stampa_binario((uint8_t)s_diff); printf(" (%d)\n", s_diff);

    stampa_separatore();

    //Moltiplicazione con Overflow: 20 × 10 = 200 che su signed 8 bit diventa -56
    // ===== 4. MOLTIPLICAZIONE CON OVERFLOW =====
    printf("4. OVERFLOW NELLA MOLTIPLICAZIONE\n");
    printf("----------------------------------------\n");

    int8_t m1 = 20;
    int8_t m2 = 10;
    int8_t m_prod = m1 * m2;

    printf("Operazione: %d × %d\n", m1, m2);
    printf("Risultato teorico: %d (supera +127)\n", m1 * m2);
    printf("Risultato su 8 bit: %d\n\n", m_prod);

    printf("In binario:\n");
    printf("  "); stampa_binario((uint8_t)m1); printf(" (%d)\n", m1);
    printf("× "); stampa_binario((uint8_t)m2); printf(" (%d)\n", m2);
    printf("  ----------------\n");
    printf("  "); stampa_binario((uint8_t)m_prod); printf(" (%d) <- OVERFLOW!\n", m_prod);

    stampa_separatore();

    //Riepilogo dei limiti: Mostra i valori min/max in binario per entrambi i tipi
    // ===== 5. RIEPILOGO LIMITI =====
    printf("5. RIEPILOGO LIMITI 8 BIT\n");
    printf("----------------------------------------\n");
    printf("UNSIGNED (uint8_t):\n");
    printf("  Range: 0 a 255\n");
    printf("  Min: "); stampa_binario(0); printf(" = 0\n");
    printf("  Max: "); stampa_binario(255); printf(" = 255\n\n");

    printf("SIGNED CA2 (int8_t):\n");
    printf("  Range: -128 a +127\n");
    printf("  Min: "); stampa_binario(128); printf(" = -128\n");
    printf("  Max: "); stampa_binario(127); printf(" = +127\n");
    printf("  Zero: "); stampa_binario(0); printf(" = 0\n");

    printf("\n========================================================\n");

    return 0;
}
