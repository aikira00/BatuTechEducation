package catalogoArray;

// "Dallo scenario al codice: il jukebox dell'intervallo" (esercitazione 08): il catalogo è un array di Canzone nel main.
// Il main non controlla mai le regole della canzone (genere, durata): controlla solo l'input dell'utente.
// Niente try/catch. Si confrontano stringhe e interi, mai canzoni.
import java.util.Random;
import java.util.Scanner;

public class RadioIntervallo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        Canzone[] canzoni = new Canzone[20];   // posto per 20 canzoni

        // Il catalogo di partenza: le canzoni della mail
        canzoni[0] = new Canzone("Campanella", "I Pendolari", "pop", 3 * 60 + 12,
                "Suona la campanella e tutto si accende,\ndieci minuti e il mondo si arrende.");
        canzoni[1] = new Canzone("Ultima fila", "I Pendolari", "rock", 4 * 60 + 5,
                "In ultima fila, vicino alla finestra,\nguardo le nuvole e penso alla festa.");
        canzoni[2] = new Canzone("Interrogato", "MC Verifica", "rap", 2 * 60 + 58,
                "Scende il dito sul registro, trattengo il respiro,\npassa la C, passa la D, e sono ancora vivo.");
        canzoni[3] = new Canzone("Ricreazione", "Lady Merenda", "pop", 0,
                "Pizzetta in una mano, nell'altra il telefono,\ndieci minuti da regina e poi torno al mio trono.");
        canzoni[4] = new Canzone("Nebbia in tangenziale", "I Lampioni", "indie", 4 * 60 + 41,
                "C'è nebbia in tangenziale e la radio non prende,\ncanto da sola una canzone che nessuno sente.");
        canzoni[5] = new Canzone("Campanella", "Gli Assenti", "Rock", 3 * 60 + 45,
                "Campanella, aspetta, non suonare adesso,\nmi manca un esercizio, te lo prometto.");
        canzoni[6] = new Canzone("Caffè delle sette", "Trio Moka", "jazz", 5 * 60 + 20,
                "Caffè delle sette, il bar è ancora vuoto,\nil sole si alza piano. Io no.");
        canzoni[7] = new Canzone("Intro", "Gli Assenti", "rock", 1 * 60 + 15);
        canzoni[8] = new Canzone("Piano terra", "Quartetto Ascensore", "lounge", 2 * 60 + 30);
        int quante = 9;                          // quante celle sono occupate davvero

        String scelta = "";   // qualsiasi valore diverso da "0", per entrare nel ciclo
        while (!scelta.equals("0")) {
            System.out.println();
            System.out.println("=== RADIO INTERVALLO ===");
            System.out.println("1. Elenco");
            System.out.println("2. Cerca per titolo");
            System.out.println("3. Cerca per cantante");
            System.out.println("4. Suona");
            System.out.println("5. A caso per genere");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            scelta = sc.nextLine().trim();

            if (scelta.equals("1")) {
                for (int i = 0; i < quante; i++) {
                    System.out.println((i + 1) + ". " + canzoni[i]);
                }

            } else if (scelta.equals("2")) {
                System.out.print("Titolo da cercare: ");
                String cercato = sc.nextLine().trim();
                int trovate = 0;
                for (int i = 0; i < quante; i++) {
                    if (canzoni[i].getTitolo().equalsIgnoreCase(cercato)) {
                        System.out.println((i + 1) + ". " + canzoni[i]);
                        trovate++;
                    }
                }
                if (trovate == 0) {
                    System.out.println("Nessuna canzone con il titolo \"" + cercato + "\"");
                }

            } else if (scelta.equals("3")) {
                System.out.print("Cantante da cercare: ");
                String cercato = sc.nextLine().trim();
                int trovate = 0;
                for (int i = 0; i < quante; i++) {
                    if (canzoni[i].getArtista().equalsIgnoreCase(cercato)) {
                        System.out.println((i + 1) + ". " + canzoni[i]);
                        trovate++;
                    }
                }
                if (trovate == 0) {
                    System.out.println("Nessuna canzone di \"" + cercato + "\"");
                }

            } else if (scelta.equals("4")) {
                System.out.print("Numero della canzone: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è la canzone \"" + rigaNumero + "\"");
                } else {
                    canzoni[pos].play();
                }

            } else if (scelta.equals("5")) {
                System.out.print("Genere: ");
                String genere = sc.nextLine().trim();

                // prima passata: dove stanno le canzoni di quel genere
                int[] posizioni = new int[quante];
                int trovate = 0;
                for (int i = 0; i < quante; i++) {
                    if (canzoni[i].getGenere().equalsIgnoreCase(genere)) {
                        posizioni[trovate] = i;
                        trovate++;
                    }
                }

                if (trovate == 0) {
                    System.out.println("Nessuna canzone di genere \"" + genere + "\"");
                } else {
                    // nextInt(trovate) restituisce un intero fra 0 e trovate-1: solo le celle riempite
                    int pos = posizioni[rnd.nextInt(trovate)];
                    canzoni[pos].play();
                }

            } else if (scelta.equals("0")) {
                System.out.println("Fine dell'intervallo!");

            } else {
                System.out.println("Scelta non valida");
            }
        }
    }
}
