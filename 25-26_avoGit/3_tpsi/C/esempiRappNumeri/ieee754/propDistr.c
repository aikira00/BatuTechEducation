#include <stdio.h>
#include <math.h>


void test_proprieta_distributiva() {
    printf("TEST 1: PROPRIETA' DISTRIBUTIVA\n");
    
    // Esempio 1: numeri con ordini di grandezza diversi
    float a = 1.0e10f;
    float b = 1.0f;
    float c = -1.0f;

    float lato_sx = a * (b + c);
    float lato_dx = (a * b) + (a * c);

    printf("a = %.1e, b = %.1f, c = %.1f\n\n", a, b, c);
    printf("a * (b + c) = %.10e\n", lato_sx);
    printf("a*b + a*c   = %.10e\n", lato_dx);
    printf("Differenza  = %.10e\n", lato_sx - lato_dx);
    printf("Sono uguali? %s\n", (lato_sx == lato_dx) ? "SI" : "NO");
}

void test_proprieta_associativa() {
    printf("TEST 2: PROPRIETA' ASSOCIATIVA\n");
    
    float a = 1.0e20f;
    float b = -1.0e20f;
    float c = 1.0f;
    
    float risultato1 = (a + b) + c;
    float risultato2 = a + (b + c);
    
    printf("a = %.1e, b = %.1e, c = %.1f\n\n", a, b, c);
    printf("(a + b) + c = %.10f\n", risultato1);
    printf("a + (b + c) = %.10f\n", risultato2);
    printf("Differenza  = %.10e\n", risultato1 - risultato2);
    printf("Sono uguali? %s\n", (risultato1 == risultato2) ? "SI" : "NO");
}

int main() {
    printf("DIMOSTRAZIONE DEI LIMITI DEL FLOATING POINT\n");
    printf("===========================================\n");
    
    test_proprieta_distributiva();
    test_proprieta_associativa();

    printf("1. Le proprietà algebriche NON valgono sempre\n");
    printf("2. MAI usare == per confrontare float/double\n");
    printf("3. Usare sempre epsilon per i confronti\n");
    printf("4. Fare attenzione all'ordine delle operazioni\n");
    printf("5. Evitare sottrazioni tra numeri molto simili\n");
    printf("========================================\n\n");
    
    return 0;
}