#include <stdio.h>
#include <stdint.h>

int main() {
    // 1. Dichiarazione variabili (8 bit)
    uint8_t a = 150;
    uint8_t b = 50;
    int8_t c = 100;
    int8_t d = 25;
    int8_t e = -80;
    int8_t f = -20;
    
    //Problemi di rappresentazione
    printf("=== ANALISI INTERVALLO RAPPRESENTABILE ===\n\n");
    printf("Variabile a contiene: %u\n", a);
    printf("Variabile b contiene: %u\n", b);
    printf("Variabile c contiene: %d\n", c);
    printf("Variabile d contiene: %d\n", d);
    printf("Variabile e contiene: %d\n", e);
    printf("Variabile f contiene: %d\n", f);

    //RIASSEGNA VALORI ALLE VARIABILI FUORI INTERVALLO e RISTAMPA VALORI
    a = 320;
    c = 145;
    e = -145;
    printf("Variabile a contiene, giusto??: %u\n", a);
    printf("Variabile c contiene, giusto??: %d\n", c);
    printf("Variabile e contiene, giusto??: %d\n", e);
    printf("\n\n");
    a = 150;
    c = 100;
    e = -80;

    // Dichiarazione variabili per i risultati
    uint8_t risultato1; // unsigned
    int8_t risultato2, risultato3, risultato4; // signed
    uint8_t risultato5; // unsigned

    printf("=== ANALISI OVERFLOW E CA2 ===\n\n");

    // --- OPERAZIONE 1a: unsigned + unsigned ---
    risultato1 = a + b;
    printf("Operazione 1: %u + %u (unsigned)\n", a, b);
    printf("Risultato ottenuto: %u\n", risultato1);
    printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b); // Uso uint16_t per il risultato teorico
    printf("---------------------------------------\n");

     // --- OPERAZIONE 1b: unsigned + unsigned (Overflow) ---
    b = 200;
    risultato1 = a + b;
    printf("Operazione 1: %u + %u (unsigned)\n", a, b);
    printf("Risultato ottenuto: %u\n", risultato1);
    printf("Risultato teorico: %u\n", (uint16_t)a + (uint16_t)b); // Uso uint16_t per il risultato teorico
    printf("---------------------------------------\n");


    // --- OPERAZIONE 2a: signed + signed positivi ---
    risultato2 = c + d;
    printf("Operazione 2: %d + %d (signed)\n", c, d);
    printf("Risultato ottenuto: %d\n", risultato2);
    printf("Risultato teorico: %d\n", c + d);
    printf("---------------------------------------\n");

    // --- OPERAZIONE 2b: signed + signed positivi (Overflow) ---
    d = 65;
    risultato2 = c + d;
    printf("Operazione 2: %d + %d (signed)\n", c, d);
    printf("Risultato ottenuto: %d\n", risultato2);
    printf("Risultato teorico: %d\n", c + d);
    printf("---------------------------------------\n");

    // --- OPERAZIONE 3a: signed + signed negativi ---
    risultato3 = e + f;
    printf("Operazione 3: %d + %d (signed)\n", e, f);
    printf("Risultato ottenuto: %d\n", risultato3);
    printf("Risultato teorico: %d\n", e + f);
    printf("---------------------------------------\n");

      // --- OPERAZIONE 3b: signed + signed negativi (Overflow) ---
    f = -60;
    risultato3 = e + f;
    printf("Operazione 3: %d + %d (signed)\n", e, f);
    printf("Risultato ottenuto: %d\n", risultato3);
    printf("Risultato teorico: %d\n", e + f);
    printf("---------------------------------------\n");

    // --- OPERAZIONE 4: signed - signed (Sottrazione con scarto base,no overflow) ---
    c = 100;
    e = -80;
    risultato4 = c + e;
    printf("Operazione 4: %d + (%d) (signed)\n", c, e);
    printf("Risultato ottenuto: %d\n", risultato4);
    printf("Risultato teorico: %d\n", c + e);
    printf("---------------------------------------\n");

    // --- OPERAZIONE 5: unsigned - unsigned (rappresentazione) ---
    //attenzione qui 11001110 viene rapprsentation come senza segno
    risultato5 = a - b;
    printf("Operazione 5: %u - %u (unsigned)\n", a, b);
    printf("Risultato ottenuto: %u\n", risultato5);
    printf("Risultato teorico: %d\n", (int16_t)a - (int16_t)b); // Uso int16_t per il risultato teorico (negativo)
    printf("---------------------------------------\n");

    a = 150;
    c = 100;
    d = 20;
    printf("Risultato teorico: %d\n", c);
    printf("Risultato teorico a: %d\n", a);
    printf("Risultato teorico: %d\n", c + d);
    return 0;
}