/**
 * Server.java - Fila B
 * Autore: Roberto D'Agostino
 * Questo server riceve comandi dal client e manipola una stringa in base ai comandi.
 * Funzionalità implementate: COUNT, CHANGE, CHECK, REVERSE, INVBIN, VOWEL. Aggiunta gestione errori.
 */

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket socketServer = new ServerSocket(9090)) {
            System.out.println("Server in attesa di connessioni...");
            
            while (true) {
                try (Socket socketClient = socketServer.accept();
                     BufferedReader lettoreInput = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                     PrintWriter scrittoreOutput = new PrintWriter(socketClient.getOutputStream(), true)) {

                    System.out.println("Connessione accettata.");

                    String rigaInput;
                    while ((rigaInput = lettoreInput.readLine()) != null) {
                        String risposta = elaboraComando(rigaInput);
                        scrittoreOutput.println(risposta);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nel server: " + e.getMessage());
        }
    }

    private static String elaboraComando(String comando) {
        String[] parti = comando.split(";");
        String operazione = parti[0];
        String risultato = "";

        

        // Gestione errore 2: Parametri insufficienti
        if ((operazione.equals("CHANGE") && parti.length < 4) || (operazione.equals("COUNT") && parti.length < 2)) {
            return "Errore: parametri insufficienti per il comando " + operazione;
        }

        // Gestione errore 3: Parametri vuoti
        for (String parte : parti) {
            if (parte.isEmpty()) {
                return "Errore: parametro vuoto per il comando " + operazione;
            }
        }

        // Gestione errore 4: Carattere non valido nel comando CHANGE
        if (operazione.equals("CHANGE") && (parti[1].length() != 1 || parti[2].length() != 1)) {
            return "Errore: carattere non valido nel comando CHANGE";
        }

        // Gestione errore 5: Stringa troppo lunga
        if (parti[1].length() > 100) {
            return "Errore: la stringa supera il limite di lunghezza di 100 caratteri";
        }

        // Processamento comandi validi
        switch (operazione) {
            case Protocol.COUNT:
                risultato = "Lettere contate: " + parti[1].length();
                break;
            case Protocol.CHANGE:
                risultato = parti[3].replace(parti[1], parti[2]);  //sostituzione di stringhe
                break;
            case Protocol.CHECK:
                risultato = contaVocaliEConsonanti(parti[1]);
                break;
            case Protocol.REVERSE:
                risultato = InvertiStringa(parti[1]);
                break;
            case Protocol.VOWEL:
                risultato = contaVocali(parti[1]);
                break;
        // Gestione errore 1: Comando non riconosciuto
            default:
                risultato = "Errore: comando non riconosciuto";
                break;
        }
        return risultato;
    } 
        
        

    private static String contaVocaliEConsonanti(String input) {
        int vocali = 0, consonanti = 0;
        input = input.toLowerCase(); //conversione della stringa di input in minuscolo

        for (char c : input.toCharArray()) {  //conversione di una stringa in un array di caratteri
            if ("aeiou".indexOf(c) != -1) {  //controllo se il carattere e presente nella stringa
                vocali++;
            } else if (Character.isLetter(c)) { //controllo se il carattere è una lettera maiscuola o minuscola
                consonanti++;
            }
        }

        return vocali > consonanti ? "Più vocali" : "Più consonanti";
    }


    private static String InvertiStringa(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    private static String contaVocali(String input) {
        int vocali1 = 0;
        input = input.toLowerCase();  //conversione della stringa di input in minuscolo
        for (char c : input.toCharArray()) {  //conversione di una stringa in un array di caratteri
            if ("aeiou".indexOf(c) != -1) {  //controllo se il carattere e presente nella stringa
                vocali1++;
            }
        }
        return "Vocali contate: " + vocali1;
    }
}