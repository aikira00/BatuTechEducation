
import java.io.*;
import java.net.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class server_verifica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket sc = null;
            System.out.println("Server in ascolto sulla porta 6666...");
            
            while(true){
                //attesa di una richiesta
                sc = ss.accept();
                System.out.println("Ho stabilito una connessione con un client");
                
                //riceve testo dal client
                InputStreamReader messaggioDalClient = new InputStreamReader(sc.getInputStream());
                BufferedReader input = new BufferedReader(messaggioDalClient);
                PrintWriter output = new PrintWriter(sc.getOutputStream(), true);
                output.println("BENVENUTO NEL SERVIZIO DI ELABORAZIONE DI STRIGHE");
                String comandoRicevuto = input.readLine();
                
                while(comandoRicevuto != null){
                    System.out.println("Il server ha ricevuto il comando: " + comandoRicevuto); 
                    
                    String parti[] = comandoRicevuto.split(";");
                    String parte_1 = parti[0].trim();
                    String parte_2 = parti[1].trim();
                    
                    //Comando COUNT - conteggio delle lettere
                    if(parte_1.equalsIgnoreCase("COUNT")){
                        int contatoreLettere = parte_2.length();
                        output.println("OK | COMANDO COUNT ESEGUITO ");
                        output.println("RISULTATO: " + contatoreLettere);
                        System.out.println("Ecco il numero di carattteri della stringa: " + contatoreLettere); 
                    } else if(parte_1.equalsIgnoreCase("CHANGE")){
                        if(parti.length == 3){
                            String parte_3 = parti[2].trim();
                            String parte_4 = parti[3].trim();
                            char carattereOld = parte_2.charAt(0);
                            char carattereNew = parte_3.charAt(0);
                            String messaggioSost = parte_4.replace(carattereOld, carattereNew);
                            output.println("OK | COMANDO CHANGE ESEGUITO"); 
                            output.println("RISULTATO: " + messaggioSost);
                            System.out.println("Ecco la stringa con i caratteri sostituiti: " + messaggioSost);
                        } else {
                            System.out.println("SINTASSI COMANDO CHANGE ERRATA!!!");
                            output.println("COMANDO NON ESEGUITO!!!"); 
                        }
                    } else if(parte_1.equalsIgnoreCase("CHECK")){
                        int contatoreVoc = 0;
                        int contatoreCons = 0; 
                        for(int i = 0; i < parte_2.length(); i++){
                            char carattere = parte_2.charAt(i);
                            if(carattere == 'a' || carattere == 'e' || carattere == 'i' || carattere == 'o' || carattere == 'u'){
                                contatoreVoc += 1; 
                            } else {
                                contatoreCons += 1;
                            }
                        }
                        if(contatoreVoc > contatoreCons){
                            output.println("OK | COMANDO CHECK ESEGUITO");
                            output.println("RISULTATO: voc>cons");
                            System.out.println("Il numero di vocali e' maggiore di quello delle consonanti"); 
                        } else if(contatoreVoc < contatoreCons){
                            output.println("OK | COMANDO CHECK ESEGUITO");
                            output.println("RISULTATO: voc<cons");
                            System.out.println("Il numero di vocali e' minore di quello delle consonanti"); 
                        } else if(contatoreVoc == contatoreCons){
                            output.println("OK | COMANDO CHECK ESEGUITO");
                            output.println("RISULTATO: voc=cons");
                            System.out.println("Il numero di vocali e' uguale a quello delle consonanti"); 
                        }
                    } else if(parte_1.equalsIgnoreCase("INVBIN")){
                        
                    } else if(parte_1.equalsIgnoreCase("REVERSE")){
                        
                    } else if(parte_1.equalsIgnoreCase("VOWEL")){
                        int contatoreVocali = 0;
                        for(int i = 0; i < parte_2.length(); i++){
                            char carattere = parte_2.charAt(i);
                            if(carattere == 'a' || carattere == 'e' || carattere == 'i' || carattere == 'o' || carattere == 'u'){
                                contatoreVocali += 1; 
                            }
                        }
                        output.println("OK | COMANDO VOWEL ESEGUITO");
                        output.println("RISULTATO: " + contatoreVocali);
                        System.out.println("Ecco il numero di vocali della stringa: " + contatoreVocali);
                    } else if(parte_1.equalsIgnoreCase("EXIT")){
                        output.println("OK | DISCONNESSIONE IN CORSO");
                        break;
                    }
                }
                
                System.out.println("Server chiude la comunicazione");
                sc.close();
            }
            
        } catch (IOException ex) {
            System.out.println("ECCEZIONE RILEVATA!!!");
        }
    }
}
