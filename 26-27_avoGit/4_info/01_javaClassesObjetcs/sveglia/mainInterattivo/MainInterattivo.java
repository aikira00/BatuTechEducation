package mainInterattivo;

// PASSO 4 — "Siete il compilatore, parte 3": un main interattivo che usa la Sveglia del bis, senza modificarla.
// Il main non controlla mai un orario: controlla solo l'input dell'utente (cifre, numero della sveglia).
// Niente try/catch: le cifre si controllano con un ciclo su charAt. Si confrontano stringhe e interi, mai sveglie.
import java.util.Scanner;

public class MainInterattivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sveglia[] sveglie = new Sveglia[10];   // posto per 10 sveglie
        int quante = 0;                        // quante celle sono occupate davvero

        // 1. Inserimento
        System.out.println("=== Inserimento sveglie ===");
        String risposta = "s";   // per entrare nel ciclo la prima volta
        while (risposta.equals("s") && quante < sveglie.length) {
            System.out.print("Ora: ");
            String rigaOra = sc.nextLine().trim();
            System.out.print("Minuti: ");
            String rigaMinuti = sc.nextLine().trim();
            System.out.print("Etichetta (invio per nessuna): ");
            String etichetta = sc.nextLine().trim();

            // ora e minuti devono essere fatti solo di cifre
            boolean numeri = !rigaOra.isEmpty() && !rigaMinuti.isEmpty();
            String cifre = rigaOra + rigaMinuti;
            for (int i = 0; i < cifre.length(); i++) {
                if (cifre.charAt(i) < '0' || cifre.charAt(i) > '9') {
                    numeri = false;
                }
            }

            if (!numeri) {
                System.out.println("Errore: ora e minuti devono essere numeri. Ricominciamo questa sveglia.");
            } else {
                int ora = Integer.parseInt(rigaOra);
                int minuti = Integer.parseInt(rigaMinuti);
                if (etichetta.isEmpty()) {
                    sveglie[quante] = new Sveglia(ora, minuti);
                } else {
                    sveglie[quante] = new Sveglia(ora, minuti, etichetta);
                }
                System.out.println("Inserita: " + sveglie[quante]);
                quante++;

                if (quante < sveglie.length) {
                    System.out.print("Un'altra? (s/n): ");
                    risposta = sc.nextLine().trim().toLowerCase();
                } else {
                    System.out.println("Elenco pieno: " + sveglie.length + " sveglie");
                }
            }
        }

        // 2. Menu
        String scelta = "";   // qualsiasi valore diverso da "0", per entrare nel ciclo
        while (!scelta.equals("0")) {
            System.out.println();
            System.out.println("=== MENU ===");
            System.out.println("1. Elenco");
            System.out.println("2. Cerca per etichetta");
            System.out.println("3. Modifica");
            System.out.println("4. Posticipa");
            System.out.println("5. Che ore sono?");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            scelta = sc.nextLine().trim();

            if (scelta.equals("1")) {
                for (int i = 0; i < quante; i++) {
                    System.out.println((i + 1) + ". " + sveglie[i]);
                }

            } else if (scelta.equals("2")) {
                System.out.print("Etichetta da cercare: ");
                String cercata = sc.nextLine().trim();
                int trovate = 0;
                for (int i = 0; i < quante; i++) {
                    if (sveglie[i].getEtichetta().equals(cercata)) {
                        System.out.println((i + 1) + ". " + sveglie[i]);
                        trovate++;
                    }
                }
                if (trovate == 0) {
                    System.out.println("Nessuna sveglia con etichetta \"" + cercata + "\"");
                }

            } else if (scelta.equals("3")) {
                System.out.print("Numero della sveglia: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è la sveglia \"" + rigaNumero + "\"");
                } else {
                    System.out.println("Prima: " + sveglie[pos]);
                    // prima si legge e si controlla tutto, poi si modifica
                    System.out.print("Nuova ora: ");
                    String rigaOra = sc.nextLine().trim();
                    System.out.print("Nuovi minuti: ");
                    String rigaMinuti = sc.nextLine().trim();
                    System.out.print("Nuova etichetta (invio per lasciarla): ");
                    String etichetta = sc.nextLine().trim();

                    boolean numeri = !rigaOra.isEmpty() && !rigaMinuti.isEmpty();
                    String cifre = rigaOra + rigaMinuti;
                    for (int i = 0; i < cifre.length(); i++) {
                        if (cifre.charAt(i) < '0' || cifre.charAt(i) > '9') {
                            numeri = false;
                        }
                    }

                    if (!numeri) {
                        System.out.println("Errore: ora e minuti devono essere numeri. Sveglia non modificata.");
                    } else {
                        sveglie[pos].setOra(Integer.parseInt(rigaOra));
                        sveglie[pos].setMinuti(Integer.parseInt(rigaMinuti));
                        if (!etichetta.isEmpty()) {
                            sveglie[pos].setEtichetta(etichetta);
                        }
                        System.out.println("Dopo:  " + sveglie[pos]);
                    }
                }

            } else if (scelta.equals("4")) {
                System.out.print("Numero della sveglia: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è la sveglia \"" + rigaNumero + "\"");
                } else {
                    System.out.println("Prima: " + sveglie[pos]);
                    System.out.print("Di quanti minuti? (invio per 5): ");
                    String riga = sc.nextLine().trim();

                    boolean numero = true;
                    for (int i = 0; i < riga.length(); i++) {
                        if (riga.charAt(i) < '0' || riga.charAt(i) > '9') {
                            numero = false;
                        }
                    }

                    if (riga.isEmpty()) {
                        sveglie[pos].posticipa();
                        System.out.println("Dopo:  " + sveglie[pos]);
                    } else if (!numero) {
                        System.out.println("Errore: \"" + riga + "\" non è un numero. Sveglia non posticipata.");
                    } else {
                        sveglie[pos].posticipa(Integer.parseInt(riga));
                        System.out.println("Dopo:  " + sveglie[pos]);
                    }
                }

            } else if (scelta.equals("5")) {
                System.out.print("Ora: ");
                String rigaOra = sc.nextLine().trim();
                System.out.print("Minuti: ");
                String rigaMinuti = sc.nextLine().trim();

                boolean numeri = !rigaOra.isEmpty() && !rigaMinuti.isEmpty();
                String cifre = rigaOra + rigaMinuti;
                for (int i = 0; i < cifre.length(); i++) {
                    if (cifre.charAt(i) < '0' || cifre.charAt(i) > '9') {
                        numeri = false;
                    }
                }

                if (!numeri) {
                    System.out.println("Errore: ora e minuti devono essere numeri.");
                } else {
                    int ora = Integer.parseInt(rigaOra);
                    int minuti = Integer.parseInt(rigaMinuti);
                    int suonate = 0;
                    for (int i = 0; i < quante; i++) {
                        if (sveglie[i].getOra() == ora && sveglie[i].getMinuti() == minuti) {
                            System.out.print(sveglie[i].getEtichetta() + ": ");
                            sveglie[i].suona();
                            suonate++;
                        }
                    }
                    if (suonate == 0) {
                        System.out.println("A quell'ora non suona nessuna sveglia");
                    }
                }

            } else if (scelta.equals("0")) {
                System.out.println("Ciao!");

            } else {
                System.out.println("Scelta non valida");
            }
        }
    }
}
