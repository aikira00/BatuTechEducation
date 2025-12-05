package cataldo_verifica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/*
Nome autore: Paolo Cataldo
Fila: B
*/
public class Server {
    
    

    public static void main(String[] args) throws IOException {
        
        ArrayList<String> stringa = new ArrayList<>();

        ServerSocket ss = new ServerSocket(9999);
        System.out.println("SERVER IN ASCOLTO...");
        
        while(true){
            
            Socket s = ss.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            
            String input = in.readLine();
            System.out.println("Richiesta ricevuta: " + input);
            
            String[] parti = input.split(";");
            
            if(parti[0].equalsIgnoreCase("CHANGE")){        //CONTROLLO NUMERO ARGOMENTI
                if(parti.length != 4){
                    out.println("ERR;01");
                }
            } else if(parti.length != 2){
                out.println("ERR;01");
            }
            
            String op = parti[0];
            
            switch(op){
                case "COUNT":
                    int count = parti[1].length();
                    out.println("OK;COUNT:" + count);
                    break;
                case "CHECK":
                    int countVocali = 0;
                    int countConsonanti = 0;
                    String[] messaggioCheck = parti[1].split("");
                    for(int i = 0; i < messaggioCheck.length; i++){
                        if(messaggioCheck[i].equalsIgnoreCase("A") || messaggioCheck[i].equalsIgnoreCase("E") || messaggioCheck[i].equalsIgnoreCase("I") || messaggioCheck[i].equalsIgnoreCase("O") || messaggioCheck[i].equalsIgnoreCase("U")){
                            countVocali++;
                        }
                        else{
                            countConsonanti++;
                        }
                    }
                    if(countVocali > countConsonanti){
                        out.println("OK;TRUE");
                        break;
                    }
                    else{
                        out.println("OK;FALSE");
                        break;
                    }
                case "INVBIN":
                    String[] messaggioInvbin = parti[1].split("");
                    for(int i = 0; i < messaggioInvbin.length; i++){
                        String temp;
                        
                        temp = messaggioInvbin[i];
                        if(i == messaggioInvbin.length - 1){
                            break;
                        } else{
                            messaggioInvbin[i] = messaggioInvbin[i + 1];
                            messaggioInvbin[i + 1] = temp;
                        }
                        
                        i++;
                    }
                    
                    StringBuilder messaggioInvbinOutput = new StringBuilder("");
                    
                    for(int i = 0; i < messaggioInvbin.length; i++){
                        messaggioInvbinOutput.append(messaggioInvbin[i]);
                    }
                                        
                    out.println("OK;INVBIN:" + messaggioInvbinOutput);
                    break;
                    
                case "REVERSE":
                    String[] messaggioReverse = parti[1].split("");
                    StringBuilder messaggioReverseOutput = new StringBuilder("");
                    
                    for(int i = messaggioReverse.length - 1; i >= 0; i--){
                        messaggioReverseOutput.append(messaggioReverse[i]);
                        System.out.println(messaggioReverse[i]);
                    }
                    
                    out.println("OK;REVERSE:" + messaggioReverseOutput);
                    break;
                 
                case "VOWEL":
                    int countVowel = 0;
                    String[] messaggioVowel = parti[1].split("");
                    for(int i = 0; i < messaggioVowel.length; i++){
                        if(messaggioVowel[i].equalsIgnoreCase("A") || messaggioVowel[i].equalsIgnoreCase("E") || messaggioVowel[i].equalsIgnoreCase("I") || messaggioVowel[i].equalsIgnoreCase("O") || messaggioVowel[i].equalsIgnoreCase("U")){
                            countVowel++;
                        }
                    }
                    
                    out.println("OK;VOWEL:" + countVowel);
                    break;
                case "CHANGE":
                    String[] messaggioChange = parti[3].split("");
                
                    boolean trovato = false;
                    for(int i = 0; i < messaggioChange.length; i++){
                        if(messaggioChange[i].equalsIgnoreCase(parti[1])){
                            messaggioChange[i] = parti[2];
                            trovato = true;
                        }
                    }
                    if(trovato == false){
                        out.println("ERR;03");
                    }
                    
                    StringBuilder messaggioOutput = new StringBuilder("");
                    for(int i = 0; i < messaggioChange.length; i++){
                        messaggioOutput.append(messaggioChange[i]);
                    }
                    
                    out.println("OK;CHANGE:" + messaggioOutput);
                    break;
                    
                default:
                    out.println("ERR;02");
                    continue;
                    

                    
            }
            
        }
    }
    
}
