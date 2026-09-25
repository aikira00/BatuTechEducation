import java.util.Scanner;

public class GestioneClasse {

    // Legge un intero da tastiera e ripete la domanda finché l'input non è valido.
    // Usa nextLine() + parseInt() per evitare il problema dell'invio rimasto nel buffer.
    static int leggiIntero(Scanner sc, String messaggio) {
        while (true) {
            System.out.print(messaggio);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero intero valido.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] allievi = null;   // ogni elemento: "Cognome Nome"
        int[] eta = null;          // eta[i] è l'età di allievi[i]
        int scelta;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Carica allievi ed età");
            System.out.println("2. Stampa gli allievi minorenni");
            System.out.println("0. Esci");
            scelta = leggiIntero(sc, "Scelta: ");

            switch (scelta) {
                case 1:
                    int n;
                    do {
                        n = leggiIntero(sc, "Quanti allievi ci sono in classe? ");
                    } while (n <= 0);

                    // i due vettori vengono creati con la dimensione scelta
                    allievi = new String[n];
                    eta = new int[n];

                    for (int i = 0; i < n; i++) {
                        System.out.print("Allievo " + (i + 1) + " (Cognome Nome): ");
                        allievi[i] = sc.nextLine();

                        do {
                            eta[i] = leggiIntero(sc, "Età: ");
                        } while (eta[i] < 0);
                    }
                    System.out.println("Dati caricati.");
                    break;

                case 2:
                    if (allievi == null) {
                        System.out.println("Prima devi caricare i dati (voce 1).");
                        break;
                    }
                    System.out.println("\nAllievi minorenni:");
                    int trovati = 0;
                    for (int i = 0; i < allievi.length; i++) {
                        if (eta[i] < 18) {
                            System.out.println("- " + allievi[i] + " (" + eta[i] + " anni)");
                            trovati++;
                        }
                    }
                    if (trovati == 0) {
                        System.out.println("Nessun allievo minorenne.");
                    }
                    break;

                case 0:
                    System.out.println("Arrivederci!");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);
    }
}
