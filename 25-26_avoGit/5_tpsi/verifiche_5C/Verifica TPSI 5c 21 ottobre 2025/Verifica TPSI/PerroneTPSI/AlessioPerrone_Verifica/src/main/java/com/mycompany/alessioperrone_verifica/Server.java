//Alessio Perrone Fila B
package com.mycompany.alessioperrone_verifica;
import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Server {
    public static void main (String[] args){
        int porta = 9999;
        try (ServerSocket ss = new ServerSocket(porta)){
            System.out.println("Server in ascolto nella porta: " + porta);
            while(true){
                Socket s = ss.accept() ;
                System.out.println("\nnuova connesione dal client" + s.getInetAddress());
                
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                
                String s1;
                
                //ciclo che legge finche il client è connesso
                while((s1= in.readLine())!= null){
                    //comandi
                    //se client scrive exit esce dal programma
                    if(s1.equalsIgnoreCase("exit")){
                        out.println("connessione chiusa.");
                        System.out.println("Il client ha chiuso la connessione");
                        break;
                    }
                    
                    if(s1.isEmpty()){
                        out.println("Comando assente");
                        System.out.println("Comando assente");
                        continue;
                    }
                    
                    if(s1.length() > 15){
                        out.println("Parola troppo grande!!");
                        System.out.println("Parola troppo grande!!");
                        continue;
                    }
                    
                    //salva il valore inserito correttamnete
                    String[] comando;
                    comando = s1.split(";");
                    
                    //controlla se il comando esiste

                    
                    
                    //controllo presenza di numeri non caratteri
                    boolean errore = false;
                    for (int i = comando.length + 1 ; i < s1.length(); i++){
                        char c = s1.charAt(i);
                        for(int l = 0 ;l < 9 ; l++){
                            if(!Character.isDigit(c) && c != l ){
                                out.println("Presenza di numeri!!");
                                System.out.println("Presenza di numeri!!");
                                errore = true;
                                break;
                            }
                        }
                    }
                    if(errore) continue;
                    
                    if(!"COUNT".equals(comando[0]) && !"CHANGE".equals(comando[0]) && !"CHECK".equals(comando[0]) && !"REVERSE".equals(comando[0]) && !"VOWEL".equals(comando[0]) ){
                        out.println("Comando sbagliato!!");
                        System.out.println("Comando sbagliato!!");
                        continue;
                     }
                    
                    //estrai la parola
                    String parola = s1.substring(1);
                    //Comandi
                    //Count
                    if("COUNT".equals(comando[0])){
                      int ris = 0;
                      ris = ris + parola.length();
                      out.println("Risultato = " + ris);
                      System.out.println("Risultato = " + ris);
                    }
                    if("CHANGE".equals(comando[0])){
                      String[] sost1;
                      sost1 = parola.split(",");
                      //continuare
                    }
                    if("VOWEL".equals(comando[0])){
                      //continuare
                      int voc = 0;
                      for(int i = 0; i<parola.length(); i++){
                          if(parola.contains("a")&& parola.contains("e")&& parola.contains("i")&& parola.contains("o")&& parola.contains("u"))
                              voc++;
                      }
                      out.println("Numero Vocali" + voc);
                      System.out.println("Numero Vocali" + voc);
                    }
                    if("REVERSE".equals(comando[0])){
                        char[] norm = parola.toCharArray();
                        for(int i = parola.length(); i > 0 ; i--){
                            int l=0;
                            norm[l] = norm[i];
                            l++;
                        }
                        out.println("La parola al contrario = " + Arrays.toString(norm));
                        System.out.println("La parola al contrario = " + Arrays.toString(norm));
                    }
                    
                    if("CHECK".equals(comando[0])){
                      int voc = 0;
                      int cons = 0;
                      for(int i = 0; i<parola.length(); i++){
                          if(parola.contains("a")&& parola.contains("e")&& parola.contains("i")&& parola.contains("o")&& parola.contains("u"))
                              voc++;
                          else
                              cons++;
                      }
                      if(voc>cons){
                      out.println("ci sono piu vocali: " + voc);
                      System.out.println("ci sono piu vocali: " + voc);
                      }
                      else{
                          out.println("ci sono piu consonati: " + cons);
                      System.out.println("ci sono piu consonati: " + cons);
                      }
                    }
                    
                    
                }
                s.close();
            }
        }   catch(IOException e){
            e.printStackTrace();
        }
    }
}
