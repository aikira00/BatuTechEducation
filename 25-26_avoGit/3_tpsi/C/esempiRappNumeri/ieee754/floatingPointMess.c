#include <stdio.h>

int main() {
    // =====================================================
    // ROUND 1: Confronto di x=1.1 con il letterale 1.1
    // =====================================================
    printf("\n# Confronto di x=1.1 con 1.1 #\n");

    float xf = 1.1;
    if (xf != 1.1) printf("In Float 1.1 risulta diverso da 1.1\n");
    else printf("In Float 1.1 risulta uguale a 1.1\n");

    double xd = 1.1;
    if (xd != 1.1) printf("In Double 1.1 risulta diverso da 1.1\n");
    else printf("In Double 1.1 risulta uguale a 1.1\n");

    printf(" double xd%.30f\n ", xd);
    printf(" float xf%.30f\n ", xf);

    // =====================================================
    // ROUND 2: Confronto di 4 valori tutti uguali a 1/3
    // =====================================================
    printf("\n\n# Confronto di 4 valori a,b,c,d uguali tra loro #");
    printf("\ne tutti 'matematicamente' uguali a 1/3 #");
    printf("\n\nIn particolare:\na=0.1/0.3 b=1.0/3.0 (Double)");
    printf("\nc=0.1/0.3 d=1.0/3.0 (Float)");

    double a=0.1/0.3;
    double b=1.0/3.0;

    float c=0.1/0.3;
    float d=1.0/3.0;

    printf("\nUna stampa di verifica, prima dei confronti");
    printf("\na=%lf \t b=%lf",a,b);
    printf("\nc=%f \t d=%f",c,d);

    printf("\nAndiamo a verificare i confronti...\n");

    if (a==b) printf("\nIn Double a e b risultano uguali");
    else printf("\nIn Double a e b risultano diversi");

    if (c==d) printf("\nIn Float c e d risultano uguali");
    else printf("\nIn Float c e d risultano diversi");

    // =====================================================
    // Spiegazione: guardiamo più da vicino i valori
    // =====================================================
    printf("\nInfatti a ben guardare...\n");

    printf("\na=%.20lf \t b=%.20lf",a,b);

    printf("\nc=%.20f \t d=%.20f",c,d);

    printf("\nAspetta, ma x valeva 1.1 oppure no?\n");
    printf("\nIn Float: xf=%.20f",xf);
    printf("\nIn Double: xd=%.20lf",xd);

    // =====================================================
    // ROUND 3: Un solo centesimo
    // =====================================================
    printf("\n\nE quindi meglio double o float o fa lo stesso?");

    printf("\n\"Vabbè, in fondo cosa importa,");
    printf("parliamo di piccole imprecisioni!\"\n");

    float cent_float=0.01;
    double cent_double=0.01;

    printf("\nPoniamo che sia questione di un solo centesimo:");
    printf("Quale centesimo sceglieresti? Il Float o il Double?");

    printf("\n(FLOAT DATA TYPE)\n 1 centesimo=%.20f",cent_float);

    printf("\n(DOUBLE DATA TYPE)\n 1 centesimo=%.20lf",cent_double);

    printf("\nForse dipende: se soldi che devi dare è un conto");
    printf("\nse sono soldi che devi ricevere è un altro\n");

    return 0;
}