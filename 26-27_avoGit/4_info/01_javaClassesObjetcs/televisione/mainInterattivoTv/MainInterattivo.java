package mainInterattivoTv;

// "La televisione, parte 2" (esercitazione 06_01): un main interattivo che usa la Televisione della 06, senza modificarla.
// Il main non controlla mai le regole del televisore: controlla solo l'input dell'utente (cifre, numero del televisore).
// Niente try/catch: le cifre si controllano con un ciclo su charAt. Si confrontano stringhe e interi, mai televisori.
import java.util.Scanner;

public class MainInterattivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Televisione[] televisori = new Televisione[10];   // posto per 10 televisori
        int quante = 0;                                     // quante celle sono occupate davvero

        // 1. Inserimento
        System.out.println("=== Inserimento televisori ===");
        String risposta = "s";   // per entrare nel ciclo la prima volta
        while (risposta.equals("s") && quante < televisori.length) {
            System.out.print("Marca: ");
            String marca = sc.nextLine().trim();
            System.out.print("Pollici: ");
            String rigaPollici = sc.nextLine().trim();

            // i pollici devono essere fatti solo di cifre
            boolean numero = !rigaPollici.isEmpty();
            for (int i = 0; i < rigaPollici.length(); i++) {
                if (rigaPollici.charAt(i) < '0' || rigaPollici.charAt(i) > '9') {
                    numero = false;
                }
            }

            if (!numero) {
                System.out.println("Errore: i pollici devono essere un numero. Ricominciamo questo televisore.");
            } else {
                televisori[quante] = new Televisione(marca, Integer.parseInt(rigaPollici));
                System.out.println("Inserito: " + televisori[quante]);
                quante++;

                if (quante < televisori.length) {
                    System.out.print("Un altro? (s/n): ");
                    risposta = sc.nextLine().trim().toLowerCase();
                } else {
                    System.out.println("Elenco pieno: " + televisori.length + " televisori");
                }
            }
        }

        // 2. Menu
        String scelta = "";   // qualsiasi valore diverso da "0", per entrare nel ciclo
        while (!scelta.equals("0")) {
            System.out.println();
            System.out.println("=== MENU ===");
            System.out.println("1. Elenco");
            System.out.println("2. Cerca per marca");
            System.out.println("3. Accendi o spegni");
            System.out.println("4. Cambia canale");
            System.out.println("5. Volume");
            System.out.println("6. Partita! Tutti sul canale di un televisore");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            scelta = sc.nextLine().trim();

            if (scelta.equals("1")) {
                for (int i = 0; i < quante; i++) {
                    System.out.println((i + 1) + ". " + televisori[i]);
                }

            } else if (scelta.equals("2")) {
                System.out.print("Marca da cercare: ");
                String cercata = sc.nextLine().trim();
                int trovati = 0;
                for (int i = 0; i < quante; i++) {
                    if (televisori[i].getMarca().equals(cercata)) {
                        System.out.println((i + 1) + ". " + televisori[i]);
                        trovati++;
                    }
                }
                if (trovati == 0) {
                    System.out.println("Nessun televisore di marca \"" + cercata + "\"");
                }

            } else if (scelta.equals("3")) {
                System.out.print("Numero del televisore: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è il televisore \"" + rigaNumero + "\"");
                } else {
                    System.out.println("Prima: " + televisori[pos]);
                    System.out.print("a per accendere, s per spegnere: ");
                    String tasto = sc.nextLine().trim().toLowerCase();
                    if (tasto.equals("a")) {
                        televisori[pos].accendi();
                    } else if (tasto.equals("s")) {
                        televisori[pos].spegni();
                    } else {
                        System.out.println("Errore: tasto \"" + tasto + "\" sconosciuto");
                    }
                    System.out.println("Dopo:  " + televisori[pos]);
                }

            } else if (scelta.equals("4")) {
                System.out.print("Numero del televisore: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è il televisore \"" + rigaNumero + "\"");
                } else {
                    System.out.println("Prima: " + televisori[pos]);
                    System.out.print("Canale: ");
                    String rigaCanale = sc.nextLine().trim();

                    boolean numero = !rigaCanale.isEmpty();
                    for (int i = 0; i < rigaCanale.length(); i++) {
                        if (rigaCanale.charAt(i) < '0' || rigaCanale.charAt(i) > '9') {
                            numero = false;
                        }
                    }

                    if (!numero) {
                        System.out.println("Errore: il canale deve essere un numero. Canale non cambiato.");
                    } else {
                        televisori[pos].cambiaCanale(Integer.parseInt(rigaCanale));
                        System.out.println("Dopo:  " + televisori[pos]);
                    }
                }

            } else if (scelta.equals("5")) {
                System.out.print("Numero del televisore: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è il televisore \"" + rigaNumero + "\"");
                } else {
                    System.out.println("Prima: " + televisori[pos]);
                    System.out.print("+ per alzare, - per abbassare, m per il muto: ");
                    String tasto = sc.nextLine().trim().toLowerCase();
                    if (tasto.equals("+")) {
                        System.out.print("Di quanti passi? (invio per 1): ");
                        String riga = sc.nextLine().trim();

                        boolean numero = true;
                        for (int i = 0; i < riga.length(); i++) {
                            if (riga.charAt(i) < '0' || riga.charAt(i) > '9') {
                                numero = false;
                            }
                        }

                        if (riga.isEmpty()) {
                            televisori[pos].alzaVolume();
                        } else if (!numero) {
                            System.out.println("Errore: \"" + riga + "\" non è un numero. Volume non cambiato.");
                        } else {
                            televisori[pos].alzaVolume(Integer.parseInt(riga));
                        }
                    } else if (tasto.equals("-")) {
                        televisori[pos].abbassaVolume();
                    } else if (tasto.equals("m")) {
                        televisori[pos].attivaMuto();
                    } else {
                        System.out.println("Errore: tasto \"" + tasto + "\" sconosciuto");
                    }
                    System.out.println("Dopo:  " + televisori[pos]);
                }

            } else if (scelta.equals("6")) {
                System.out.print("Tutti sul canale del televisore numero: ");
                String rigaNumero = sc.nextLine().trim();
                int pos = -1;
                for (int i = 0; i < quante; i++) {
                    if (rigaNumero.equals("" + (i + 1))) {
                        pos = i;
                    }
                }

                if (pos == -1) {
                    System.out.println("Errore: non c'è il televisore \"" + rigaNumero + "\"");
                } else {
                    for (int i = 0; i < quante; i++) {
                        if (i != pos) {
                            televisori[i].sintonizzaCome(televisori[pos]);
                        }
                    }
                    for (int i = 0; i < quante; i++) {
                        System.out.println((i + 1) + ". " + televisori[i]);
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
