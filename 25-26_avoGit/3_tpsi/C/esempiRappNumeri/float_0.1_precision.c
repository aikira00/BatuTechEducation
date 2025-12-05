/* programma per dimostrare che 0.1 la cui conversione in base 2 è infinita
non si riesce a rappresentare in modo preciso.
Precisione standard: 0.100000
Tentativo di alta precisione: 0.100000001490116119384765625000
In sintesi, per numeri con molte cifre decimali e che richiedono precisione, 
come nel caso di applicazioni scientifiche, 
finanziarie o ingegneristiche, si dovrebbe utilizzare 
una libreria di precisione arbitraria o un tipo di dato decimale, 
disponibile in alcune altre lingue, ma non nativamente in C.
*/
#include <stdio.h>

int main() {
    float a = 0.1f;
    int b = 101;

    // Stampa con precisione standard (6 cifre dopo il punto decimale)
    printf("Precisione standard: %.6f\n", a);

    // Tentativo di stampare con 30 cifre decimali
    printf("Tentativo di alta precisione: %.30f\n", a);

    return 0;
}
