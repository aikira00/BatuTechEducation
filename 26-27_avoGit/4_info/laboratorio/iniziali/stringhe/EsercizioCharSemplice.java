import java.util.Scanner;

public class EsercizioCharSemplice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lettura: si legge sempre una riga (String) e si prende il primo carattere
        System.out.print("Inserisci una parola: ");
        String riga = sc.nextLine();
        for (int i = 0; i<riga.length(); i++){
            char c = riga.charAt(i);

            // 1) Codice Unicode: il cast a int mostra il numero associato al carattere
            System.out.println("Codice Unicode: " + (int) c);

            // 2) Tipo di carattere, usando solo confronti (niente Character.isXxx)
            if (c >= 'A' && c <= 'Z') {
                System.out.println("Tipo: lettera maiuscola");
            } else if (c >= 'a' && c <= 'z') {
                System.out.println("Tipo: lettera minuscola");
            } else if (c >= '0' && c <= '9') {
                System.out.println("Tipo: cifra");
            } else {
                System.out.println("Tipo: altro");
            }

            // 3) Se è una cifra, il suo valore numerico: '7' - '0' = 7
            if (c >= '0' && c <= '9') {
                int valore = c - '0';
                System.out.println("Valore numerico: " + valore);
            }

            // 4) Se è una lettera, la versione opposta usando solo l'aritmetica.
            //    Tra una minuscola e la sua maiuscola c'è sempre la stessa distanza:
            //    'a' - 'A' = 32
            if (c >= 'A' && c <= 'Z') {
                // maiuscola -> minuscola: aggiungo la distanza
                char opposta = (char) (c + ('a' - 'A'));
                System.out.println("Versione opposta: " + opposta);
            } else if (c >= 'a' && c <= 'z') {
                // minuscola -> maiuscola: tolgo la distanza
                char opposta = (char) (c - ('a' - 'A'));
                System.out.println("Versione opposta: " + opposta);
            }
        }

    }
}
