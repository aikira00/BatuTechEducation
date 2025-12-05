
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Server {
    public static void main(String[]args)throws IOException{
   
       ServerSocket ss = new ServerSocket(10000);
       Socket s;
       while(true){
           s = ss.accept();
           PrintWriter out = new PrintWriter(s.getOutputStream(),true);
           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
           String messaggioRicevuto = in.readLine();
           System.out.println("Il server riceve: " + messaggioRicevuto);
           
           String[] parti = messaggioRicevuto.split(";");
           
           String primaParte = parti[0];
           String secondaParte = parti[1];
           String terzaParte = parti[2];
           String QuartaParte = parti[3];
          
           
           //ERR;1 CONTEGGIO LETTERE
           if(primaParte == "COUNT"){
               int c = 0;
               c =secondaParte.length();
               if(c > 8){out.println("ERR;1");}
               else{out.println("Le lettere sono: " + c);}
               }
           //ERR;3 CONTROLLO NUM VOCOALI E NUM CONSONANTI
               int c = 0;
               int v = 0;
           if(primaParte == "CHECK"){
               int i;
               for(i = 0;i < secondaParte.length(); i++){
                   char a = secondaParte.charAt(i);
               if(a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
               { v++;}
               else {c++; }
           
           }
               
               if(c > v){
                   out.println("ERR;3");}
               else{out.println("Vocali maggiori delle consionanti");}
         
               }
           //ERR;6 CONTEGGIO VOCALI
               
           if(primaParte == "VOWEL"){
               int i;
               int vo= 0;
               for(i = 0;i < secondaParte.length(); i++){
                   char a = secondaParte.charAt(i);
               if(a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
               { vo++;}
            
               
               if(v == 0){
                   out.println("ERR;6");}
               else{out.println("Numero vocali:" + vo);}
         
               }
       
          }
           //ERR;5 REVERSE
           if (primaParte ==  "REVERSE"){
               
           }
           
           //ERR;2 CHANGE
           
           
        s.close();
       }
       
    }
}


