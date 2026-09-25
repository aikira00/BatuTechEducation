import java.util.Scanner;

public class EsercizioChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci un carattere: ");
        char c = sc.next().charAt(0);

        // 1) Codice Unicode: il cast a int mostra il valore numerico del char
        System.out.println("Codice Unicode: " + (int) c);

        // 2) Tipo di carattere, solo con confronti (niente Character.isXxx)
        boolean maiuscola = c >= 'A' && c <= 'Z';
        boolean minuscola = c >= 'a' && c <= 'z';
        boolean cifra     = c >= '0' && c <= '9';

        if (maiuscola) {
            System.out.println("Tipo: lettera maiuscola");
        } else if (minuscola) {
            System.out.println("Tipo: lettera minuscola");
        } else if (cifra) {
            System.out.println("Tipo: cifra");
        } else {
            System.out.println("Tipo: altro");
        }

        // 3) Valore numerico della cifra: '7' - '0' = 7
        if (cifra) {
            System.out.println("Valore numerico: " + (c - '0'));
        }

        // 4) Versione opposta (maiuscola <-> minuscola) con l'aritmetica
        //    'a' - 'A' = 32: distanza fissa tra minuscole e maiuscole
        if (maiuscola) {
            char opposta = (char) (c + ('a' - 'A'));
            System.out.println("Versione opposta: " + opposta);
        } else if (minuscola) {
            char opposta = (char) (c - ('a' - 'A'));
            System.out.println("Versione opposta: " + opposta);
        }

        // 5) Cesare con shift 3 e wrap-around (dopo 'z' si torna ad 'a')
        //    Si porta la lettera in 0..25, si somma 3, si fa % 26 e si riporta indietro
        char cifrato = c; // i caratteri che non sono lettere restano invariati
        if (minuscola) {
            cifrato = (char) ('a' + (c - 'a' + 3) % 26);
        } else if (maiuscola) {
            cifrato = (char) ('A' + (c - 'A' + 3) % 26);
        }
        System.out.println("Cesare (shift 3): " + cifrato);

        // Nota sul cast: c + 1 è un int, quindi senza cast stampa un numero
        System.out.println("c + 1          -> " + (c + 1));          // numero
        System.out.println("(char)(c + 1)  -> " + (char) (c + 1));   // carattere successivo
    }
}
